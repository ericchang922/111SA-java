package com.example.sajava.model;

import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class LaneModel {
    @Id
    private String lane_id;
    private String lane_type;
    public String getLane_id(){ return lane_id;}
    public String getLane_type(){ return lane_type;}
    public void setLane_id(String lane_id){ this.lane_id = lane_id;}
    public void setLane_type(String lane_type){ this.lane_type = lane_type;}
}
