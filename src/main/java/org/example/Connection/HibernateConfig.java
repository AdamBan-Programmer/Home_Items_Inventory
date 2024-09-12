package org.example.Connection;

import org.example.Settings.AppSettings;

import java.util.Properties;

public class HibernateConfig {

    //config
    public Properties setProperties()
    {
        AppSettings settings = AppSettings.getInstance();

        Properties properties= new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://"+settings.getDatabaseHost()+":"+settings.getDatabasePort()+"/"+settings.getDatabaseName());
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.connection.username",settings.getDatabaseLogin());
        properties.setProperty("hibernate.connection.password", settings.getDatabasePassword());
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        return properties;
    }
}
