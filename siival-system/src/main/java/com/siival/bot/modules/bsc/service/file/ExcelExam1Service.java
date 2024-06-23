package com.siival.bot.modules.bsc.service.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.siival.bot.modules.api.resp.SelectListInfo;
import com.siival.bot.modules.bsc.domain.QuestionInfo;
import com.siival.bot.modules.bsc.service.QuestionInfoService;
import com.siival.bot.modules.bsc.service.dto.LilliaFileBatchDto;
import com.siival.bot.modules.bsc.service.dto.QuestionInfoDto;
import com.siival.bot.modules.bsc.service.dto.QuestionInfoQueryCriteria;
import com.siival.bot.modules.bsc.service.file.dto.Exam1;
import com.siival.bot.modules.bsc.service.impl.QuestionInfoServiceImpl;
import com.siival.bot.utils.BeanUtil;
import com.siival.bot.utils.ExcelUtils;
import com.siival.bot.utils.JacksonUtil;
import com.siival.bot.utils.RegexUtil;

@Service
public class ExcelExam1Service implements FileBaseService {
    public final Log logger = LogFactory.getLog(ExcelExam1Service.class);

    @Override
    public void read(File xlsFile) {
        
    }

    @Override
    public void read(File xlsFile, LilliaFileBatchDto lilliaFileBatchDto) throws Exception {
        QuestionInfoService questionInfoService = BeanUtil.getBean(QuestionInfoServiceImpl.class);

        List<Exam1> list = ExcelUtils.readExcel(Exam1.class, xlsFile, 0);
        logger.info("读取文件信息---批次读取---行数---" + list.size());
        if(list.size() > 0) {
            logger.info("读取文件信息---批次读取---第一行---" + JacksonUtil.toJson(list.get(0)));
        }

        Exam1 exam1 = null;
        String questionAll = "";
        String question = "";
        List<String> data;
        QuestionInfoQueryCriteria criteria;
        List<QuestionInfoDto> questionInfoDtoList;
        QuestionInfoDto questionInfoDto;
        QuestionInfo questionInfo;
        List<SelectListInfo> selectListInfoList;
        SelectListInfo selectListInfo;

        for(int j = 0 ; j < list.size() ; j++) {
            exam1 = list.get(j);
            logger.info("读取文件信息---批次读取---" + j + "---" + JacksonUtil.toJson(exam1));
            questionAll = exam1.getQuestion();
            // [{"title": "test"}]
            data = RegexUtil.getMatches(RegexUtil.REGEX_QUESTION1, questionAll);
            logger.info("读取文件信息---批次读取---" + j + "---" + JacksonUtil.toJson(data));
            selectListInfoList = new ArrayList<>();
            question = "[单选]" + data.get(0).replace("\n", "");
            for(int i = 1 ; i < data.size() ; i++) {
                selectListInfo = new SelectListInfo();
                selectListInfo.setTitle(data.get(i).replace("\n", ""));
                selectListInfoList.add(selectListInfo);
            }
            criteria = new QuestionInfoQueryCriteria();
            criteria.setQuestion(question);
            criteria.setPid(lilliaFileBatchDto.getQuestionMenuId());
            questionInfoDtoList = questionInfoService.queryAll(criteria);
            // insert
            if(questionInfoDtoList == null || questionInfoDtoList.size() == 0) {
                questionInfo = new QuestionInfo();
                questionInfo.setType(1);
                questionInfo.setMultiply(0);
                questionInfo.setPid(lilliaFileBatchDto.getQuestionMenuId());
                questionInfo.setAnalysis(exam1.getChatper());
                questionInfo.setRightAnswer(exam1.getAnswer1());
                questionInfo.setStatus(1);
                questionInfo.setQuestion(question);
                questionInfo.setSelectList(selectListInfoList);
                questionInfoService.create(questionInfo);
            } else {
                questionInfoDto = questionInfoDtoList.get(0);
                questionInfo = new QuestionInfo();
                questionInfo.setId(questionInfoDto.getId());
                questionInfo.setType(1);
                questionInfo.setMultiply(0);
                questionInfo.setPid(lilliaFileBatchDto.getQuestionMenuId());
                questionInfo.setAnalysis(exam1.getChatper());
                questionInfo.setRightAnswer(exam1.getAnswer1());
                questionInfo.setStatus(1);
                questionInfo.setQuestion(question);
                questionInfo.setSelectList(selectListInfoList);
                questionInfoService.update(questionInfo);
            }
        }
    }
}
