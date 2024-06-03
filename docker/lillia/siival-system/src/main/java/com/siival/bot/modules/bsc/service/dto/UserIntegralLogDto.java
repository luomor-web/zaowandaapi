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
public class UserIntegralLogDto implements Serializable {

    private Integer id;

    /** 用户id */
    private Integer uid;

    /** 积分 */
    private Integer integral;

    /** 1-增加,2-减少 */
    private Integer type;

    /** 操作前数量 */
    private Integer beforeCount;

    /** 操作后数量 */
    private Integer afterCount;

    private String remark;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;
}