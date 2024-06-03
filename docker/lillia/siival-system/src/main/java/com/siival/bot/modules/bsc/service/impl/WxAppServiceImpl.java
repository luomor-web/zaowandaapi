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

import com.siival.bot.modules.bsc.domain.WxApp;
import com.siival.bot.exception.EntityExistException ;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.WxAppRepository;
import com.siival.bot.modules.bsc.service.WxAppService;
import com.siival.bot.modules.bsc.service.dto.WxAppDto;
import com.siival.bot.modules.bsc.service.dto.WxAppQueryCriteria;
import com.siival.bot.modules.bsc.service.mapstruct.WxAppMapper;
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
public class WxAppServiceImpl implements WxAppService {

    private final WxAppRepository wxAppRepository;
    private final WxAppMapper wxAppMapper;

    @Override
    public Map<String,Object> queryAll(WxAppQueryCriteria criteria, Pageable pageable){
        Page<WxApp> page = wxAppRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(wxAppMapper::toDto));
    }

    @Override
    public List<WxAppDto> queryAll(WxAppQueryCriteria criteria){
        return wxAppMapper.toDto(wxAppRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public WxAppDto findById(Integer id) {
        WxApp wxApp = wxAppRepository.findById(id).orElseGet(WxApp::new);
        ValidationUtil.isNull(wxApp.getId(),"WxApp","id",id);
        return wxAppMapper.toDto(wxApp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxAppDto create(WxApp resources) {
        if(wxAppRepository.findByAppId(resources.getAppId()) != null){
            throw new EntityExistException(WxApp.class,"app_id",resources.getAppId());
        }
        return wxAppMapper.toDto(wxAppRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(WxApp resources) {
        WxApp wxApp = wxAppRepository.findById(resources.getId()).orElseGet(WxApp::new);
        ValidationUtil.isNull( wxApp.getId(),"WxApp","id",resources.getId());
        WxApp wxApp1 = null;
        wxApp1 = wxAppRepository.findByAppId(resources.getAppId());
        if(wxApp1 != null && !wxApp1.getId().equals(wxApp.getId())){
            throw new EntityExistException(WxApp.class,"app_id",resources.getAppId());
        }
        wxApp.copy(resources);
        wxAppRepository.save(wxApp);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            wxAppRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<WxAppDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (WxAppDto wxApp : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("app id", wxApp.getAppId());
            map.put("app secret", wxApp.getAppSecret());
            map.put("token", wxApp.getAppToken());
            map.put("aes 加密key", wxApp.getAesKey());
            map.put("消息格式", wxApp.getMsgDataFormat());
            map.put("1-小程序,2-公众号,3-服务号", wxApp.getType());
            map.put("名称", wxApp.getName());
            map.put("备注", wxApp.getRemark());
            map.put("1-启用,0-禁用", wxApp.getStatus());
            map.put("创建时间", wxApp.getCreateTime());
            map.put("更新时间", wxApp.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}