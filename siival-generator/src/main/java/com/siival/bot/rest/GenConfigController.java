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
package com.siival.bot.rest;

import com.siival.bot.domain.GenConfig;
import com.siival.bot.service.GenConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Peter Zhang
 * @date 2019-01-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bsc/genConfig")
@Api(tags = "系统：代码生成器配置管理")
public class GenConfigController {

    private final GenConfigService genConfigService;

    @ApiOperation("查询")
    @GetMapping(value = "/{tableName}")
    public ResponseEntity<Object> queryGenConfig(@PathVariable String tableName){
        return new ResponseEntity<>(genConfigService.find(tableName), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation("修改")
    public ResponseEntity<Object> updateGenConfig(@Validated @RequestBody GenConfig genConfig){
        return new ResponseEntity<>(genConfigService.update(genConfig.getTableName(), genConfig),HttpStatus.OK);
    }
}
