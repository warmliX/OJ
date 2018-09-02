package com.zzkk.dao.impl;

import com.zzkk.util.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test_dataDao {
    private static final String SQL = "select paramter,outcome from test_data where qid=?";
    private int qid;

    public Test_dataDao(int qid){
        this.qid = qid;
    }

    public ArrayList<String[]> sel() throws SQLException {
        ArrayList<String[]> data = new ArrayList<>(5);
        Connection conn = C3p0Util.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1 ,qid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String[] strings = {rs.getString("paramter") ,rs.getString("outcome")};
            data.add(strings);
        }
        rs.close();
        conn.close();
        return data;
    }

}
