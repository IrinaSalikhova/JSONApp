package com.cst3104.jsonapp;

public class Restaurant {

    private String name;
    private String description;
    private String image;

    public Restaurant(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Restaurant(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Restaurant() {
        this(null, null);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
