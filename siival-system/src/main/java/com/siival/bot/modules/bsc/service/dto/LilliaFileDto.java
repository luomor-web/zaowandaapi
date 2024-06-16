/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package com.siival.bot.modules.bsc.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @website
* @description /
* @author Peter
* @date 2024-06-16
**/
@Data
public class LilliaFileDto implements Serializable {

    private Long lilliaFileId;

    private Long lilliaFileBatchId;

    private String fileName;

    private Integer fileType;

    private String filePath;

    private String localPath;

    private String fileSize;

    private Integer fileCtime;

    private Integer fileCdate;

    private Integer fileUtime;

    private String readRet;

    private Integer status;

    private Integer readStatus;

    private Long flag;

    private Integer operatorId;

    private String operator;

    private String comment;

    private Integer createTime;

    private Integer updateTime;
}