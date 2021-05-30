package com.savaz.delivery.model.entity.bean;

import java.io.Serializable;


public class CalculateBean implements Serializable {
    private int cityArriveId;
    private int cityDepartureId;
    private String rateName;
    private int weight;
    private int basicRate;
    private int length;
    private int height;
    private int width;
    private double rateDepArr;

    public int getCityArriveId() {
        return cityArriveId;
    }

    public void setCityArriveId(int cityArriveId) {
        this.cityArriveId = cityArriveId;
    }

    public int getCityDepartureId() {
        return cityDepartureId;
    }

    public void setCityDepartureId(int cityDepartureId) {
        this.cityDepartureId = cityDepartureId;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(int basicRate) {
        this.basicRate = basicRate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getRateDepArr() {
        return rateDepArr;
    }

    public void setRateDepArr(double rateDepArr) {
        this.rateDepArr = rateDepArr;
    }

    @Override
    public String toString() {
        return "CalculateBean{" +
                "cityArrive_id=" + cityArriveId +
                ", cityDeparture_id=" + cityDepartureId +
                ", rateName='" + rateName + '\'' +
                ", weight=" + weight +
                ", basicRate=" + basicRate +
                ", length=" + length +
                ", height=" + height +
                ", width=" + width +
                ", rateDepArr=" + rateDepArr +
                '}';
    }
}
