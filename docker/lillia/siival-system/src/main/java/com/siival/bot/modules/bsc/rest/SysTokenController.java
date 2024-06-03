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
import com.siival.bot.modules.bsc.domain.SysToken;
import com.siival.bot.modules.bsc.service.SysTokenService;
import com.siival.bot.modules.bsc.service.dto.SysTokenQueryCriteria;
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
@Api(tags = "token管理管理")
@RequestMapping("/bsc/sysToken")
public class SysTokenController {

    private final SysTokenService sysTokenService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('sysToken:list')")
    public void exportSysToken(HttpServletResponse response, SysTokenQueryCriteria criteria) throws IOException {
        sysTokenService.download(sysTokenService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询token管理")
    @ApiOperation("查询token管理")
    @PreAuthorize("@el.check('sysToken:list')")
    public ResponseEntity<Object> querySysToken(SysTokenQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(sysTokenService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增token管理")
    @ApiOperation("新增token管理")
    @PreAuthorize("@el.check('sysToken:add')")
    public ResponseEntity<Object> createSysToken(@Validated @RequestBody SysToken resources){
        return new ResponseEntity<>(sysTokenService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改token管理")
    @ApiOperation("修改token管理")
    @PreAuthorize("@el.check('sysToken:edit')")
    public ResponseEntity<Object> updateSysToken(@Validated @RequestBody SysToken resources){
        sysTokenService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除token管理")
    @ApiOperation("删除token管理")
    @PreAuthorize("@el.check('sysToken:del')")
    public ResponseEntity<Object> deleteSysToken(@RequestBody Integer[] ids) {
        sysTokenService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}