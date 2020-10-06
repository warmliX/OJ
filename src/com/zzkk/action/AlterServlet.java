package com.zzkk.action;

import com.zzkk.dao.impl.AlterUserDao;
import com.zzkk.dao.impl.UserDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
//dsfff
public class AlterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("name");
        String ans = request.getParameter("ans");
        String password = request.getParameter("password");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        UserDaoFactory factory = new UserDaoFactory();
        AlterUserDao dao = factory.createAlterUserDao(email ,password);
        try {
            String answer = dao.sel();
            System.out.println(answer);
            if(answer==null){
                response.getWriter().write("<script type='text/javascript'>if(confirm('用户名错误'))window.history.go(-1);" +
                        "else window.history.go(-1);</script>");
            } else{
                if(ans.equals(answer)){
                    dao.update();
                    response.sendRedirect(request.getContextPath()+"/Web/login/index.jsp");
                }else{
                    response.getWriter().write("<script type='text/javascript'>if(confirm('密保答案错误'))window.history.go(-1);" +
                            "else window.history.go(-1);</script>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request ,response);
    }
}
