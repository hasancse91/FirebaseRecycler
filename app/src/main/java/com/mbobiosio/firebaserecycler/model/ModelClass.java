package com.mbobiosio.firebaserecycler.model;

/**
 * Created by kodefreak on 3/21/17.
 */

public class ModelClass {

    String image, title, content, newDate, quote;

    public ModelClass(String image, String title, String newDate, String quote, String content) {
        this.image = image;
        this.title = title;
        this.quote = quote;
        this.newDate = newDate;
        this.content = content;
    }

    public ModelClass() {
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }
    public String getNewDate() {
        return newDate;
    }
    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}