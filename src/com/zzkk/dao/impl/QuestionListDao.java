package com.zzkk.dao.impl;

import com.zzkk.bean.PageBean;
import com.zzkk.util.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionListDao {
    private static final String FSQL = "select count(*) from question";
    private static final String SQL = "select qid,qname,AC,submit from question limit ?,?";
    private PageBean bean = new PageBean();
    private int start;
    private int total;

    public QuestionListDao(int start ,int total){
        this.start = start;
        this.total = total;
    }

    public PageBean sel(){
        ArrayList<String[]> list = null;
        int num = 0;
        Connection conn = null;
        try {
            conn = C3p0Util.getConnection();
            PreparedStatement ps = conn.prepareStatement(FSQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                num = rs.getInt(1);
                bean.setTotalCount(num);
            }
            if(num > 0){
                ps = conn.prepareStatement(SQL);
                ps.setInt(1 ,start);
                ps.setInt(2 ,total);
                rs = ps.executeQuery();
                list = format(rs);
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        bean.setPageCount(20);
        if(num%20 == 0){
            bean.setTotalPage(num/20);
        }else{
            bean.setTotalPage(num/20+1);
        }
        bean.setList(list);
        return bean;
    }

    public ArrayList<String[]> format(ResultSet rs) throws SQLException {
        ArrayList<String[]> list = new ArrayList<>(total);
        while(rs.next()){
            String[] data = {rs.getString("qid") ,rs.getString("qname") ,
            rs.getString("AC") ,rs.getString("submit")};
            list.add(data);
        }
        return list;
    }
}
