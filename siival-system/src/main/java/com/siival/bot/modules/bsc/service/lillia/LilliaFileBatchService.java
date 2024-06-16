package com.siival.bot.modules.bsc.service.lillia;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.siival.bot.modules.bsc.service.mapstruct.LilliaFileBatchMapper;

@Service
public class LilliaFileBatchService extends LilliaBaseService {
    public static final Integer READ_STATUS_INIT = 0; // init
    public static final Integer READ_STATUS_ALL_DONE = 1; // 获取excel信息
    public static final Integer READ_STATUS_PART_DONE = 2; // 部分获取excel信息
    public static final Integer READ_STATUS_NO_EXCEL = 3; // 没有excel信息

    private final Log logger = LogFactory.getLog(LilliaFileBatchService.class);
}
