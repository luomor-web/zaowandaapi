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
package com.siival.bot.modules.bsc.service.impl;

import com.siival.bot.modules.bsc.domain.QuestionMenu;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.QuestionMenuRepository;
import com.siival.bot.modules.bsc.service.QuestionMenuService;
import com.siival.bot.modules.bsc.service.dto.QuestionMenuDto;
import com.siival.bot.modules.bsc.service.dto.QuestionMenuQueryCriteria;
import com.siival.bot.modules.bsc.service.mapstruct.QuestionMenuMapper;
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
* @date 2024-06-26
**/
@Service
@RequiredArgsConstructor
public class QuestionMenuServiceImpl implements QuestionMenuService {

    private final QuestionMenuRepository questionMenuRepository;
    private final QuestionMenuMapper questionMenuMapper;

    @Override
    public Map<String,Object> queryAll(QuestionMenuQueryCriteria criteria, Pageable pageable){
        Page<QuestionMenu> page = questionMenuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(questionMenuMapper::toDto));
    }

    @Override
    public List<QuestionMenuDto> queryAll(QuestionMenuQueryCriteria criteria){
        return questionMenuMapper.toDto(questionMenuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public QuestionMenuDto findById(Integer id) {
        QuestionMenu questionMenu = questionMenuRepository.findById(id).orElseGet(QuestionMenu::new);
        ValidationUtil.isNull(questionMenu.getId(),"QuestionMenu","id",id);
        return questionMenuMapper.toDto(questionMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuestionMenuDto create(QuestionMenu resources) {
        return questionMenuMapper.toDto(questionMenuRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QuestionMenu resources) {
        QuestionMenu questionMenu = questionMenuRepository.findById(resources.getId()).orElseGet(QuestionMenu::new);
        ValidationUtil.isNull( questionMenu.getId(),"QuestionMenu","id",resources.getId());
        questionMenu.copy(resources);
        questionMenuRepository.save(questionMenu);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            questionMenuRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<QuestionMenuDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (QuestionMenuDto questionMenu : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("昵称", questionMenu.getName());
            map.put("1-启用,0-禁用", questionMenu.getStatus());
            map.put("创建时间", questionMenu.getCreateTime());
            map.put("更新时间", questionMenu.getUpdateTime());
            map.put("上级标签", questionMenu.getPid());
            map.put("href", questionMenu.getCategory());
            map.put("排序字段", questionMenu.getSort());
            map.put("chapter_id", questionMenu.getChapterId());
            map.put("chapter_ratio", questionMenu.getChapterRatio());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}