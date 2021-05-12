package com.bis.lafefny.Model;

public class RecomededModel {
    String image;
    String category;
    String name;

    public RecomededModel(final String image, final String category, final String name) {
        this.image = image;
        this.category = category;
        this.name = name;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
