package com.mbobiosio.firebaserecycler.model;

/**
 * Created by kodefreak on 3/21/17.
 */

public class ModelClass {

    String image, title, content;

    public ModelClass(String image, String title, String content) {
        this.image = image;
        this.title = title;
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
