/*
*  Copyright 2019-2020 Peter Zhang
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
* @author mark
* @date 2022-01-17
**/
@Data
public class QuestionMenuDto implements Serializable {

    /** ID */
    private Integer id;

    /** 题目 */
    private String name;

    /** 是否启用 */
    private Integer status;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    private boolean hasChildren;

    private Integer pid;

    private Integer sort;

     /** chapter_id */
     private Integer chapterId;

     /** chapter_ratio */
     private Integer chapterRatio;
}