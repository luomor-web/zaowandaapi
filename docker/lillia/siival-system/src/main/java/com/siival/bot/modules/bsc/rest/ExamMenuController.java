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
package com.siival.bot.modules.bsc.rest;

import com.siival.bot.annotation.Log;
import com.siival.bot.modules.bsc.domain.ExamMenu;
import com.siival.bot.modules.bsc.service.ExamMenuService;
import com.siival.bot.modules.bsc.service.dto.ExamMenuQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website
* @author Peter
* @date 2024-06-03
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "试卷分类管理")
@RequestMapping("/bsc/examMenu")
public class ExamMenuController {

    private final ExamMenuService examMenuService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('examMenu:list')")
    public void exportExamMenu(HttpServletResponse response, ExamMenuQueryCriteria criteria) throws IOException {
        examMenuService.download(examMenuService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询试卷分类")
    @ApiOperation("查询试卷分类")
    @PreAuthorize("@el.check('examMenu:list')")
    public ResponseEntity<Object> queryExamMenu(ExamMenuQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(examMenuService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增试卷分类")
    @ApiOperation("新增试卷分类")
    @PreAuthorize("@el.check('examMenu:add')")
    public ResponseEntity<Object> createExamMenu(@Validated @RequestBody ExamMenu resources){
        return new ResponseEntity<>(examMenuService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改试卷分类")
    @ApiOperation("修改试卷分类")
    @PreAuthorize("@el.check('examMenu:edit')")
    public ResponseEntity<Object> updateExamMenu(@Validated @RequestBody ExamMenu resources){
        examMenuService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除试卷分类")
    @ApiOperation("删除试卷分类")
    @PreAuthorize("@el.check('examMenu:del')")
    public ResponseEntity<Object> deleteExamMenu(@RequestBody Integer[] ids) {
        examMenuService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}