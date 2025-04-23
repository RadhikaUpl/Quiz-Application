package com.Quiz_Application_Project.Quiz_Application.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="questions_table")
public class Questions
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @Column(nullable = false)
    private String questions;

    private String queType;

    public Questions(String questions,String queType) {
        this.questions = questions;
        this.queType=queType;
    }

    public Questions(){}

    public int getQuestionId() {
        return questionId;
    }

    public String getQueType() {
        return queType;
    }

    public void setQueType(String queType) {
        this.queType = queType;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    @OneToMany(mappedBy = "questions",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Options> options=new ArrayList<>();

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }
}
