package com.example.sajava.model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class IntersectionModel {
    @Id
    private String intersectionId;
    private double locationX;
    private double locationY;

    public String getIntersectionId() {
        return intersectionId;
    }
    public double getLocationX() {
        return locationX;
    }
    public double getLocationY() {
        return locationY;
    }

    public void setIntersectionId(String intersectionId) {
        this.intersectionId = intersectionId;
    }
    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }
    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }
}

