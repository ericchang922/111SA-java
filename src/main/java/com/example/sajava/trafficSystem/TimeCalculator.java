package com.example.sajava.trafficSystem;

import com.example.sajava.Data;
import com.example.sajava.model.request.TimeRequest;
import com.example.sajava.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "time", produces = "application/json")
public class TimeCalculator {
    final String[] lane = {"L01", "L02", "L03", "L04"};
    final List<String> laneList = Arrays.stream(lane).toList();
    final double[] cArray = {4.0, 3.0, 2.0, 1.0};
    final int[] upperArray = {90, 70, 60, 50};
    final int[] lowerArray = {50, 40, 20, 10};

    @Autowired
    TimeRequest timeRequest;
    @Autowired
    CalculatorService calculatorService;

    private static Data data;
    @PostMapping("calculator")
    private ResponseEntity<Map<String, Object>> calculator(@RequestBody TimeRequest tRequest){
        Map<String, Object> roadInfo = calculatorService.roadInfo(tRequest);
        IdentifyTraffic identifyTraffic = new IdentifyTraffic();

        double lightTime = 0;
        String laneId = (String) roadInfo.get("laneId");
        String roadId = (String)roadInfo.get("roadId");
        int laneIndex = laneList.indexOf(laneId);
        int upperValue = upperArray[laneIndex];
        int lowerValue = lowerArray[laneIndex];

        double c = cArray[laneIndex];

        Map<String, Object> carNumberRequest = new LinkedHashMap<>();
        carNumberRequest.put("roadId", roadId);
        int carNum = (int)((Map<String, Object>)(identifyTraffic.carNumber(carNumberRequest).getResult())).get("carno");

        lightTime = calculate(carNum, c);

        if(lightTime >= upperValue) lightTime = upperValue;
        else if (lightTime <= lowerValue) lightTime = lowerValue;
        else lightTime = Math.round(lightTime);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("trafficFlow", carNum);
        response.put("lightTime", lightTime);
        data = new Data(200, response);
        return data.getResponse();
    }

    @PostMapping("tool")
    private ResponseEntity<Map<String, Object>> tool(@RequestBody Map<String, Object> request){
        String intersectionId = (String)request.get("intersectionId");
        int isNS = (int)request.get("isNS");
        int carNum = (int)request.get("carNum");

        TimeRequest tRequest = new TimeRequest();
        tRequest.setIntersectionId(intersectionId);
        tRequest.setIsNS(isNS);

        Map<String, Object> roadInfo = calculatorService.roadInfo(tRequest);

        double lightTime = 0;
        String laneId = (String) roadInfo.get("laneId");
        int laneIndex = laneList.indexOf(laneId);
        int upperValue = upperArray[laneIndex];
        int lowerValue = lowerArray[laneIndex];

        double c = cArray[laneIndex];

        lightTime = calculate(carNum, c);

        if(lightTime >= upperValue) lightTime = upperValue;
        else if (lightTime <= lowerValue) lightTime = lowerValue;
        else lightTime = Math.round(lightTime);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("trafficFlow", carNum);
        response.put("lightTime", lightTime);
        data = new Data(200, response);
        return data.getResponse();
    }

    private double calculate(int carNum, double c){
        return (10 * (c - 1)) + ((4.0 * carNum) / c) + 2.0;
    }

}
