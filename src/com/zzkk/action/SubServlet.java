package com.zzkk.action;

import com.zzkk.dao.impl.RecordDao;
import com.zzkk.dao.impl.RecordDaoFactory;
import com.zzkk.util.Executor;
import com.zzkk.util.TestFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SubServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int qid = Integer.parseInt(request.getParameter("qid"));
        String text = request.getParameter("text");
        String language = request.getParameter("language");
        int lan = 0;
        switch (language) {
            case "c":
                lan = 1;
                break;
            case "c++":
                lan = 2;
                break;
            case "java":
                lan = 3;
                break;
        }

        RecordDaoFactory factory = new RecordDaoFactory();
        RecordDao dao = factory.createRecordDao((String) request.getSession().getAttribute("usr"), qid, 0, lan, text.length());
        int result = dao.ins();

        TestFile file = new TestFile((String) request.getSession().getAttribute("usr"),
                language ,text);
        FutureTask<Boolean> task = new FutureTask<>(file);
        new Thread(task).start();
        try {
            boolean isSave = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath()+"/com/zzkk/action/RecordServlet?page=-1");

        Executor executor = new Executor(qid ,(String) request.getSession().getAttribute("usr"),language ,text ,result);
        new Thread(executor).start();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request ,response);
    }
}
