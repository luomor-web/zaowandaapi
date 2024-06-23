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
* @date 2024-06-23
**/
@Data
public class LilliaFileBatchDto implements Serializable {

    private Long lilliaFileBatchId;

    private String uploadName;

    /** uploadType */
    private Integer uploadType;

    private Integer num;

    private Integer uploadTotalNum;

    private Integer uploadSuccessNum;

    private Integer uploadFailNum;

    private Integer uploadRemoveNum;

    private Integer unUploadNum;

    private Integer readNum;

    private String readRet;

    private Integer status;

    private Integer readStatus;

    private Integer operatorId;

    /** operator  */
    private String operator;

    private String comment;

    private Integer createTime;

    private Integer updateTime;

    private Integer questionMenuId;
}