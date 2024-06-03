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

import com.siival.bot.modules.bsc.domain.ExamInfo;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.ExamInfoRepository;
import com.siival.bot.modules.bsc.service.ExamInfoService;
import com.siival.bot.modules.bsc.service.dto.ExamInfoDto;
import com.siival.bot.modules.bsc.service.dto.ExamInfoQueryCriteria;
import com.siival.bot.modules.bsc.service.mapstruct.ExamInfoMapper;
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
* @date 2024-06-03
**/
@Service
@RequiredArgsConstructor
public class ExamInfoServiceImpl implements ExamInfoService {

    private final ExamInfoRepository examInfoRepository;
    private final ExamInfoMapper examInfoMapper;

    @Override
    public Map<String,Object> queryAll(ExamInfoQueryCriteria criteria, Pageable pageable){
        Page<ExamInfo> page = examInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(examInfoMapper::toDto));
    }

    @Override
    public List<ExamInfoDto> queryAll(ExamInfoQueryCriteria criteria){
        return examInfoMapper.toDto(examInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ExamInfoDto findById(Integer id) {
        ExamInfo examInfo = examInfoRepository.findById(id).orElseGet(ExamInfo::new);
        ValidationUtil.isNull(examInfo.getId(),"ExamInfo","id",id);
        return examInfoMapper.toDto(examInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamInfoDto create(ExamInfo resources) {
        return examInfoMapper.toDto(examInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ExamInfo resources) {
        ExamInfo examInfo = examInfoRepository.findById(resources.getId()).orElseGet(ExamInfo::new);
        ValidationUtil.isNull( examInfo.getId(),"ExamInfo","id",resources.getId());
        examInfo.copy(resources);
        examInfoRepository.save(examInfo);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            examInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<ExamInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ExamInfoDto examInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" examName",  examInfo.getExamName());
            map.put("0-禁用,1-启用", examInfo.getStatus());
            map.put("创建时间", examInfo.getCreateTime());
            map.put("更新时间", examInfo.getUpdateTime());
            map.put("filelabel", examInfo.getFileLabel());
            map.put("file 大小", examInfo.getFileSize());
            map.put("教材版本version", examInfo.getVersion());
            map.put("pid", examInfo.getPid());
            map.put("临时表id用户校验数据完整性", examInfo.getTempId());
            map.put("文件类型", examInfo.getFileType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}