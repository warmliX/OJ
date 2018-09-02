package com.zzkk.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zzkk.util.C3p0Util;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class PoolTest {
        public static void main(String[] args) throws Exception
        {
            try {
                Connection conn = C3p0Util.getConnection();
                conn.close();
                ComboPooledDataSource ds  = C3p0Util.getDataSource();
                //System.out.println(ds.getInitialPoolSize());
                //Thread.sleep(1000)
                System.out.println(ds.getNumBusyConnections());//正在使用
                System.out.println(ds.getNumIdleConnections());//空闲连接
                System.out.println(ds.getNumUnclosedOrphanedConnectionsAllUsers());//未关闭连接
                System.out.println(ds.getNumConnectionsDefaultUser());//总连接s
                //conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
}
