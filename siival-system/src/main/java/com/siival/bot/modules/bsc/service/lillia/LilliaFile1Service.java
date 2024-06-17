package com.siival.bot.modules.bsc.service.lillia;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.siival.bot.modules.bsc.service.mapstruct.LilliaFileBatchMapper;

@Service
public class LilliaFile1Service extends LilliaBaseService {
    public static final Long FLAG_NO_DATA = 0x01L; // 没有信息
    public static final Long FLAG_HAS_DATA = 0x02L; // 存在信息
    public static final Long FLAG_DATA = 0x04L; // 已读取信息

    public static final Integer READ_STATUS_NO_DATA = 1; // 没有信息
    public static final Integer READ_STATUS_HAS_DATA = 2; // 存在信息
    public static final Integer READ_STATUS_DATA = 3; // 已读取信息

    private final Log logger = LogFactory.getLog(LilliaFile1Service.class);
}
