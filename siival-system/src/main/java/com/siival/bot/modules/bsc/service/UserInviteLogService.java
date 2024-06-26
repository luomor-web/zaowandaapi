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
package com.siival.bot.modules.bsc.service;

import com.siival.bot.modules.api.req.BaseReq;
import com.siival.bot.modules.api.resp.UserInviteLogVo;
import com.siival.bot.modules.bsc.domain.UserInviteLog;
import com.siival.bot.modules.bsc.service.dto.UserInviteLogDto;
import com.siival.bot.modules.bsc.service.dto.UserInviteLogQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website
* @description 服务接口
* @author Mark
* @date 2022-03-29
**/
public interface UserInviteLogService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(UserInviteLogQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<UserInviteLogDto>
    */
    List<UserInviteLogDto> queryAll(UserInviteLogQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return UserInviteLogDto
     */
    UserInviteLogDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return UserInviteLogDto
    */
    UserInviteLogDto create(UserInviteLog resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(UserInviteLog resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Integer[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<UserInviteLogDto> all, HttpServletResponse response) throws IOException;

    List<UserInviteLogVo> findUserInviteLogVoByReq(BaseReq req);
}