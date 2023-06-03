package com.example.sajava.service;

import com.example.sajava.Data;
import com.example.sajava.model.LaneModel;
import com.example.sajava.repository.LaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaneService {
    @Autowired
    LaneRepository laneRepository;

    public Data add(LaneModel laneModel){
        return laneRepository.insertLane(laneModel);
    }

    public Data all(){
        return laneRepository.selectAllLane();
    }
}
