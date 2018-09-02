package com.zzkk.dao;

import com.zzkk.dao.impl.AlterUserDao;
import com.zzkk.dao.impl.LoadUserDao;
import com.zzkk.dao.impl.RegUserDao;
import com.zzkk.dao.impl.StateUserDao;
import com.zzkk.bean.UserBean;

public interface IUserDaoFactory {
    public abstract RegUserDao createRegUser(String email, String usrname, String password,
                                             String uquestion, String uanswer);

    public LoadUserDao createLoadUser(String email);

    public UserBean createDelUser();

    public AlterUserDao createAlterUserDao(String email ,String password);

    public StateUserDao createStaUser(String email ,int start ,int total);
}
