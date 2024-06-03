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
@Table(name="question_today_answer")
public class QuestionTodayAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private Integer id;

    @Column(name = "qid",nullable = false)
    @NotNull
    @ApiModelProperty(value = "题目id")
    private Integer qid;

    @Column(name = "integral")
    @ApiModelProperty(value = "获得积分")
    private Integer integral;

    @Column(name = "uid",nullable = false)
    @NotNull
    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @Column(name = "user_answer",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "用户答案")
    private String userAnswer;

    @Column(name = "status")
    @ApiModelProperty(value = "0-答错, 1-答对")
    private Integer status;

    @Column(name = "create_time",nullable = false)
    @NotNull
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    public void copy(QuestionTodayAnswer source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}