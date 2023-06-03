package com.example.sajava.controller;

import com.example.sajava.Data;
import com.example.sajava.model.LaneModel;
import com.example.sajava.service.LaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("lane")
public class LaneController {
    @Autowired
    LaneModel laneModel;

    @Autowired
    LaneService laneService;

    Data data;
    @PostMapping("add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody LaneModel lModel){
        data = laneService.add(lModel);
        return data.getResponse();
    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> all(){
        data = laneService.all();

        return data.getResponse();
    }
}
