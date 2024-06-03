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

import com.siival.bot.modules.bsc.domain.UserCorrectQuestion;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.UserCorrectQuestionRepository;
import com.siival.bot.modules.bsc.service.UserCorrectQuestionService;
import com.siival.bot.modules.bsc.service.dto.UserCorrectQuestionDto;
import com.siival.bot.modules.bsc.service.dto.UserCorrectQuestionQueryCriteria;
import com.siival.bot.modules.bsc.service.mapstruct.UserCorrectQuestionMapper;
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
public class UserCorrectQuestionServiceImpl implements UserCorrectQuestionService {

    private final UserCorrectQuestionRepository userCorrectQuestionRepository;
    private final UserCorrectQuestionMapper userCorrectQuestionMapper;

    @Override
    public Map<String,Object> queryAll(UserCorrectQuestionQueryCriteria criteria, Pageable pageable){
        Page<UserCorrectQuestion> page = userCorrectQuestionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(userCorrectQuestionMapper::toDto));
    }

    @Override
    public List<UserCorrectQuestionDto> queryAll(UserCorrectQuestionQueryCriteria criteria){
        return userCorrectQuestionMapper.toDto(userCorrectQuestionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public UserCorrectQuestionDto findById(Integer id) {
        UserCorrectQuestion userCorrectQuestion = userCorrectQuestionRepository.findById(id).orElseGet(UserCorrectQuestion::new);
        ValidationUtil.isNull(userCorrectQuestion.getId(),"UserCorrectQuestion","id",id);
        return userCorrectQuestionMapper.toDto(userCorrectQuestion);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserCorrectQuestionDto create(UserCorrectQuestion resources) {
        return userCorrectQuestionMapper.toDto(userCorrectQuestionRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserCorrectQuestion resources) {
        UserCorrectQuestion userCorrectQuestion = userCorrectQuestionRepository.findById(resources.getId()).orElseGet(UserCorrectQuestion::new);
        ValidationUtil.isNull( userCorrectQuestion.getId(),"UserCorrectQuestion","id",resources.getId());
        userCorrectQuestion.copy(resources);
        userCorrectQuestionRepository.save(userCorrectQuestion);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            userCorrectQuestionRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<UserCorrectQuestionDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserCorrectQuestionDto userCorrectQuestion : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("题库分类id", userCorrectQuestion.getPid());
            map.put("问题id", userCorrectQuestion.getQuestionId());
            map.put("用户id", userCorrectQuestion.getUid());
            map.put("纠错原因", userCorrectQuestion.getReason());
            map.put("1-用户提交纠错,2-管理员审核通过,3-审核不通过", userCorrectQuestion.getStatus());
            map.put("审核不通过原因", userCorrectQuestion.getCheckReason());
            map.put("管理员用户id", userCorrectQuestion.getCheckUid());
            map.put("创建时间", userCorrectQuestion.getCheckTime());
            map.put("创建时间", userCorrectQuestion.getCreateTime());
            map.put("更新时间", userCorrectQuestion.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}