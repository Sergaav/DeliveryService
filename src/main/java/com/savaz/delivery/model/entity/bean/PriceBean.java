package com.savaz.delivery.model.entity.bean;

import java.io.Serializable;

public class PriceBean implements Serializable {
    private int id;
    private String rateName;
    private int maxWeight;
    private int rate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "PriceBean{" +
                "id=" + id +
                ", rateName='" + rateName + '\'' +
                ", maxWeight=" + maxWeight +
                ", rate=" + rate +
                '}';
    }
}
