package com.siival.bot.modules.bsc.task;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.siival.bot.modules.bsc.domain.LilliaFile;
import com.siival.bot.modules.bsc.domain.LilliaFileBatch;
import com.siival.bot.modules.bsc.service.LilliaFileBatchService;
import com.siival.bot.modules.bsc.service.LilliaFileService;
import com.siival.bot.modules.bsc.service.dto.LilliaFileBatchDto;
import com.siival.bot.modules.bsc.service.dto.LilliaFileDto;
import com.siival.bot.modules.bsc.service.dto.LilliaFileQueryCriteria;
import com.siival.bot.modules.bsc.service.file.FileBaseService;
import com.siival.bot.modules.bsc.service.file.FileServiceBuild;
import com.siival.bot.modules.bsc.service.impl.LilliaFileBatchServiceImpl;
import com.siival.bot.modules.bsc.service.impl.LilliaFileServiceImpl;
import com.siival.bot.task.Task;
import com.siival.bot.utils.BeanUtil;
import com.siival.bot.utils.CharUtil;
import com.siival.bot.utils.RegexUtil;

public class LilliaFileBatchReadTask extends Task {
    private final Log logger = LogFactory.getLog(LilliaFileBatchReadTask.class);
    private Long lilliaFileBatchId = -1L;

    public LilliaFileBatchReadTask(Long lilliaFileBatchId, long delayInMilliseconds) {
        super("LilliaFileBatchReadTask-" + lilliaFileBatchId, delayInMilliseconds);
        this.lilliaFileBatchId = lilliaFileBatchId;
    }

    public LilliaFileBatchReadTask(Long lilliaFileBatchId) {
        super("LilliaFileBatchReadTask-" + lilliaFileBatchId, 0);
        this.lilliaFileBatchId = lilliaFileBatchId;
    }

    @Override
    public void run() {
        logger.info("读取文件信息---批次读取BEGIN---" + this.lilliaFileBatchId);

        // read image
        LilliaFileBatchService lilliaFileBatchService = BeanUtil.getBean(LilliaFileBatchServiceImpl.class);
        LilliaFileService lilliaFileService = BeanUtil.getBean(LilliaFileServiceImpl.class);

        FileBaseService fileBaseService;

        LilliaFileBatchDto lilliaFileBatchDto = lilliaFileBatchService.findById(this.lilliaFileBatchId);

        if(lilliaFileBatchDto == null) {
            logger.info("读取文件信息---批次读取ERR---" + this.lilliaFileBatchId + "---没有批次信息");
            return;
        }
        if(lilliaFileBatchDto.getStatus().equals(com.siival.bot.modules.bsc.service.lillia.LilliaFileBatch1Service.STATUS_READ)) {
            logger.info("读取文件信息---批次读取ERR---" + this.lilliaFileBatchId + "---上传批次读取中，请稍后查看");
            return;
        }
        LilliaFileQueryCriteria lilliaFileQueryCriteria = new LilliaFileQueryCriteria();
        lilliaFileQueryCriteria.setLilliaFileBatchId(this.lilliaFileBatchId);
        List<LilliaFileDto> lilliaFiles = lilliaFileService.queryAll(lilliaFileQueryCriteria);

        LilliaFileDto lilliaFileDto = null;
        BufferedInputStream inputStream = null;
        URL url = null;
        FileOutputStream fileOS = null;
        byte data[] = null;
        String suffix = "";
        String userDir = "";
        Long flag = 0L;
        Integer readStatus = 0;
        Integer status = 0;
        int time = 0;
        int date = 0;

        boolean hasAllRead = true;
        boolean existData = false;
        int readNum = 0;
        String readRet = "";

        try {
            lilliaFileBatchDto.setStatus(com.siival.bot.modules.bsc.service.lillia.LilliaFileBatch1Service.STATUS_READ);
            LilliaFileBatch lilliaFileBatch = new LilliaFileBatch();
            lilliaFileBatch.setLilliaFileBatchId(this.lilliaFileBatchId);
            lilliaFileBatch.setStatus(com.siival.bot.modules.bsc.service.lillia.LilliaFileBatch1Service.STATUS_READ);
            lilliaFileBatchService.update(lilliaFileBatch);

            for(int i = 0 ; i < lilliaFiles.size() ; i++) {
                lilliaFileDto = lilliaFiles.get(i);

                userDir = System.getProperty("user.dir");
                url = new URL(lilliaFileDto.getFilePath());
                inputStream = new BufferedInputStream(url.openStream());

                suffix = RegexUtil.parseSuffix(lilliaFileDto.getFilePath());
                String fileName = userDir + "/fileName" + CharUtil.getRandomString(6) + "." + suffix;
                fileOS = new FileOutputStream(fileName);
                data = new byte[1024];
                int byteContent;
                while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                    fileOS.write(data, 0, byteContent);
                }
                File localFile = new File(fileName);

                fileBaseService = FileServiceBuild.buildService(lilliaFileDto.getFileType());
                fileBaseService.read(localFile, lilliaFileBatchDto);
                
                readNum++;
                status = com.siival.bot.modules.bsc.service.lillia.LilliaFile1Service.STATUS_EXCEL;

                lilliaFileDto.setStatus(status);
                lilliaFileDto.setReadStatus(readStatus);
                lilliaFileDto.setReadRet(readRet);
                lilliaFileDto.setFlag(flag);

                LilliaFile lilliaFile = new LilliaFile();
                lilliaFile.setLilliaFileId(lilliaFileDto.getLilliaFileId());
                lilliaFile.setStatus(status);
                lilliaFile.setReadStatus(readStatus);
                lilliaFile.setReadRet(readRet);
                lilliaFile.setFlag(flag);
                lilliaFileService.update(lilliaFile);

                if(localFile.delete()) {
                    logger.info("读取文件信息---批次读取---删除文件成功" + fileName);
                } else {
                    logger.info("读取文件信息---批次读取---删除文件失败" + fileName);
                }
            }
            if(hasAllRead) {
                readStatus = com.siival.bot.modules.bsc.service.lillia.LilliaFileBatch1Service.READ_STATUS_ALL_DONE;
                readRet = "全部读取文件信息";
            } else {
                if(existData) {
                    readStatus = com.siival.bot.modules.bsc.service.lillia.LilliaFileBatch1Service.READ_STATUS_PART_DONE;
                    readRet = "部分读取文件信息";
                } else {
                    readStatus = com.siival.bot.modules.bsc.service.lillia.LilliaFileBatch1Service.READ_STATUS_NO_EXCEL;
                    readRet = "所有文件没有信息";
                }
            }
            //update ct upload batch

            lilliaFileBatch = new LilliaFileBatch();
            lilliaFileBatch.setLilliaFileBatchId(this.lilliaFileBatchId);
            lilliaFileBatch.setReadNum(readNum);
            lilliaFileBatch.setReadStatus(readStatus);
            lilliaFileBatch.setReadRet(readRet);
            lilliaFileBatch.setStatus(com.siival.bot.modules.bsc.service.lillia.LilliaFileBatch1Service.STATUS_READ);

            lilliaFileBatchService.update(lilliaFileBatch);
        } catch(Exception e) {
            LilliaFileBatch lilliaFileBatch = new LilliaFileBatch();
            lilliaFileBatch.setLilliaFileBatchId(this.lilliaFileBatchId);
            lilliaFileBatch.setReadNum(readNum);
            lilliaFileBatch.setReadRet("读取文件错误");
            lilliaFileBatch.setStatus(com.siival.bot.modules.bsc.service.lillia.LilliaFileBatch1Service.STATUS_READ_ERR);

            lilliaFileBatchService.update(lilliaFileBatch);
            e.printStackTrace();
            logger.error("读取文件信息---批次读取Exception---" + e.toString(), e);
        }

        logger.info("读取文件信息---批次读取END---" + this.lilliaFileBatchId);
    }
}

