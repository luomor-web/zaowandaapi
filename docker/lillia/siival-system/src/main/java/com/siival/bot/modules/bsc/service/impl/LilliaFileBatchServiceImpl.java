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
package com.siival.bot.modules.bsc.service.impl;

import com.siival.bot.modules.bsc.domain.LilliaFileBatch;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.LilliaFileBatchRepository;
import com.siival.bot.modules.bsc.service.LilliaFileBatchService;
import com.siival.bot.modules.bsc.service.dto.LilliaFileBatchDto;
import com.siival.bot.modules.bsc.service.dto.LilliaFileBatchQueryCriteria;
import com.siival.bot.modules.bsc.service.mapstruct.LilliaFileBatchMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website
* @description 服务实现
* @author Peter
* @date 2024-06-23
**/
@Service
@RequiredArgsConstructor
public class LilliaFileBatchServiceImpl implements LilliaFileBatchService {

    private final LilliaFileBatchRepository lilliaFileBatchRepository;
    private final LilliaFileBatchMapper lilliaFileBatchMapper;

    @Override
    public Map<String,Object> queryAll(LilliaFileBatchQueryCriteria criteria, Pageable pageable){
        Page<LilliaFileBatch> page = lilliaFileBatchRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(lilliaFileBatchMapper::toDto));
    }

    @Override
    public List<LilliaFileBatchDto> queryAll(LilliaFileBatchQueryCriteria criteria){
        return lilliaFileBatchMapper.toDto(lilliaFileBatchRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public LilliaFileBatchDto findById(Long lilliaFileBatchId) {
        LilliaFileBatch lilliaFileBatch = lilliaFileBatchRepository.findById(lilliaFileBatchId).orElseGet(LilliaFileBatch::new);
        ValidationUtil.isNull(lilliaFileBatch.getLilliaFileBatchId(),"LilliaFileBatch","lilliaFileBatchId",lilliaFileBatchId);
        return lilliaFileBatchMapper.toDto(lilliaFileBatch);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LilliaFileBatchDto create(LilliaFileBatch resources) {
        return lilliaFileBatchMapper.toDto(lilliaFileBatchRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LilliaFileBatch resources) {
        LilliaFileBatch lilliaFileBatch = lilliaFileBatchRepository.findById(resources.getLilliaFileBatchId()).orElseGet(LilliaFileBatch::new);
        ValidationUtil.isNull( lilliaFileBatch.getLilliaFileBatchId(),"LilliaFileBatch","id",resources.getLilliaFileBatchId());
        lilliaFileBatch.copy(resources);
        lilliaFileBatchRepository.save(lilliaFileBatch);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long lilliaFileBatchId : ids) {
            lilliaFileBatchRepository.deleteById(lilliaFileBatchId);
        }
    }

    @Override
    public void download(List<LilliaFileBatchDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LilliaFileBatchDto lilliaFileBatch : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" uploadName",  lilliaFileBatch.getUploadName());
            map.put("uploadType", lilliaFileBatch.getUploadType());
            map.put(" num",  lilliaFileBatch.getNum());
            map.put(" uploadTotalNum",  lilliaFileBatch.getUploadTotalNum());
            map.put(" uploadSuccessNum",  lilliaFileBatch.getUploadSuccessNum());
            map.put(" uploadFailNum",  lilliaFileBatch.getUploadFailNum());
            map.put(" uploadRemoveNum",  lilliaFileBatch.getUploadRemoveNum());
            map.put(" unUploadNum",  lilliaFileBatch.getUnUploadNum());
            map.put(" readNum",  lilliaFileBatch.getReadNum());
            map.put(" readRet",  lilliaFileBatch.getReadRet());
            map.put(" status",  lilliaFileBatch.getStatus());
            map.put(" readStatus",  lilliaFileBatch.getReadStatus());
            map.put(" operatorId",  lilliaFileBatch.getOperatorId());
            map.put("operator ", lilliaFileBatch.getOperator());
            map.put(" comment",  lilliaFileBatch.getComment());
            map.put(" createTime",  lilliaFileBatch.getCreateTime());
            map.put(" updateTime",  lilliaFileBatch.getUpdateTime());
            map.put(" questionMenuId",  lilliaFileBatch.getQuestionMenuId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}