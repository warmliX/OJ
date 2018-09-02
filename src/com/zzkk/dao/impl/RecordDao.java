package com.zzkk.dao.impl;

import com.zzkk.bean.RecordBean;
import com.zzkk.util.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordDao {
    private String email;
    private RecordBean bean = new RecordBean();
    private static final String SQL = "insert into registrar (uid,qid,state,language,codelength) values" +
            "((select uid from user where email=?),?,?,?,?);";
    private static final String sSQL = "select @@IDENTITY as rid;";

    public RecordDao(String email ,int qid ,int state ,int language ,int codelength){
        this.email = email;
        bean.setQid(qid);
        bean.setState(state);
        bean.setLanguage(language);
        bean.setCodelength(codelength);
    }

    public int ins(){
        int flag = -1;
        Connection conn = null;
        try {
            conn = C3p0Util.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1 ,email);
            ps.setInt(2 ,bean.getQid());
            ps.setInt(3 ,bean.getState());
            ps.setInt(4 ,bean.getLanguage());
            ps.setLong(5 ,bean.getCodelength());
            ps.executeUpdate();
            ps = conn.prepareStatement(sSQL);
            ResultSet rs = ps.executeQuery();
            conn.commit();
            while (rs.next()){
                flag = rs.getInt(1);
                System.out.println(flag);
            }

        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally{
            return flag;
        }
    }
}
