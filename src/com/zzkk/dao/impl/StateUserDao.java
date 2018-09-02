package com.zzkk.dao.impl;

import com.zzkk.bean.PageBean;
import com.zzkk.util.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StateUserDao {
    private String email;
    private int start;
    private int total;
    private PageBean pageBean = new PageBean();
    private static final String FSQL = "select count(*) from registrar,user\n" +
            "where email=? and user.uid=registrar.uid";
    private static final String SQL = "select registrar.qid,qname,state,date,time,language,memory,codelength from registrar,user," +
            "question where email=? and user.uid=registrar.uid and registrar.qid=question.qid limit ?,?";

    public StateUserDao(String email ,int currentPage ,int total){
        this.email = email;
        this.start = currentPage;
        this.total = total;
    }

    public PageBean sel(){
        ArrayList<String[]> list = null;
        Connection conn = null;int num=0;

        try {
            conn = C3p0Util.getConnection();
            PreparedStatement ps = conn.prepareStatement(FSQL);
            ps.setString(1 ,email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                num = rs.getInt(1);
            }
            if(num%5!=0)
                pageBean.setTotalPage(num/5+1);
            else
                pageBean.setTotalPage(num/5);

            pageBean.setCurrentPage(start);

            if(num > 0) {
                ps = conn.prepareStatement(SQL);
                ps.setString(1, email);
                ps.setInt(2 ,5*(pageBean.getCurrentPage()-1));
                ps.setInt(3 ,total);
                rs = ps.executeQuery();
                list = format(rs,num);
                ps.close();
                rs.close();
            } else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        pageBean.setTotalCount(num);
        pageBean.setPageCount(5);

        pageBean.setList(list);
        return pageBean;
    }

    private ArrayList format(ResultSet rs ,int num) throws SQLException {
        ArrayList<String[]> list = new ArrayList<>(num);
        while(rs.next()){
            String[] data = {rs.getString("qid") ,rs.getString("qname") ,
                    rs.getString("state") , rs.getString("date") ,
                    rs.getString("language") , rs.getString("time") ,
                    rs.getString("memory") , rs.getString("codelength")};
            list.add(data);
        }
        return list;
    }
}
