package com.siival.bot.modules.bsc.service.lillia;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.siival.bot.modules.bsc.domain.LilliaFileBatch;
import com.siival.bot.modules.bsc.service.LilliaFileBatchService;
import com.siival.bot.modules.bsc.service.dto.LilliaFileBatchDto;
import com.siival.bot.modules.bsc.service.mapstruct.LilliaFileBatchMapper;
import com.siival.bot.modules.bsc.task.LilliaFileBatchReadTask;
import com.siival.bot.task.TaskService;

@Service
public class LilliaFileBatch1Service extends LilliaBaseService {
    public static final Integer READ_STATUS_INIT = 0; // init
    public static final Integer READ_STATUS_ALL_DONE = 1; // 获取excel信息
    public static final Integer READ_STATUS_PART_DONE = 2; // 部分获取excel信息
    public static final Integer READ_STATUS_NO_EXCEL = 3; // 没有excel信息

    @Autowired
    private LilliaFileBatchService lilliaFileBatchService;

    @Autowired
    private TaskService taskService;

    private final Log logger = LogFactory.getLog(LilliaFileBatch1Service.class);

    public Object addTask(LilliaFileBatch lilliaFileBatch) {
        Long lilliaFileBatchId = lilliaFileBatch.getLilliaFileBatchId();
        //check read
        LilliaFileBatchDto dbLilliaFileBatch = lilliaFileBatchService.findById(lilliaFileBatchId);
        if(dbLilliaFileBatch == null) {
            return new ResponseEntity<>("没有上传批次", HttpStatus.BAD_REQUEST);
        }
        if(dbLilliaFileBatch.getStatus().equals(LilliaFileBatch1Service.STATUS_READ)) {
            return new ResponseEntity<>("上传批次读取中，请稍后查看", HttpStatus.BAD_REQUEST);
        }
        dbLilliaFileBatch.setStatus(LilliaFileBatch1Service.STATUS_READ);
        taskService.addTask(new LilliaFileBatchReadTask(lilliaFileBatchId));

        return new ResponseEntity<>(dbLilliaFileBatch, HttpStatus.OK);
    }
}
