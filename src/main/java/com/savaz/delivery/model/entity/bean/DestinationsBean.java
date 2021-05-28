package com.savaz.delivery.model.entity.bean;

import java.io.Serializable;

public class DestinationsBean implements Serializable {

    private String cityArrive;
    private String cityDeparture;

    public String getCityArrive() {
        return cityArrive;
    }

    public void setCityArrive(String cityArrive) {
        this.cityArrive = cityArrive;
    }

    public String getCityDeparture() {
        return cityDeparture;
    }

    public void setCityDeparture(String cityDeparture) {
        this.cityDeparture = cityDeparture;
    }

    @Override
    public String toString() {
        return "DestinationsBean{" +
                "cityArrive='" + cityArrive + '\'' +
                ", cityDeparture='" + cityDeparture + '\'' +
                '}';
    }
}
