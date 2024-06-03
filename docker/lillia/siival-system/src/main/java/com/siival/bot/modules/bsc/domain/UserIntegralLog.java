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
package com.siival.bot.modules.bsc.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website
* @description /
* @author Peter
* @date 2024-06-03
**/
@Entity
@Data
@Table(name="user_integral_log")
public class UserIntegralLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private Integer id;

    @Column(name = "uid",nullable = false)
    @NotNull
    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @Column(name = "integral")
    @ApiModelProperty(value = "积分")
    private Integer integral;

    @Column(name = "type")
    @ApiModelProperty(value = "1-增加,2-减少")
    private Integer type;

    @Column(name = "before_count")
    @ApiModelProperty(value = "操作前数量")
    private Integer beforeCount;

    @Column(name = "after_count")
    @ApiModelProperty(value = "操作后数量")
    private Integer afterCount;

    @Column(name = "remark")
    @ApiModelProperty(value = "remark")
    private String remark;

    @Column(name = "create_time",nullable = false)
    @NotNull
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    public void copy(UserIntegralLog source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}