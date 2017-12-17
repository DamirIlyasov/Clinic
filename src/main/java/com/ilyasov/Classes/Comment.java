package com.ilyasov.Classes;

import java.util.Date;

public class Comment {
    String author;
    String service;
    String comment;
    Date createdAt;
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {

        return comment;
    }




    public String getAuthor() {
        return author;
    }

    public String getService() {
        return service;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
