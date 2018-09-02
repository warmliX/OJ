package com.zzkk.action;

import com.mysql.cj.protocol.Resultset;
import com.zzkk.util.C3p0Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =  request.getParameter("email");
        Connection conn = null;
        try {
            conn = C3p0Util.getConnection();
            PreparedStatement ps = conn.prepareStatement("select uquestion from user where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                response.getWriter().print(rs.getString("uquestion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
