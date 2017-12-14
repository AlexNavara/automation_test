package com.example.config;

import java.io.IOException;
import java.util.Properties;

public enum ConfigProvider
{
        INSTANCE;

        private Properties properties;

        ConfigProvider() {
                properties = new Properties();
                try
                {
                        properties.load(ConfigProvider.class.getClassLoader().getResourceAsStream("api.properties"));
                        properties.putAll(System.getProperties());

                }
                catch (IOException e)
                {
                        e.printStackTrace();
                }
        }

        public String getServerUrl() {
                String port = properties.getProperty("server.port");

                return properties.getProperty("server.scheme") + "://" +
                      properties.getProperty("server.host") +
                      (port == null || port.isEmpty() ? "" : ":" + port);
        }

}
