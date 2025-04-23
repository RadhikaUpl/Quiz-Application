package com.Quiz_Application_Project.Quiz_Application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Options
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;

    @Column(nullable = false)
    private String options;

    @Column(nullable = false)
    private boolean isCorrect;

    public Options(){}

    public Options(String options, boolean isCorrect) {
        this.options = options;
        this.isCorrect =isCorrect;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setCorrectOption(boolean isCorrect) {
        this.isCorrect=isCorrect;
    }

    @ManyToOne
    @JoinColumn(name="question_id")
    @JsonBackReference
    private Questions questions;

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }
}
