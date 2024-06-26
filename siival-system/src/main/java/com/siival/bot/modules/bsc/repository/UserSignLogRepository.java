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
package com.siival.bot.modules.bsc.repository;

import com.siival.bot.modules.bsc.domain.UserSignLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
* @website
* @author mark
* @date 2022-02-21
**/
public interface UserSignLogRepository extends JpaRepository<UserSignLog, Integer>, JpaSpecificationExecutor<UserSignLog> {

    @Query(value = "select usl from UserSignLog usl where usl.user.id=?1 and usl.createTime>=?2 order by id asc ")
    List<UserSignLog> findUserSignLogInfo(Integer uid, Date startDay);
}