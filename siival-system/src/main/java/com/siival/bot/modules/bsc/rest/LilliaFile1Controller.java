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
import com.siival.bot.modules.bsc.domain.LilliaFile;
import com.siival.bot.modules.bsc.service.LilliaFileService;
import com.siival.bot.modules.bsc.service.dto.LilliaFileDto;
import com.siival.bot.modules.bsc.service.dto.LilliaFileQueryCriteria;
import com.siival.bot.resp.R;
import com.siival.bot.utils.JacksonUtil;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
* @website
* @author Peter
* @date 2024-06-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "文件管理")
@RequestMapping("/bsc/lillia-file")
public class LilliaFile1Controller {

    private final LilliaFileService lilliaFileService;

    @PreAuthorize("@el.check('lilliaFile:list')")
    @Log("添加批次上传")
    @ApiOperation("添加批次上传")
    @PostMapping("/addWithBatch")
    public ResponseEntity<Object> addWithBatch(@RequestBody String body) {
        Map<String, String> data = JacksonUtil.toMap(body);
        if(data == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LilliaFileDto lilliaFile = lilliaFileService.addWithBatch(data);
        return new ResponseEntity<>(lilliaFile, HttpStatus.CREATED);
    }

    @PreAuthorize("@el.check('lilliaFile:list')")
    @Log("删除批次上传")
    @ApiOperation("删除批次上传")
    @PostMapping("/deleteWithBatch")
    public ResponseEntity<Object> deleteWithBatch(@RequestBody String body) {
        Map<String, String> data = JacksonUtil.toMap(body);
        if(data == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LilliaFileDto lilliaFile = lilliaFileService.deleteWithBatch(data);
        return new ResponseEntity<>(lilliaFile, HttpStatus.CREATED);
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('lilliaFile:list')")
    public void exportLilliaFile(HttpServletResponse response, LilliaFileQueryCriteria criteria) throws IOException {
        lilliaFileService.download(lilliaFileService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询文件")
    @ApiOperation("查询文件")
    @PreAuthorize("@el.check('lilliaFile:list')")
    public ResponseEntity<Object> queryLilliaFile(LilliaFileQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(lilliaFileService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增文件")
    @ApiOperation("新增文件")
    @PreAuthorize("@el.check('lilliaFile:add')")
    public ResponseEntity<Object> createLilliaFile(@Validated @RequestBody LilliaFile resources){
        return new ResponseEntity<>(lilliaFileService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改文件")
    @ApiOperation("修改文件")
    @PreAuthorize("@el.check('lilliaFile:edit')")
    public ResponseEntity<Object> updateLilliaFile(@Validated @RequestBody LilliaFile resources){
        lilliaFileService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除文件")
    @ApiOperation("删除文件")
    @PreAuthorize("@el.check('lilliaFile:del')")
    public ResponseEntity<Object> deleteLilliaFile(@RequestBody Long[] ids) {
        lilliaFileService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}