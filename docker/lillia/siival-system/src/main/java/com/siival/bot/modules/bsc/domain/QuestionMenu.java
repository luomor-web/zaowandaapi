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
@Table(name="question_menu")
public class QuestionMenu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private Integer id;

    @Column(name = "name")
    @ApiModelProperty(value = "昵称")
    private String name;

    @Column(name = "status",nullable = false)
    @NotNull
    @ApiModelProperty(value = "1-启用,0-禁用")
    private Integer status;

    @Column(name = "create_time",nullable = false)
    @NotNull
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    @Column(name = "pid",nullable = false)
    @NotNull
    @ApiModelProperty(value = "上级标签")
    private Integer pid;

    @Column(name = "category")
    @ApiModelProperty(value = "href")
    private String category;

    @Column(name = "sort",nullable = false)
    @NotNull
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    public void copy(QuestionMenu source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}