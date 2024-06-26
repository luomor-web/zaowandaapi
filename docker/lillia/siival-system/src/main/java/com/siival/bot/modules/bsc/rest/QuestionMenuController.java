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
package com.siival.bot.modules.bsc.rest;

import com.siival.bot.annotation.Log;
import com.siival.bot.modules.bsc.domain.QuestionMenu;
import com.siival.bot.modules.bsc.service.QuestionMenuService;
import com.siival.bot.modules.bsc.service.dto.QuestionMenuQueryCriteria;
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
* @date 2024-06-26
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "题目分类管理")
@RequestMapping("/bsc/questionMenu")
public class QuestionMenuController {

    private final QuestionMenuService questionMenuService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('questionMenu:list')")
    public void exportQuestionMenu(HttpServletResponse response, QuestionMenuQueryCriteria criteria) throws IOException {
        questionMenuService.download(questionMenuService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询题目分类")
    @ApiOperation("查询题目分类")
    @PreAuthorize("@el.check('questionMenu:list')")
    public ResponseEntity<Object> queryQuestionMenu(QuestionMenuQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(questionMenuService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增题目分类")
    @ApiOperation("新增题目分类")
    @PreAuthorize("@el.check('questionMenu:add')")
    public ResponseEntity<Object> createQuestionMenu(@Validated @RequestBody QuestionMenu resources){
        return new ResponseEntity<>(questionMenuService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改题目分类")
    @ApiOperation("修改题目分类")
    @PreAuthorize("@el.check('questionMenu:edit')")
    public ResponseEntity<Object> updateQuestionMenu(@Validated @RequestBody QuestionMenu resources){
        questionMenuService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除题目分类")
    @ApiOperation("删除题目分类")
    @PreAuthorize("@el.check('questionMenu:del')")
    public ResponseEntity<Object> deleteQuestionMenu(@RequestBody Integer[] ids) {
        questionMenuService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}