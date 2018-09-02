package com.zzkk.util;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class SessionListener implements HttpSessionListener {
    private static Logger logger = Logger.getLogger(String.valueOf(SessionListener.class));

    @Override
    public void sessionCreated(HttpSessionEvent event){

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event){
        HttpSession session = event.getSession();
        String sessionId = session.getId();
        ConcurrentHashMap<String ,String> usr = (ConcurrentHashMap<String, String>) event.getSession().getServletContext().getAttribute("usr");
        usr.remove(event.getSession().getAttribute("usr"));
        event.getSession().getServletContext().setAttribute("usr" ,usr);
        session.removeAttribute("usr");
        System.out.println("destory!!!!!!!!!!");
        session.invalidate();
    }
}
