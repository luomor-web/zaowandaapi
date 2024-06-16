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

import com.siival.bot.modules.bsc.domain.LilliaFile;
import com.siival.bot.modules.bsc.domain.LilliaFileBatch;
import com.siival.bot.utils.FileUtil;
import com.siival.bot.utils.PageUtil;
import com.siival.bot.utils.QueryHelp;
import com.siival.bot.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import com.siival.bot.modules.bsc.repository.LilliaFileRepository;
import com.siival.bot.modules.bsc.service.LilliaFileService;
import com.siival.bot.modules.bsc.service.dto.LilliaFileDto;
import com.siival.bot.modules.bsc.service.dto.LilliaFileQueryCriteria;
import com.siival.bot.modules.bsc.service.file.FileBaseService;
import com.siival.bot.modules.bsc.service.lillia.LilliaBaseService;
import com.siival.bot.modules.bsc.service.mapstruct.LilliaFileMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/**
* @website
* @description 服务实现
* @author Peter
* @date 2024-06-16
**/
@Service
@RequiredArgsConstructor
public class LilliaFileServiceImpl implements LilliaFileService {

    private final com.siival.bot.modules.bsc.service.LilliaFileBatchService lilliaFileBatchService;

    @Override
    public LilliaFileDto addWithBatch(Map<String, String> data) {
        /*
        * lilliaFileBatchId: this.lilliaFileBatchId,
        fileName: file.name,
        fileSize: file.size,
        localPath: file.url,
        uploadSuccessNum: this.uploadSuccessNum,
        uploadTotalNum: this.uploadTotalNum,
        uploadFailNum: this.uploadFailNum,
        unUploadNum: this.unUploadNum*/
        Long lilliaFileBatchId = Long.valueOf(data.get("lilliaFileBatchId"));
        LilliaFile lilliaFile = new LilliaFile();
        lilliaFile.setLilliaFileBatchId(lilliaFileBatchId);
        String fileName = data.get("fileName");
        if(fileName.length() > 128) {
            fileName = fileName.substring(0, 128);
        }
        int time = LilliaBaseService.getSecondTimestamp(new Date());
        lilliaFile.setFileName(fileName);
        lilliaFile.setFileSize(data.get("fileSize"));
        lilliaFile.setFileType(Integer.valueOf(data.get("fileType")));
        lilliaFile.setLocalPath(data.get("localPath"));
        lilliaFile.setFilePath(data.get("filePath"));
        lilliaFile.setFileCtime(time);
        LilliaFileDto lilliaFileDto;
        lilliaFileDto = this.create(lilliaFile);
        // add read file task


        LilliaFileBatch lilliaFileBatch = new LilliaFileBatch();
        lilliaFileBatch.setLilliaFileBatchId(lilliaFileBatchId);
        lilliaFileBatch.setUploadSuccessNum(Integer.valueOf(data.get("uploadSuccessNum")));
        lilliaFileBatch.setUploadTotalNum(Integer.valueOf(data.get("uploadTotalNum")));
        lilliaFileBatch.setUploadFailNum(Integer.valueOf(data.get("uploadFailNum")));
        lilliaFileBatch.setUnUploadNum(Integer.valueOf(data.get("unUploadNum")));
        lilliaFileBatchService.update(lilliaFileBatch);

        return lilliaFileDto;
    }

    @Override
    public LilliaFileDto deleteWithBatch(Map<String, String> data) {
        /*
        * lilliaFileBatchId: this.lilliaFileBatchId,
        localPath: file.url,
        uploadTotalNum: this.uploadTotalNum*/
        Long lilliaFileBatchId = Long.valueOf(data.get("lilliaFileBatchId"));
        LilliaFile lilliaFile = new LilliaFile();
        lilliaFile.setStatus(LilliaBaseService.STATUS_DEL);
        this.updateByFileBatchId(lilliaFile, lilliaFileBatchId, data.get("localPath"));

        LilliaFileBatch lilliaFileBatch = new LilliaFileBatch();
        lilliaFileBatch.setUploadTotalNum(Integer.valueOf(data.get("uploadTotalNum")));
        lilliaFileBatchService.reduceNumById(lilliaFileBatch, lilliaFileBatchId);

        LilliaFileDto lilliaFileDto = new LilliaFileDto();
        lilliaFileDto.setLilliaFileBatchId(lilliaFileBatchId);
        return lilliaFileDto;
    }

    public void updateByFileBatchId(LilliaFile lilliaFile, Long lilliaFileBatchId, String localPath) {
        lilliaFile.setLilliaFileBatchId(lilliaFileBatchId);
        //this.update(lilliaFile);
    }

    private final LilliaFileRepository lilliaFileRepository;
    private final LilliaFileMapper lilliaFileMapper;

    @Override
    public Map<String,Object> queryAll(LilliaFileQueryCriteria criteria, Pageable pageable){
        Page<LilliaFile> page = lilliaFileRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(lilliaFileMapper::toDto));
    }

    @Override
    public List<LilliaFileDto> queryAll(LilliaFileQueryCriteria criteria){
        return lilliaFileMapper.toDto(lilliaFileRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public LilliaFileDto findById(Long lilliaFileId) {
        LilliaFile lilliaFile = lilliaFileRepository.findById(lilliaFileId).orElseGet(LilliaFile::new);
        ValidationUtil.isNull(lilliaFile.getLilliaFileId(),"LilliaFile","lilliaFileId",lilliaFileId);
        return lilliaFileMapper.toDto(lilliaFile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LilliaFileDto create(LilliaFile resources) {
        return lilliaFileMapper.toDto(lilliaFileRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LilliaFile resources) {
        LilliaFile lilliaFile = lilliaFileRepository.findById(resources.getLilliaFileId()).orElseGet(LilliaFile::new);
        ValidationUtil.isNull( lilliaFile.getLilliaFileId(),"LilliaFile","id",resources.getLilliaFileId());
        lilliaFile.copy(resources);
        lilliaFileRepository.save(lilliaFile);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long lilliaFileId : ids) {
            lilliaFileRepository.deleteById(lilliaFileId);
        }
    }

    @Override
    public void download(List<LilliaFileDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LilliaFileDto lilliaFile : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" lilliaFileBatchId",  lilliaFile.getLilliaFileBatchId());
            map.put(" fileName",  lilliaFile.getFileName());
            map.put(" fileType",  lilliaFile.getFileType());
            map.put(" filePath",  lilliaFile.getFilePath());
            map.put(" localPath",  lilliaFile.getLocalPath());
            map.put(" fileSize",  lilliaFile.getFileSize());
            map.put(" fileCtime",  lilliaFile.getFileCtime());
            map.put(" fileCdate",  lilliaFile.getFileCdate());
            map.put(" fileUtime",  lilliaFile.getFileUtime());
            map.put(" readRet",  lilliaFile.getReadRet());
            map.put(" status",  lilliaFile.getStatus());
            map.put(" readStatus",  lilliaFile.getReadStatus());
            map.put(" flag",  lilliaFile.getFlag());
            map.put(" operatorId",  lilliaFile.getOperatorId());
            map.put(" operator",  lilliaFile.getOperator());
            map.put(" comment",  lilliaFile.getComment());
            map.put(" createTime",  lilliaFile.getCreateTime());
            map.put(" updateTime",  lilliaFile.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}