package dev.akyl.youthcentre.repository.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SurveyResultItem {
    private StringProperty chapter = new SimpleStringProperty(this, "chapter");
    private StringProperty question = new SimpleStringProperty(this, "question");
    private StringProperty answer = new SimpleStringProperty(this, "answer");

    public SurveyResultItem() {
    }

    public SurveyResultItem(SurveyRef surveyRef) {
        this.chapter.bindBidirectional(surveyRef.codeProperty());
        //chapter.set(surveyRef.getCode());
    }

    public SurveyResultItem(SurveyRef surveyRef, SurveyResult surveyResult) {
        this.question.bindBidirectional(surveyRef.codeProperty());
        if (surveyResult != null) {
            this.answer.bindBidirectional(surveyResult.valueProperty());
        } else {
            this.answer.set("");
        }
    }

    public String getChapter() {
        return chapter.get();
    }

    public StringProperty chapterProperty() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter.set(chapter);
    }

    public String getQuestion() {
        return question.get();
    }

    public StringProperty questionProperty() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public String getAnswer() {
        return answer.get();
    }

    public StringProperty answerProperty() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }
}
