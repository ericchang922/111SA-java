package com.example.sajava.service;

import com.example.sajava.model.request.TimeRequest;
import com.example.sajava.repository.CalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculatorService {
    @Autowired
    CalculatorRepository calculatorRepository;
    public Map<String, Object> roadInfo(TimeRequest timeRequest){
        List<Map<String,Object>> crossList = calculatorRepository.crossroadsData(timeRequest.getIntersectionId());
        String roadId = "";
        String laneId = "";
        int roadLength = 0;

        for(int i = 0; i < 2; i++){
            Map<String, Object> thisCross = crossList.get(i);
            if(timeRequest.getIsNS() == ((int)thisCross.get("is_ns"))){
                roadId = (String)thisCross.get("road_id");
                roadLength = (int)thisCross.get("length");
                laneId = calculatorRepository.roadLane(roadId);
            }
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("roadId", roadId);
        response.put("laneId", laneId);
        response.put("roadLength",roadLength);

        return response;
    }
}
