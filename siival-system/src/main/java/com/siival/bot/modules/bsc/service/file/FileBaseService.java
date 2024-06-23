package com.siival.bot.modules.bsc.service.file;

import java.io.File;

import com.siival.bot.modules.bsc.service.dto.LilliaFileBatchDto;

public interface FileBaseService {
    public static final int UPLOAD_TYPE_1 = 1;
    public static final int UPLOAD_TYPE_FINAL = 2;

    public void read(File localFile) throws Exception;

    public void read(File localFile, LilliaFileBatchDto lilliaFileBatchDto) throws Exception;
}

