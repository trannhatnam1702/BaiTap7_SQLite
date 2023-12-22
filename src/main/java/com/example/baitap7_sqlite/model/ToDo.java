package com.example.baitap7_sqlite.model;

public class ToDo {
    private Integer Id;
    private String Title;
    private String Content;
    private String Date;
    private String Type;
    private Integer Status;

    public ToDo(Integer id, String title, String content, String date, String type, Integer status) {
        Id = id;
        Title = title;
        Content = content;
        Date = date;
        Type = type;
        Status = status;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}
