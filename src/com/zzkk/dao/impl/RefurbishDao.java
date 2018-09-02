package com.zzkk.dao.impl;

import com.zzkk.util.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RefurbishDao {
    private String email;
    private int currentPage;
    private int total;
    private static final String SQL = "select registrar.qid,qname,state,date,time,language,memory,codelength from registrar,user," +
            "question where email=? and user.uid=registrar.uid and registrar.qid=question.qid limit ?,?";

    public RefurbishDao(String email ,int currentPage ,int total){
        this.email = email;
        this.currentPage = currentPage;
        this.total = total;
    }

    public ArrayList<String[]> sel(){
        Connection conn = null;
        ArrayList<String[]> data = null;
        try {
            conn = C3p0Util.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1 ,email);
            ps.setInt(2 ,5*(currentPage-1));
            ps.setInt(3 ,total);
            ResultSet rs = ps.executeQuery();
            data = Format(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return  data;
        }
    }

    public ArrayList<String[]> Format(ResultSet rs) throws SQLException {
        ArrayList<String[]> data = new ArrayList<>();
        while(rs.next()){
            data.add(new String[]{rs.getString("qid") ,rs.getString("qname") ,
                    rs.getString("state") , rs.getString("date") ,
                    rs.getString("language") , rs.getString("time") ,
                    rs.getString("memory") , rs.getString("codelength")});
        }
        return data;
    }
}
