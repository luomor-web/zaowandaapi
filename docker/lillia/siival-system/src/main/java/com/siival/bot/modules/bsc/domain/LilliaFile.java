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
* @date 2024-06-16
**/
@Entity
@Data
@Table(name="lillia_file")
public class LilliaFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lillia_file_id")
    @ApiModelProperty(value = "lilliaFileId")
    private Long lilliaFileId;

    @Column(name = "lillia_file_batch_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = "lilliaFileBatchId")
    private Long lilliaFileBatchId;

    @Column(name = "file_name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "fileName")
    private String fileName;

    @Column(name = "file_type",nullable = false)
    @NotNull
    @ApiModelProperty(value = "fileType")
    private Integer fileType;

    @Column(name = "file_path",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "filePath")
    private String filePath;

    @Column(name = "local_path",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "localPath")
    private String localPath;

    @Column(name = "file_size",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "fileSize")
    private String fileSize;

    @Column(name = "file_ctime",nullable = false)
    @NotNull
    @ApiModelProperty(value = "fileCtime")
    private Integer fileCtime;

    @Column(name = "file_cdate",nullable = false)
    @NotNull
    @ApiModelProperty(value = "fileCdate")
    private Integer fileCdate;

    @Column(name = "file_utime",nullable = false)
    @NotNull
    @ApiModelProperty(value = "fileUtime")
    private Integer fileUtime;

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

    @Column(name = "flag",nullable = false)
    @NotNull
    @ApiModelProperty(value = "flag")
    private Long flag;

    @Column(name = "operator_id",nullable = false)
    @NotNull
    @ApiModelProperty(value = "operatorId")
    private Integer operatorId;

    @Column(name = "operator",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "operator")
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

    public void copy(LilliaFile source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}