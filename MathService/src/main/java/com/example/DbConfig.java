package com.example;

import java.io.IOException;
import java.util.Properties;

public enum DbConfig {

    INSTANCE;

    private Properties properties;

    DbConfig() {
        properties = new Properties();

        try {
            properties.load(DbConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDriverClass() {
        return properties.getProperty("driver.class");
    }

    public String getDbUrl() {
        return "jdbc:mysql://" + properties.getProperty("db.host") + ":" +
                properties.getProperty("db.port") + "/" +
                properties.getProperty("db.name");
    }

    public String getUserName() {
        return properties.getProperty("db.user");
    }

    public String getPassword() {
        return properties.getProperty("db.password");
    }

}
