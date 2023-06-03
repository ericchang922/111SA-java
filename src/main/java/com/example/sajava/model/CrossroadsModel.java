package com.example.sajava.model;

import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
public class CrossroadsModel {
    @Id
    private String intersectionId;
    @Id
    private String roadId;
    private Boolean isNS;
    private int length;

    public String getIntersectionId() {
        return intersectionId;
    }
    public String getRoadId() {
        return roadId;
    }
    public Boolean getIsNS() { return isNS;}
    public int getLength() { return length;}
    public void setIntersectionId(String intersectionId) {
        this.intersectionId = intersectionId;
    }
    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }
    public void setIsNS(Boolean isNs) { this.isNS = isNs;}
    public void setLength(int length) { this.length = length;}
}
