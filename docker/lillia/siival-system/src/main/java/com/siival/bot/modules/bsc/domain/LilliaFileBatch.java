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
import java.io.Serializable;

/**
* @website
* @description /
* @author Peter
* @date 2024-06-23
**/
@Entity
@Data
@Table(name="lillia_file_batch")
public class LilliaFileBatch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lillia_file_batch_id")
    @ApiModelProperty(value = "lilliaFileBatchId")
    private Long lilliaFileBatchId;

    @Column(name = "upload_name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "uploadName")
    private String uploadName;

    @Column(name = "upload_type",nullable = false)
    @NotNull
    @ApiModelProperty(value = "uploadType")
    private Integer uploadType;

    @Column(name = "num",nullable = false)
    @NotNull
    @ApiModelProperty(value = "num")
    private Integer num;

    @Column(name = "upload_total_num",nullable = false)
    @NotNull
    @ApiModelProperty(value = "uploadTotalNum")
    private Integer uploadTotalNum;

    @Column(name = "upload_success_num",nullable = false)
    @NotNull
    @ApiModelProperty(value = "uploadSuccessNum")
    private Integer uploadSuccessNum;

    @Column(name = "upload_fail_num",nullable = false)
    @NotNull
    @ApiModelProperty(value = "uploadFailNum")
    private Integer uploadFailNum;

    @Column(name = "upload_remove_num",nullable = false)
    @NotNull
    @ApiModelProperty(value = "uploadRemoveNum")
    private Integer uploadRemoveNum;

    @Column(name = "un_upload_num",nullable = false)
    @NotNull
    @ApiModelProperty(value = "unUploadNum")
    private Integer unUploadNum;

    @Column(name = "read_num",nullable = false)
    @NotNull
    @ApiModelProperty(value = "readNum")
    private Integer readNum;

    @Column(name = "read_ret",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "readRet")
    private String readRet;

    @Column(name = "status",nullable = false)
    @NotNull
    @ApiModelProperty(value = "status")
    private Integer status;

    @Column(name = "read_status",nullable = false)
    @NotNull
    @ApiModelProperty(value = "readStatus")
    private Integer readStatus;

    @Column(name = "operator_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = "operatorId")
    private Integer operatorId;

    @Column(name = "operator",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "operator ")
    private String operator;

    @Column(name = "comment",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "comment")
    private String comment;

    @Column(name = "create_time",nullable = false)
    @NotNull
    @ApiModelProperty(value = "createTime")
    private Integer createTime;

    @Column(name = "update_time",nullable = false)
    @NotNull
    @ApiModelProperty(value = "updateTime")
    private Integer updateTime;

    @Column(name = "question_menu_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = "questionMenuId")
    private Integer questionMenuId;

    public void copy(LilliaFileBatch source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}