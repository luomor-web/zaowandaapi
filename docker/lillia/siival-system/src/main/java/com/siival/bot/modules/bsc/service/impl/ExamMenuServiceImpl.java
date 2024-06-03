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

import com.siival.bot.modules.bsc.domain.ExamMenu;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.ExamMenuRepository;
import com.siival.bot.modules.bsc.service.ExamMenuService;
import com.siival.bot.modules.bsc.service.dto.ExamMenuDto;
import com.siival.bot.modules.bsc.service.dto.ExamMenuQueryCriteria;
import com.siival.bot.modules.bsc.service.mapstruct.ExamMenuMapper;
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
public class ExamMenuServiceImpl implements ExamMenuService {

    private final ExamMenuRepository examMenuRepository;
    private final ExamMenuMapper examMenuMapper;

    @Override
    public Map<String,Object> queryAll(ExamMenuQueryCriteria criteria, Pageable pageable){
        Page<ExamMenu> page = examMenuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(examMenuMapper::toDto));
    }

    @Override
    public List<ExamMenuDto> queryAll(ExamMenuQueryCriteria criteria){
        return examMenuMapper.toDto(examMenuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ExamMenuDto findById(Integer id) {
        ExamMenu examMenu = examMenuRepository.findById(id).orElseGet(ExamMenu::new);
        ValidationUtil.isNull(examMenu.getId(),"ExamMenu","id",id);
        return examMenuMapper.toDto(examMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamMenuDto create(ExamMenu resources) {
        return examMenuMapper.toDto(examMenuRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ExamMenu resources) {
        ExamMenu examMenu = examMenuRepository.findById(resources.getId()).orElseGet(ExamMenu::new);
        ValidationUtil.isNull( examMenu.getId(),"ExamMenu","id",resources.getId());
        examMenu.copy(resources);
        examMenuRepository.save(examMenu);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            examMenuRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<ExamMenuDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ExamMenuDto examMenu : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("菜单名称", examMenu.getName());
            map.put("1-启用,0-禁用", examMenu.getStatus());
            map.put("创建时间", examMenu.getCreateTime());
            map.put("更新时间", examMenu.getUpdateTime());
            map.put("上级标签", examMenu.getPid());
            map.put("排序字段", examMenu.getSort());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}