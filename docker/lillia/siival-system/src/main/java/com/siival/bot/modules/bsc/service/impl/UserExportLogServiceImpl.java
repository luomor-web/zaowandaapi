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

import com.siival.bot.modules.bsc.domain.UserExportLog;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.UserExportLogRepository;
import com.siival.bot.modules.bsc.service.UserExportLogService;
import com.siival.bot.modules.bsc.service.dto.UserExportLogDto;
import com.siival.bot.modules.bsc.service.dto.UserExportLogQueryCriteria;
import com.siival.bot.modules.bsc.service.mapstruct.UserExportLogMapper;
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
public class UserExportLogServiceImpl implements UserExportLogService {

    private final UserExportLogRepository userExportLogRepository;
    private final UserExportLogMapper userExportLogMapper;

    @Override
    public Map<String,Object> queryAll(UserExportLogQueryCriteria criteria, Pageable pageable){
        Page<UserExportLog> page = userExportLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(userExportLogMapper::toDto));
    }

    @Override
    public List<UserExportLogDto> queryAll(UserExportLogQueryCriteria criteria){
        return userExportLogMapper.toDto(userExportLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public UserExportLogDto findById(Integer id) {
        UserExportLog userExportLog = userExportLogRepository.findById(id).orElseGet(UserExportLog::new);
        ValidationUtil.isNull(userExportLog.getId(),"UserExportLog","id",id);
        return userExportLogMapper.toDto(userExportLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserExportLogDto create(UserExportLog resources) {
        return userExportLogMapper.toDto(userExportLogRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserExportLog resources) {
        UserExportLog userExportLog = userExportLogRepository.findById(resources.getId()).orElseGet(UserExportLog::new);
        ValidationUtil.isNull( userExportLog.getId(),"UserExportLog","id",resources.getId());
        userExportLog.copy(resources);
        userExportLogRepository.save(userExportLog);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            userExportLogRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<UserExportLogDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserExportLogDto userExportLog : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户id", userExportLog.getUid());
            map.put("消耗积分", userExportLog.getIntegral());
            map.put("题库id", userExportLog.getPid());
            map.put("邮箱", userExportLog.getEmail());
            map.put("0-用户提交, 1-导出成功, 2-导出失败", userExportLog.getStatus());
            map.put("创建时间", userExportLog.getCreateTime());
            map.put("更新时间", userExportLog.getUpdateTime());
            map.put("1-题库  2-试卷", userExportLog.getExportType());
            map.put("试卷id", userExportLog.getExamId());
            map.put("发送邮件附件地址pdf用到", userExportLog.getFilePath());
            map.put("0-完整题库 1-模拟考试试卷", userExportLog.getQuestionType());
            map.put("模拟考试试卷题目数量", userExportLog.getQuestionNumber());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}