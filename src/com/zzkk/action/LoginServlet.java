package com.zzkk.action;

import com.zzkk.dao.impl.LoadUserDao;
import com.zzkk.bean.UserBean;
import com.zzkk.dao.impl.UserDaoFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        String path = request.getContextPath();
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("name");
        String password = request.getParameter("password");
        ServletContext context = this.getServletContext();
        ConcurrentHashMap<String ,String> usr = (ConcurrentHashMap<String, String>) context.getAttribute("usr");
        boolean flag = false;
        if(usr == null) {
            flag = true;
        } else{
            if(usr.get(email) == null)
                flag = true;
            else
                flag = false;
        }
        if(flag == false){
            response.sendRedirect(path+"/Web/login/index.jsp?error=yes");
        } else {
            UserDaoFactory factory = new UserDaoFactory();
            LoadUserDao Luser = factory.createLoadUser(email);
            UserBean user = Luser.Load();
            response.getWriter().write(user.getPassword());
            if (user.getPassword().equals(password)) {
                usr = new ConcurrentHashMap<>();
                usr.put(email, email);
                context.setAttribute("usr", usr);
                request.getSession().setAttribute("usr", email);
                response.sendRedirect(path+"/Web/index.jsp");
            } else {
                response.sendRedirect(path+"/Web/login/index.jsp?error=yes");
            }
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        doPost(request ,response);
    }
}
