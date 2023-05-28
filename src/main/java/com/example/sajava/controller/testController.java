package com.example.sajava.controller;

import com.example.sajava.model.TestModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;



@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
@RequestMapping("test")
public class testController {
    public static void main(String[] args) {

    }
    @GetMapping("gettest")
    public Map<String, Object> index() {
        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");

        return  data;
    }

    @PostMapping("posttest")
    public TestModel test(@RequestBody TestModel testModel){
        return new TestModel(
                testModel.getId(),
                testModel.getTestString()
        );
//        會回傳傳進來的class

    }
//    將JSON 傳入 localhost:8080/test/posttest 例如:
//    {
//        "id": 11111111,
//            "testString": "test"
//    }
}
