package com.zzkk.dao.impl;

import com.zzkk.bean.UserBean;
import com.zzkk.dao.IUserDaoFactory;

public class UserDaoFactory implements IUserDaoFactory {

    public UserDaoFactory(){}

    @Override
    public RegUserDao createRegUser(String email ,String usrname ,String password ,
                                 String uquestion ,String uanswer) {
        return new RegUserDao(email ,usrname ,password ,uquestion ,uanswer);
    }

    @Override
    public LoadUserDao createLoadUser(String email) {
        return new LoadUserDao(email);
    }

    @Override
    public UserBean createDelUser() {
        return new UserBean();
    }

    @Override
    public AlterUserDao createAlterUserDao(String email ,String password) {
        return new AlterUserDao(email ,password);
    }

    @Override
    public StateUserDao createStaUser(String email ,int start ,int total) {
        return new StateUserDao(email ,start ,total);
    }
}
