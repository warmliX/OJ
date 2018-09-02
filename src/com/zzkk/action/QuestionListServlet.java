package com.zzkk.action;

import com.zzkk.bean.PageBean;
import com.zzkk.dao.impl.QuestionDaoFactory;
import com.zzkk.dao.impl.QuestionListDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        QuestionDaoFactory factory = new QuestionDaoFactory();
        QuestionListDao dao = factory.createQuestionListDao((20*(page-1)) ,20);
        PageBean bean = dao.sel();
        bean.setCurrentPage(page);
        request.setAttribute("bean" ,bean);
        request.getRequestDispatcher("/Web/practice.jsp").forward(request ,response);
    }
}
