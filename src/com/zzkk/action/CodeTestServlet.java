package com.zzkk.action;

import com.zzkk.util.ContExecutor;
import com.zzkk.util.TestFile;
import com.zzkk.util.UserDir;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
//fhfhfdsfds
public class CodeTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        UserDir.mkdir("OJ"+request.getSession().getId());
        System.out.println("111");
        String code = request.getParameter("code");
        String language = request.getParameter("language");
        String paramter = request.getParameter("paramter");

        TestFile file = new TestFile("OJ"+request.getSession().getId() ,language ,code);

        FutureTask<Boolean> task = new FutureTask<>(file);
        new Thread(task).start();
        try {
            boolean isSave = task.get(5,TimeUnit.SECONDS);
            if(!isSave){
                response.getWriter().write("服务器异常(by saving exception)");
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            response.getWriter().write("服务器异常(by out of saving time exception)");
            e.printStackTrace();
            return;
        }

        FutureTask<String> task1 = new FutureTask<>(new ContExecutor(language ,paramter ,
                "OJ"+request.getSession().getId()));
        new Thread(task1).start();
        try {
            String end = task1.get();
            int memIndex = end.indexOf("mem=");
            int timeIndex = end.indexOf("time=");
            if(memIndex!=-1||timeIndex!=-1) {
                String outcome = end.toString().substring(0, memIndex);
                outcome += "\n\ntime:" + end.substring(timeIndex + 5, end.length()) + "ms";
                outcome += "\nmemory:" + end.substring(memIndex + 4, timeIndex) + "kb";
                response.getWriter().write(outcome);
            }else{
                response.getWriter().write(end);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request ,response);
    }
}
