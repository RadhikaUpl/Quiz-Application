package com.Quiz_Application_Project.Quiz_Application.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Attachment")
public class Attachment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String filename;
    private String fileType;
    private String quizType;
    @Lob
    private byte[] data;

    public Attachment() {
    }

    public Attachment(String filename, String fileType,String quizType, byte[] data) {
        this.filename = filename;
        this.fileType=fileType;
        this.quizType = quizType;
        this.data = data;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
