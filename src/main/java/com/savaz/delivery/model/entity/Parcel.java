package com.savaz.delivery.model.entity;

import java.io.Serializable;

public class Parcel implements Serializable {
    private int id;
    private String description;
    private int length;
    private int width;
    private int height;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
