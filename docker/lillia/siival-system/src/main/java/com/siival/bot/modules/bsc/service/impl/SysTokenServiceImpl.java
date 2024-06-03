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

import com.siival.bot.modules.bsc.domain.SysToken;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.SysTokenRepository;
import com.siival.bot.modules.bsc.service.SysTokenService;
import com.siival.bot.modules.bsc.service.dto.SysTokenDto;
import com.siival.bot.modules.bsc.service.dto.SysTokenQueryCriteria;
import com.siival.bot.modules.bsc.service.mapstruct.SysTokenMapper;
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
public class SysTokenServiceImpl implements SysTokenService {

    private final SysTokenRepository sysTokenRepository;
    private final SysTokenMapper sysTokenMapper;

    @Override
    public Map<String,Object> queryAll(SysTokenQueryCriteria criteria, Pageable pageable){
        Page<SysToken> page = sysTokenRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(sysTokenMapper::toDto));
    }

    @Override
    public List<SysTokenDto> queryAll(SysTokenQueryCriteria criteria){
        return sysTokenMapper.toDto(sysTokenRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public SysTokenDto findById(Integer id) {
        SysToken sysToken = sysTokenRepository.findById(id).orElseGet(SysToken::new);
        ValidationUtil.isNull(sysToken.getId(),"SysToken","id",id);
        return sysTokenMapper.toDto(sysToken);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysTokenDto create(SysToken resources) {
        return sysTokenMapper.toDto(sysTokenRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysToken resources) {
        SysToken sysToken = sysTokenRepository.findById(resources.getId()).orElseGet(SysToken::new);
        ValidationUtil.isNull( sysToken.getId(),"SysToken","id",resources.getId());
        sysToken.copy(resources);
        sysTokenRepository.save(sysToken);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            sysTokenRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<SysTokenDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SysTokenDto sysToken : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("token值", sysToken.getToken());
            map.put("每天最大请求次数", sysToken.getDayMaxLimit());
            map.put("并发限制次数", sysToken.getThreadLimit());
            map.put("1-启用,0-禁用", sysToken.getStatus());
            map.put("备注", sysToken.getRemark());
            map.put("创建时间", sysToken.getCreateTime());
            map.put("更新时间", sysToken.getUpdateTime());
            map.put("绑定的微信小程序id", sysToken.getWxAppId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}