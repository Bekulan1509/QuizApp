package com.twodev.models;

public class QuestionModelBool extends QuestionModel {
    private String answerYes;
    private String question;
    private String answerNo;

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerYes() {
        return answerYes;
    }

    public void setAnswerYes(String answerYes) {
        this.answerYes = answerYes;
    }

    public String getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(String answerNo) {
        this.answerNo = answerNo;
    }

    public QuestionModelBool(String question) {
        super(question);
        this.question = question;
    }
}
