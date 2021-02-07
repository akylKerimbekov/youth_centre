package dev.akyl.youthcentre.repository.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SurveyResultItem {
    private StringProperty chapter = new SimpleStringProperty(this, "chapter");
    private StringProperty question = new SimpleStringProperty(this, "question");
    private StringProperty answer = new SimpleStringProperty(this, "answer");

    public SurveyResultItem() {
    }

    public SurveyResultItem(StringProperty chapter) {
        this.chapter = chapter;
    }

    public SurveyResultItem(StringProperty question, StringProperty answer) {
        this.question = question;
        this.answer = answer;
    }

    public SurveyResultItem(StringProperty chapter, StringProperty question, StringProperty answer) {
        this.chapter = chapter;
        this.question = question;
        this.answer = answer;
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
