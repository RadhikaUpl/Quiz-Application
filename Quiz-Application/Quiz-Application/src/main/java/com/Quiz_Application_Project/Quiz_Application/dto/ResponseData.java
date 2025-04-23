package com.Quiz_Application_Project.Quiz_Application.dto;

public class ResponseData
{
    private String fileName;
    private String downloadUrl;
    private String fileType;

    public ResponseData() {
    }

    public ResponseData(String fileName, String downloadUrl, String fileType) {
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


}
