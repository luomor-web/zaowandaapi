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
@Table(name="question_info")
public class QuestionInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private Integer id;

    @Column(name = "type",nullable = false)
    @NotNull
    @ApiModelProperty(value = "1-选择题, 2-简答")
    private Integer type;

    @Column(name = "multiply")
    @ApiModelProperty(value = "0-单选, 1-多选")
    private Integer multiply;

    @Column(name = "pid")
    @ApiModelProperty(value = "上级分类")
    private Integer pid;

    @Column(name = "create_time",nullable = false)
    @NotNull
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    @Column(name = "analysis")
    @ApiModelProperty(value = "答案解析")
    private String analysis;

    @Column(name = "status",nullable = false)
    @NotNull
    @ApiModelProperty(value = "1-启用, 0 -禁用")
    private Integer status;

    @Column(name = "right_answer")
    @ApiModelProperty(value = "答案")
    private String rightAnswer;

    @Column(name = "question")
    @ApiModelProperty(value = "问题")
    private String question;

    @Column(name = "select_list")
    @ApiModelProperty(value = "选项列表")
    private unknowType selectList;

    public void copy(QuestionInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}