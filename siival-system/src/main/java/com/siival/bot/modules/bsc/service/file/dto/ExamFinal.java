package com.siival.bot.modules.bsc.service.file.dto;

import com.siival.bot.annotation.ExcelColumn;

/**
 * @author Peter
 */
public class ExamFinal {
    //題號	參考章節	題目	正確答案	題目解釋
    @ExcelColumn(value = "題號", col = 1)
    private String questionNo;

    @ExcelColumn(value = "參考章節", col = 2)
    private String chatper;

    @ExcelColumn(value = "題目", col = 3)
    private String question;

    @ExcelColumn(value = "正確答案", col = 4)
    private String answer;

    @ExcelColumn(value = "題目解釋", col = 5)
    private String explain;

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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
