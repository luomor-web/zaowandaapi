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
import com.siival.bot.modules.bsc.service.QuestionMenuService;
import com.siival.bot.modules.bsc.service.dto.LilliaFileBatchDto;
import com.siival.bot.modules.bsc.service.dto.QuestionInfoDto;
import com.siival.bot.modules.bsc.service.dto.QuestionInfoQueryCriteria;
import com.siival.bot.modules.bsc.service.dto.QuestionMenuDto;
import com.siival.bot.modules.bsc.service.dto.QuestionMenuQueryCriteria;
import com.siival.bot.modules.bsc.service.file.dto.Exam1;
import com.siival.bot.modules.bsc.service.file.dto.ExamFinal;
import com.siival.bot.modules.bsc.service.impl.QuestionInfoServiceImpl;
import com.siival.bot.modules.bsc.service.impl.QuestionMenuServiceImpl;
import com.siival.bot.utils.BeanUtil;
import com.siival.bot.utils.ExcelUtils;
import com.siival.bot.utils.JacksonUtil;
import com.siival.bot.utils.RegexUtil;
import com.siival.bot.utils.StringUtils;

@Service
public class ExcelExamFinalService implements FileBaseService {
    public final Log logger = LogFactory.getLog(ExcelExamFinalService.class);

    @Override
    public void read(File xlsFile) {
        
    }

    @Override
    public void read(File xlsFile, LilliaFileBatchDto lilliaFileBatchDto) throws Exception {
        QuestionInfoService questionInfoService = BeanUtil.getBean(QuestionInfoServiceImpl.class);
        QuestionMenuService questionMenuService = BeanUtil.getBean(QuestionMenuServiceImpl.class);

        List<ExamFinal> list = ExcelUtils.readExcel(ExamFinal.class, xlsFile, 0);
        logger.info("读取文件信息---批次读取---行数---" + list.size());
        if(list.size() > 0) {
            logger.info("读取文件信息---批次读取---第一行---" + JacksonUtil.toJson(list.get(0)));
        }

        ExamFinal exam1 = null;
        String question = "";
        String chapter = "";
        String[] chapters;
        int chapterId = 0;
        int pid = 0;
        int questionMenuId = 0;
        String answer = "";
        String analysis = "";
        List<String> data;
        QuestionInfoQueryCriteria criteria;
        QuestionMenuQueryCriteria criteriaMenu;
        List<QuestionInfoDto> questionInfoDtoList;
        QuestionInfoDto questionInfoDto;
        QuestionInfo questionInfo;
        List<SelectListInfo> selectListInfoList;
        SelectListInfo selectListInfo;

        questionMenuId = lilliaFileBatchDto.getQuestionMenuId();
        criteriaMenu = new QuestionMenuQueryCriteria();
        criteriaMenu.setPid(questionMenuId);
        Map<Integer, QuestionMenuDto> questionMenuMap = new HashMap<>();
        List<QuestionMenuDto> questionMenuDto = questionMenuService.queryAll(criteriaMenu);
        for(int i = 0 ; i < questionMenuDto.size() ; i++) {
            questionMenuMap.put(questionMenuDto.get(i).getChapterId(), questionMenuDto.get(i));
        }
        logger.info("读取文件信息---批次读取---questionMenuMap---" + JacksonUtil.toJson(questionMenuMap));

        int num = 5;
        for(int j = 0 ; j < list.size() ; j = j + num) {
            data = new ArrayList<>();
            for(int i = 0 ; i < num ; i++) {
                exam1 = list.get(j + i);
                logger.info("读取文件信息---批次读取---" + (j + i) + "---" + JacksonUtil.toJson(exam1));
                if(i == 0) {
                    question = "[单选]" + exam1.getQuestion();
                    if(!StringUtils.isEmpty(exam1.getExplain())) {
                        analysis = exam1.getExplain();
                    } else {
                        analysis = exam1.getChatper();
                        chapter = exam1.getChatper();
                    }
                } else {
                    if(!StringUtils.isEmpty(exam1.getQuestion())) {
                        data.add(exam1.getQuestion());
                    }
                    if(!StringUtils.isEmpty(exam1.getAnswer())
                        && exam1.getAnswer().equals("Y")) {
                        answer = exam1.getChatper().toUpperCase();
                    }
                }
            }
            selectListInfoList = new ArrayList<>();
            for(int i = 0 ; i < data.size() ; i++) {
                selectListInfo = new SelectListInfo();
                selectListInfo.setTitle(data.get(i).replace("\n", ""));
                selectListInfoList.add(selectListInfo);
            }
            
            chapters = chapter.split("\\.");
            logger.info("读取文件信息---批次读取---chapters---" + JacksonUtil.toJson(chapters));
            if(chapters.length > 0) {
                chapterId = Integer.valueOf(chapters[0]);
            } else {
                chapterId = 0;
            }
            if(questionMenuMap.containsKey(chapterId)) {
                pid = questionMenuMap.get(chapterId).getId();
            } else {
                pid = lilliaFileBatchDto.getQuestionMenuId();
            }

            criteria = new QuestionInfoQueryCriteria();
            criteria.setQuestion(question);
            criteria.setPid(pid);
            questionInfoDtoList = questionInfoService.queryAll(criteria);
            // insert
            if(questionInfoDtoList == null || questionInfoDtoList.size() == 0) {
                questionInfo = new QuestionInfo();
                questionInfo.setType(1);
                questionInfo.setMultiply(0);
                questionInfo.setPid(pid);
                questionInfo.setAnalysis(analysis);
                questionInfo.setRightAnswer(answer);
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
                questionInfo.setPid(pid);
                questionInfo.setAnalysis(analysis);
                questionInfo.setRightAnswer(answer);
                questionInfo.setStatus(1);
                questionInfo.setQuestion(question);
                questionInfo.setSelectList(selectListInfoList);
                questionInfoService.update(questionInfo);
            }
        }
    }
}
