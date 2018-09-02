package com.zzkk.action;

import com.zzkk.bean.PageBean;
import com.zzkk.dao.impl.StateUserDao;
import com.zzkk.dao.impl.UserDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request ,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();
        String email = (String) request.getSession().getAttribute("usr");
        if(email == null){
            response.sendRedirect(path+"/Web/login/index.jsp");
            return;
        }
        int page = Integer.parseInt(request.getParameter("page"));
        UserDaoFactory factory = new UserDaoFactory();
        StateUserDao dao = factory.createStaUser((String) request.getSession()
                .getAttribute("usr") ,page ,5);
        PageBean bean = dao.sel();
        if(bean == null){
            request.setAttribute("bean" ,bean);
            request.getRequestDispatcher("/Web/record.jsp").forward(request ,response);
            return;
        } else{
            bean.setCurrentPage(page);
            request.setAttribute("bean" ,bean);
            request.getRequestDispatcher("/Web/record.jsp").forward(request ,response);
        }
    }
}

