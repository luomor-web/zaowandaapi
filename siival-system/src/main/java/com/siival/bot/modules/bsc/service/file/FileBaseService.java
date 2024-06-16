package com.siival.bot.modules.bsc.service.file;

import java.io.File;

public interface FileBaseService {
    public static final int UPLOAD_TYPE_1 = 1;
    public static final int UPLOAD_TYPE_2 = 2;

    public void read(File localFile) throws Exception;
}

