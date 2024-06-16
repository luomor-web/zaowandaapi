package com.siival.bot.modules.bsc.service.file;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.siival.bot.modules.bsc.domain.QuestionInfo;
import com.siival.bot.modules.bsc.service.QuestionInfoService;
import com.siival.bot.modules.bsc.service.dto.QuestionInfoDto;
import com.siival.bot.modules.bsc.service.dto.QuestionInfoQueryCriteria;
import com.siival.bot.modules.bsc.service.file.dto.Exam1;
import com.siival.bot.modules.bsc.service.impl.QuestionInfoServiceImpl;
import com.siival.bot.utils.BeanUtil;
import com.siival.bot.utils.ExcelUtils;
import com.siival.bot.utils.JacksonUtil;

@Service
public class ExcelExam2Service implements FileBaseService {
    public final Log logger = LogFactory.getLog(ExcelExam2Service.class);

    @Override
    public void read(File xlsFile) {
        QuestionInfoService questionInfoService = BeanUtil.getBean(QuestionInfoServiceImpl.class);

        List<Exam1> list = ExcelUtils.readExcel(Exam1.class, xlsFile, 1);
        logger.info("读取文件信息---批次读取---行数---" + list.size());
        if(list.size() > 0) {
            logger.info("读取文件信息---批次读取---第一行---" + JacksonUtil.toJson(list.get(0)));
        }

        Exam1 exam1 = null;
        String question = "";
        QuestionInfoQueryCriteria criteria;
        List<QuestionInfoDto> questionInfoDtoList;
        QuestionInfoDto questionInfoDto;
        QuestionInfo questionInfo;

        for(int j = 0 ; j < list.size() ; j++) {
            exam1 = list.get(j);
            logger.info("读取文件信息---批次读取---" + j + "---" + JacksonUtil.toJson(exam1));
            question = exam1.getQuestion();
            criteria = new QuestionInfoQueryCriteria();
            criteria.setQuestion(question);
            questionInfoDtoList = questionInfoService.queryAll(criteria);
            // insert
            if(questionInfoDtoList == null || questionInfoDtoList.size() == 0) {
                questionInfo = new QuestionInfo();
                questionInfoService.create(questionInfo);
            } else {
                questionInfoDto = questionInfoDtoList.get(0);
                questionInfo = new QuestionInfo();
                questionInfo.setId(questionInfoDto.getId());
                questionInfoService.update(questionInfo);
            }
        }
    }
}
