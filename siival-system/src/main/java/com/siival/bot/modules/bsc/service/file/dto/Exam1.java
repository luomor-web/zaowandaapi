package com.siival.bot.modules.bsc.service.file.dto;

import com.siival.bot.annotation.ExcelColumn;

/**
 * @author Peter
 */
public class Exam1 {
    //題號	參考章節	問題	答案	答案1
    @ExcelColumn(value = "題號", col = 1)
    private String questionNo;

    @ExcelColumn(value = "參考章節", col = 2)
    private String chatper;

    @ExcelColumn(value = "問題", col = 3)
    private String question;

    @ExcelColumn(value = "答案", col = 4)
    private String answer;

    @ExcelColumn(value = "答案1", col = 5)
    private String answer1;

    public String getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }

    public String getChatper() {
        return chatper;
    }

    public void setChatper(String chatper) {
        this.chatper = chatper;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
}
