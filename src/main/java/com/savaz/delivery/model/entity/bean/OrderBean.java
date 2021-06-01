package com.savaz.delivery.model.entity.bean;

import com.savaz.delivery.model.entity.Parcel;
import com.savaz.delivery.model.entity.User;
import com.savaz.delivery.model.entity.enums.Status;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderBean implements Serializable {
    private int id;
    private User user;
    private String address;
    private Parcel parcel;
    private Status status;
    private int cityArriveId;
    private int cityDepartureId;
    private int weightRate;
    private LocalDate dateCreation;
    private LocalDate dateDeparture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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

    public int getWeightRate() {
        return weightRate;
    }

    public void setWeightRate(int weightRate) {
        this.weightRate = weightRate;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(LocalDate dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", user=" + user +
                ", address='" + address + '\'' +
                ", parcel=" + parcel +
                ", status=" + status +
                ", cityArriveId=" + cityArriveId +
                ", cityDepartureId=" + cityDepartureId +
                ", weightRate=" + weightRate +
                ", dateCreation=" + dateCreation +
                ", dateDeparture=" + dateDeparture +
                '}';
    }
}
