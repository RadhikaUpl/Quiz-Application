package com.Quiz_Application_Project.Quiz_Application.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="quizScore")
public class quizScore
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizid;
    private String username;
    private String quiztype;
    private int score;

    public quizScore() {
    }

    public quizScore(String username, String quiztype, int score) {
        this.username = username;
        this.quiztype = quiztype;
        this.score = score;
    }

    public int getQuizid() {
        return quizid;
    }

    public void setQuizid(int quizid) {
        this.quizid = quizid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuiztype() {
        return quiztype;
    }

    public void setQuiztype(String quiztype) {
        this.quiztype = quiztype;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
