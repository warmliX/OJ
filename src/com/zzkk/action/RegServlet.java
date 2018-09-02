package com.zzkk.action;

import com.zzkk.dao.impl.RegUserDao;
import com.zzkk.dao.impl.UserDaoFactory;
import com.zzkk.util.UserDir;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String usrname = request.getParameter("name");
        String password = request.getParameter("pass");
        String uquestion = request.getParameter("que");
        String uanswer = request.getParameter("ans");

        UserDaoFactory factory = new UserDaoFactory();
        RegUserDao userDao = factory.createRegUser(email ,usrname ,password ,uquestion ,uanswer);
        userDao.registrar();
        UserDir.mkdir(email);
        response.sendRedirect(request.getContextPath()+"/Web/login/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request ,response);
    }
}
