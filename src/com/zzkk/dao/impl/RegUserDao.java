package com.zzkk.dao.impl;

import com.zzkk.bean.UserBean;
import com.zzkk.util.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegUserDao {
    private static final String SQL = "insert into user (email ,usrname ,password ," +
            "uquestion , uanswer) values(?,?,?,?,?)";
    UserBean user = new UserBean();
    public RegUserDao(String email ,String usrname ,String password ,
                   String uquestion ,String uanswer){
        user.setEmail(email);
        user.setUsrname(usrname);
        user.setPassword(password);
        user.setUanswer(uanswer);
        user.setUquestion(uquestion);
    }

    public void registrar(){
        Connection conn = null;
        try {
            conn = C3p0Util.getConnection();
            PreparedStatement pStatement = conn.prepareStatement(SQL);
            pStatement.setString(1,user.getEmail());
            pStatement.setString(2,user.getUsrname());
            pStatement.setString(3,user.getPassword());
            pStatement.setString(4,user.getUquestion());
            pStatement.setString(5,user.getUanswer());
            if(pStatement.executeUpdate() == 1){
                System.out.println("insert success!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
