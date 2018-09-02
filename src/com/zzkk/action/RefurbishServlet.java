package com.zzkk.action;

import com.zzkk.dao.impl.RecordDaoFactory;
import com.zzkk.dao.impl.RefurbishDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RefurbishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request ,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();
        int page = Integer.parseInt(request.getParameter("page"));
        boolean flag = false;
        RecordDaoFactory factory = new RecordDaoFactory();
        RefurbishDao dao = factory.createRefurbishDao((String) request.getSession().getAttribute("usr"),page ,5);
        ArrayList<String[]> data = dao.sel();
        for (String[] strings:data
             ) {
           if(Integer.parseInt(strings[2]) < 2)
               flag = true;
        }

        String string = "";
        for(int i = 0 ; i < data.size() ; i++) {
            String[] strings = data.get(i);
            String language = new String();
            switch(strings[4]){
                case "1":language = "C";break;
                case "2":language = "C++";break;
                case "3":language = "JAVA";break;
            }
            string+="<tr>" +
                    "<td>"+strings[0]+"</td>" +
                    "<td><a href='"+path+"/com/zzkk/action/QuestionContentServlet?qid="+strings[0]+"'>"+strings[1]+"</a></td>" +
                    "<td>"+strings[2]+"</td>" +
                    "<td>"+strings[3]+"</td>" +
                    "<td>"+strings[5]+"</td>" +
                    "<td>"+language+"</td>" +
                    "<td>"+strings[6]+"</td>" +
                    "<td>"+strings[7]+"</td>" +
                    "</tr>";
        }

        if(flag){
            response.getWriter().write(string);
        } else
            response.getWriter().write(string+"#");
    }
}
