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
import com.siival.bot.modules.bsc.domain.LilliaFileBatch;
import com.siival.bot.modules.bsc.service.LilliaFileBatchService;
import com.siival.bot.modules.bsc.service.dto.LilliaFileBatchQueryCriteria;
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
* @date 2024-06-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "文件批次管理")
@RequestMapping("/bsc/lilliaFileBatch")
public class LilliaFileBatchController {

    private final LilliaFileBatchService lilliaFileBatchService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('lilliaFileBatch:list')")
    public void exportLilliaFileBatch(HttpServletResponse response, LilliaFileBatchQueryCriteria criteria) throws IOException {
        lilliaFileBatchService.download(lilliaFileBatchService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询文件批次")
    @ApiOperation("查询文件批次")
    @PreAuthorize("@el.check('lilliaFileBatch:list')")
    public ResponseEntity<Object> queryLilliaFileBatch(LilliaFileBatchQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(lilliaFileBatchService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增文件批次")
    @ApiOperation("新增文件批次")
    @PreAuthorize("@el.check('lilliaFileBatch:add')")
    public ResponseEntity<Object> createLilliaFileBatch(@Validated @RequestBody LilliaFileBatch resources){
        return new ResponseEntity<>(lilliaFileBatchService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改文件批次")
    @ApiOperation("修改文件批次")
    @PreAuthorize("@el.check('lilliaFileBatch:edit')")
    public ResponseEntity<Object> updateLilliaFileBatch(@Validated @RequestBody LilliaFileBatch resources){
        lilliaFileBatchService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除文件批次")
    @ApiOperation("删除文件批次")
    @PreAuthorize("@el.check('lilliaFileBatch:del')")
    public ResponseEntity<Object> deleteLilliaFileBatch(@RequestBody Long[] ids) {
        lilliaFileBatchService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}