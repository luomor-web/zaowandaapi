package com.siival.bot.modules.bsc.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.siival.bot.task.Task;
import com.siival.bot.utils.BeanUtil;

public class LilliaFileBatchReadTask extends Task {
    private final Log logger = LogFactory.getLog(LilliaFileBatchReadTask.class);
    private Long ctFileBatchId = -1L;

    public LilliaFileBatchReadTask(Long ctFileBatchId, long delayInMilliseconds) {
        super("LilliaFileBatchReadTask-" + ctFileBatchId, delayInMilliseconds);
        this.ctFileBatchId = ctFileBatchId;
    }

    public LilliaFileBatchReadTask(Long ctFileBatchId) {
        super("LilliaFileBatchReadTask-" + ctFileBatchId, 0);
        this.ctFileBatchId = ctFileBatchId;
    }

    @Override
    public void run() {
        logger.info("读取文件信息---批次读取BEGIN---" + this.ctFileBatchId);

        // read image
        CtFileBatchService ctFileBatchService = BeanUtil.getBean(CtFileBatchService.class);
        CtFileService ctFileService = BeanUtil.getBean(CtFileService.class);

        FileBaseService fileBaseService;

        CtFileBatch ctFileBatch = ctFileBatchService.findFileBatch(this.ctFileBatchId);

        if(ctFileBatch == null) {
            logger.info("读取文件信息---批次读取ERR---" + this.ctFileBatchId + "---没有批次信息");
            return;
        }
        if(ctFileBatch.getStatus().equals(CtFileBatchService.STATUS_READ)) {
            logger.info("读取文件信息---批次读取ERR---" + this.ctFileBatchId + "---上传批次读取中，请稍后查看");
            return;
        }
        List<CtFile> ctFiles = ctFileService.querySelective(this.ctFileBatchId);

        CtFile ctFile = null;
        BufferedInputStream inputStream = null;
        URL url = null;
        FileOutputStream fileOS = null;
        byte data[] = null;
        String suffix = "";
        String userDir = "";
        Long flag = 0L;
        Byte readStatus = 0;
        Byte status = 0;
        int time = 0;
        int date = 0;

        boolean hasAllRead = true;
        boolean existData = false;
        int readNum = 0;
        String readRet = "";

        try {
            ctFileBatch.setStatus(CtFileBatchService.STATUS_READ);
            ctFileBatchService.updateById(ctFileBatch);

            for(int i = 0 ; i < ctFiles.size() ; i++) {
                ctFile = ctFiles.get(i);

                userDir = System.getProperty("user.dir");
                url = new URL(ctFile.getFilePath());
                inputStream = new BufferedInputStream(url.openStream());

                suffix = RegexUtil.parseSuffix(ctFile.getFilePath());
                String fileName = userDir + "/fileName" + CharUtil.getRandomString(6) + "." + suffix;
                fileOS = new FileOutputStream(fileName);
                data = new byte[1024];
                int byteContent;
                while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                    fileOS.write(data, 0, byteContent);
                }
                File localFile = new File(fileName);

                fileBaseService = FileServiceBuild.buildService(ctFile.getFileType());
                fileBaseService.read(localFile);
                
                readNum++;
                status = CtFileService.STATUS_EXCEL;

                ctFile.setStatus(status);
                ctFile.setReadStatus(readStatus);
                ctFile.setReadRet(readRet);
                ctFile.setFlag(flag);

                ctFileService.updateById(ctFile);

                if(localFile.delete()) {
                    logger.info("读取文件信息---批次读取---删除文件成功" + fileName);
                } else {
                    logger.info("读取文件信息---批次读取---删除文件失败" + fileName);
                }
            }
            if(hasAllRead) {
                readStatus = CtFileBatchService.READ_STATUS_ALL_DONE;
                readRet = "全部读取文件信息";
            } else {
                if(existData) {
                    readStatus = CtFileBatchService.READ_STATUS_PART_DONE;
                    readRet = "部分读取文件信息";
                } else {
                    readStatus = CtFileBatchService.READ_STATUS_NO_EXCEL;
                    readRet = "所有文件没有信息";
                }
            }
            //update ct upload batch
            ctFileBatch.setReadNum(readNum);
            ctFileBatch.setReadStatus(readStatus);
            ctFileBatch.setReadRet(readRet);
            ctFileBatch.setStatus(CtFileBatchService.STATUS_EXCEL);
            ctFileBatchService.updateById(ctFileBatch);
        } catch(Exception e) {
            ctFileBatch.setReadNum(readNum);
            ctFileBatch.setReadRet("读取文件错误");
            ctFileBatch.setStatus(CtFileBatchService.STATUS_READ_ERR);
            ctFileBatchService.updateById(ctFileBatch);
            e.printStackTrace();
            logger.error("读取文件信息---批次读取Exception---" + e.toString(), e);
        }

        logger.info("读取文件信息---批次读取END---" + this.ctFileBatchId);
    }
}

