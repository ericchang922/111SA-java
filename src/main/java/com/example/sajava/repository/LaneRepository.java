package com.example.sajava.repository;

import com.example.sajava.Data;
import com.example.sajava.model.LaneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LaneRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Data data;

    public Data insertLane(LaneModel laneModel){
        System.out.println("add schedule");

        String sql = "INSERT INTO lane(lane_id, lane_type) VALUES(?, ?)";
        Object[] value = {laneModel.getLane_id(),laneModel.getLane_type()};

        try{
            jdbcTemplate.update(sql, value);
            data = new Data(200, "insert success");
            return data;
        }catch (Exception e){
            System.out.println(e);
            data = new Data(400, e.toString());
            return data;
        }

    }

    public Data selectAllLane(){
        String sql = "SELECT * FROM lane";


        data = new Data(200, jdbcTemplate.queryForList(sql));

        return data;
    }
}
