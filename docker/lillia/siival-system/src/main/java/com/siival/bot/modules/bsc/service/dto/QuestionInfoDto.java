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
public class QuestionInfoDto implements Serializable {

    private Integer id;

    /** 1-选择题, 2-简答 */
    private Integer type;

    /** 0-单选, 1-多选 */
    private Integer multiply;

    /** 上级分类 */
    private Integer pid;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 答案解析 */
    private String analysis;

    /** 1-启用, 0 -禁用 */
    private Integer status;

    /** 答案 */
    private String rightAnswer;

    /** 问题 */
    private String question;

    /** 选项列表 */
    private unknowType selectList;
}