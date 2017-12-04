package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DbConnectionSource {

    INSTANCE;

    static {
        try {
            Class.forName(DbConfig.INSTANCE.getDriverClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DbConfig.INSTANCE.getDbUrl(),
                    DbConfig.INSTANCE.getUserName(),
                    DbConfig.INSTANCE.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
