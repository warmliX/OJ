package com.zzkk.dao.impl;

import com.zzkk.util.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlterUserDao {
    private static final String SQL = "select uanswer from user where email=?";
    private static final String ASQL = "update user set password=? where email=?";
    private String email;
    private String password;

    public AlterUserDao(String email ,String password){
        this.email = email;
        this.password = password;
    }

    public String sel() throws SQLException {
        Connection conn = C3p0Util.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1 ,email);
        ResultSet rs = ps.executeQuery();
        String ans = null;
        while (rs.next()){
            ans = rs.getString("uanswer");
        }
        ps.close();
        conn.close();
        return ans;
    }

    public void update() throws SQLException {
        Connection conn = C3p0Util.getConnection();
        PreparedStatement ps = conn.prepareStatement(ASQL);
        ps.setString(1 ,password);
        ps.setString(2 ,email);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
