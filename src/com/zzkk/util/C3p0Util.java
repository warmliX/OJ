package com.zzkk.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Util {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    private C3p0Util(){}

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    public static ComboPooledDataSource getDataSource(){
        return dataSource;
    }

    public static void closeConnection(Connection conn) throws SQLException {
        if(conn != null){
            conn.close();
        }
    }
}
