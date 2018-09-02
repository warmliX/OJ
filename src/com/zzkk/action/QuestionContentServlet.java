package com.zzkk.action;

import com.zzkk.bean.ContactBean;
import com.zzkk.dao.impl.QuestionContentDao;
import com.zzkk.dao.impl.QuestionDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request ,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int qid = Integer.parseInt(request.getParameter("qid"));
        QuestionDaoFactory factory = new QuestionDaoFactory();
        QuestionContentDao dao = factory.createQuestionContentDao(qid);
        ContactBean bean = dao.sel();
        request.setAttribute("bean" ,bean);
        request.setAttribute("qid" ,qid);
        request.getRequestDispatcher("/Web/contact.jsp").forward(request ,response);
    }
}
