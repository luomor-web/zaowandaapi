package com.siival.bot.modules.bsc.service.file;

import com.siival.bot.utils.BeanUtil;

public class FileServiceBuild {
    public static FileBaseService buildService(int uploadType) {
        if(uploadType == FileBaseService.UPLOAD_TYPE_1) {
            return BeanUtil.getBean(ExcelExam1Service.class);
        } else if(uploadType == FileBaseService.UPLOAD_TYPE_FINAL) {
            return BeanUtil.getBean(ExcelExamFinalService.class);
        } else {
            return BeanUtil.getBean(ExcelExam1Service.class);
        }
    }
}
