package com.example.sajava.repository;

import com.example.sajava.Data;
import com.example.sajava.model.CrossroadsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public class CrossroadsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static Data data;

    public Data insertCrossroads(CrossroadsModel crossroadsModel) {
        System.out.println("insert crossroads");
        String sql = "INSERT INTO crossroads(intersection_id, road_id, is_ns, length) VALUES(?, ?, ?, ?)";
        Object[] value = new Object[]{crossroadsModel.getIntersectionId(), crossroadsModel.getRoadId(), crossroadsModel.getIsNS(), crossroadsModel.getLength()};

        try {
            jdbcTemplate.update(sql, value);
            data = new Data(200, "insert success");
            return data;

        } catch (Exception e) {
            data = new Data(400, e.toString());
            return data;
        }
    }

    public Data delCrossroads(CrossroadsModel crossroadsModel) {
        System.out.println("delete crossroads");
        String sql = "DELETE FROM crossroads WHERE intersection_id = ? and road_id = ?";

        try {
            jdbcTemplate.update(sql, crossroadsModel.getIntersectionId(), crossroadsModel.getRoadId());
            data = new Data(200, "delete success");
        } catch (Exception e) {
            data = new Data(400, e.toString());
        }

        return data;
    }

    public Data selectCrossroads(Map<String, Object> reqBody) {
        System.out.println("search ");
        final String jsonKey[] = {"intersectionId", "roadId, isNS, length"};
        final String sqlAttribute[] = {"intersection_id", "road_id", "is_ns", "length"};

        ArrayList<String> value = new ArrayList<>();
        String sql;
        sql = "SELECT * FROM crossroads WHERE ";

        for (int i = 0; i < jsonKey.length; i++) {
            String s = jsonKey[i];
            String rBody = (String) reqBody.get(s);

            if (rBody != null) {
                if (value.toArray().length > 0) sql += " and ";

                value.add(rBody);

                sql += sqlAttribute[i] + " = ?";
            }
        }

        try {
            data = new Data(200, jdbcTemplate.queryForList(sql, value.toArray()));
        } catch (Exception e) {
            data = new Data(400, e.toString());
        }

        return data;
    }

    public Data selectAllCrossroads() {
        System.out.println("select all");

        try {
            data = new Data(200, jdbcTemplate.queryForList("SELECT * FROM crossroads"));
        } catch (Exception e) {
            data = new Data(400, e.toString());
        }

        return data;
    }
}
