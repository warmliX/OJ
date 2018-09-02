package com.zzkk.dao.impl;

import com.zzkk.bean.ContactBean;
import com.zzkk.util.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionContentDao {
    private static final String SQL = "select qname,content,decipt,paramter,outcome,source from question,test_data " +
            "where question.qid=? and question.qid=test_data.qid limit 0,1";
    private int qid;
    private ContactBean bean = new ContactBean();

    public QuestionContentDao(int qid){
        this.qid = qid;
    }

    public ContactBean sel(){
        Connection conn = null;
        try {
            conn = C3p0Util.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1 ,qid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                bean.setContent(rs.getString("content"));
                bean.setDecipt(rs.getString("decipt"));
                bean.setQname(rs.getString("qname"));
                bean.setSource(rs.getString("source"));
                bean.setParamter(rs.getString("paramter"));
                bean.setOutcome(rs.getString("outcome"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
}
