package com.zzkk.dao.impl;

import com.zzkk.util.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStateDao {
    private String state;
    private int time;
    private int memory;
    private int rid;
    private String SQL = "update registrar set state=?,time=?,memory=? where rid=?;";

    public UpdateStateDao(String state ,int time ,int memory ,int rid){
        this.state = state;
        this.time = time;
        this.memory = memory;
        this.rid = rid;
    }

    public void update() throws SQLException {
        Connection conn = C3p0Util.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1 ,state);
        ps.setInt(2 ,time);
        ps.setInt(3 ,memory);
        ps.setInt(4 ,rid);
        ps.executeUpdate();
    }
}
