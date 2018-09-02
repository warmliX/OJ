package com.zzkk.dao.impl;

import com.zzkk.bean.UserBean;
import com.zzkk.util.C3p0Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadUserDao {
    private static final String SQL = "select password from user where email=?";
    private UserBean user = new UserBean();
    public LoadUserDao(String email){
        user.setEmail(email);
    }

    public UserBean Load(){
        try {
            Connection conn = C3p0Util.getConnection();
            PreparedStatement pStatment = conn.prepareStatement(SQL);
            pStatment.setString(1 ,user.getEmail());
            ResultSet resultset = pStatment.executeQuery();
            while(resultset.next()){
                user.setPassword(resultset.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
