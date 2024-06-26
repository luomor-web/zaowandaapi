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
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website
* @description /
* @author Peter
* @date 2024-06-03
**/
@Data
public class UserExportLogDto implements Serializable {

    private Integer id;

    /** 用户id */
    private Integer uid;

    /** 消耗积分 */
    private Integer integral;

    /** 题库id */
    private Integer pid;

    /** 邮箱 */
    private String email;

    /** 0-用户提交, 1-导出成功, 2-导出失败 */
    private Integer status;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 1-题库  2-试卷 */
    private Integer exportType;

    /** 试卷id */
    private Integer examId;

    /** 发送邮件附件地址pdf用到 */
    private String filePath;

    /** 0-完整题库 1-模拟考试试卷 */
    private Integer questionType;

    /** 模拟考试试卷题目数量 */
    private Integer questionNumber;
}