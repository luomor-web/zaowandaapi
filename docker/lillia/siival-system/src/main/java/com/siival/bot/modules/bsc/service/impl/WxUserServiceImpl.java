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

import com.siival.bot.modules.bsc.domain.WxUser;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.WxUserRepository;
import com.siival.bot.modules.bsc.service.WxUserService;
import com.siival.bot.modules.bsc.service.dto.WxUserDto;
import com.siival.bot.modules.bsc.service.dto.WxUserQueryCriteria;
import com.siival.bot.modules.bsc.service.mapstruct.WxUserMapper;
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
public class WxUserServiceImpl implements WxUserService {

    private final WxUserRepository wxUserRepository;
    private final WxUserMapper wxUserMapper;

    @Override
    public Map<String,Object> queryAll(WxUserQueryCriteria criteria, Pageable pageable){
        Page<WxUser> page = wxUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(wxUserMapper::toDto));
    }

    @Override
    public List<WxUserDto> queryAll(WxUserQueryCriteria criteria){
        return wxUserMapper.toDto(wxUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public WxUserDto findById(Integer id) {
        WxUser wxUser = wxUserRepository.findById(id).orElseGet(WxUser::new);
        ValidationUtil.isNull(wxUser.getId(),"WxUser","id",id);
        return wxUserMapper.toDto(wxUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxUserDto create(WxUser resources) {
        return wxUserMapper.toDto(wxUserRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(WxUser resources) {
        WxUser wxUser = wxUserRepository.findById(resources.getId()).orElseGet(WxUser::new);
        ValidationUtil.isNull( wxUser.getId(),"WxUser","id",resources.getId());
        wxUser.copy(resources);
        wxUserRepository.save(wxUser);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            wxUserRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<WxUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (WxUserDto wxUser : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("昵称", wxUser.getNickName());
            map.put("头像地址", wxUser.getAvatarUrl());
            map.put("国家", wxUser.getCountry());
            map.put("性别", wxUser.getGender());
            map.put("语言", wxUser.getLanguage());
            map.put("省份", wxUser.getProvince());
            map.put("unionid", wxUser.getUnionid());
            map.put("openid", wxUser.getOpenid());
            map.put("session key", wxUser.getSessionKey());
            map.put("1-启用,0-禁用", wxUser.getStatus());
            map.put("创建时间", wxUser.getCreateTime());
            map.put("更新时间", wxUser.getUpdateTime());
            map.put("小程序appid", wxUser.getAppId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}