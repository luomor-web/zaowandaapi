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

import com.siival.bot.modules.bsc.domain.QuestionInfo;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.QuestionInfoRepository;
import com.siival.bot.modules.bsc.service.QuestionInfoService;
import com.siival.bot.modules.bsc.service.dto.QuestionInfoDto;
import com.siival.bot.modules.bsc.service.dto.QuestionInfoQueryCriteria;
import com.siival.bot.modules.bsc.service.mapstruct.QuestionInfoMapper;
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
public class QuestionInfoServiceImpl implements QuestionInfoService {

    private final QuestionInfoRepository questionInfoRepository;
    private final QuestionInfoMapper questionInfoMapper;

    @Override
    public Map<String,Object> queryAll(QuestionInfoQueryCriteria criteria, Pageable pageable){
        Page<QuestionInfo> page = questionInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(questionInfoMapper::toDto));
    }

    @Override
    public List<QuestionInfoDto> queryAll(QuestionInfoQueryCriteria criteria){
        return questionInfoMapper.toDto(questionInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public QuestionInfoDto findById(Integer id) {
        QuestionInfo questionInfo = questionInfoRepository.findById(id).orElseGet(QuestionInfo::new);
        ValidationUtil.isNull(questionInfo.getId(),"QuestionInfo","id",id);
        return questionInfoMapper.toDto(questionInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuestionInfoDto create(QuestionInfo resources) {
        return questionInfoMapper.toDto(questionInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QuestionInfo resources) {
        QuestionInfo questionInfo = questionInfoRepository.findById(resources.getId()).orElseGet(QuestionInfo::new);
        ValidationUtil.isNull( questionInfo.getId(),"QuestionInfo","id",resources.getId());
        questionInfo.copy(resources);
        questionInfoRepository.save(questionInfo);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            questionInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<QuestionInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (QuestionInfoDto questionInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("1-选择题, 2-简答", questionInfo.getType());
            map.put("0-单选, 1-多选", questionInfo.getMultiply());
            map.put("上级分类", questionInfo.getPid());
            map.put("创建时间", questionInfo.getCreateTime());
            map.put("更新时间", questionInfo.getUpdateTime());
            map.put("答案解析", questionInfo.getAnalysis());
            map.put("1-启用, 0 -禁用", questionInfo.getStatus());
            map.put("答案", questionInfo.getRightAnswer());
            map.put("问题", questionInfo.getQuestion());
            map.put("选项列表", questionInfo.getSelectList());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}