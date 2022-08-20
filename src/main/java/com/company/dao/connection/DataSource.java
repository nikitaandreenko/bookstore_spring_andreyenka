package com.company.dao.connection;


import com.company.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataSource {


    @Autowired
    public DataSource() {
    }

    private static final Logger log = LogManager.getLogger(DataSource.class);

    private Connection connection;

    public Connection getConnection() {

        String url_key = null;
        String user_key = null;
        String password_key = null;
        String typeOfConnection = ConfigurationManager.INSTANCE.getProperty("connection");

        if (typeOfConnection.equals("local")) {
            url_key = ConfigurationManager.INSTANCE.getProperty("db.local.url");
            user_key = ConfigurationManager.INSTANCE.getProperty("db.local.user");
            password_key = ConfigurationManager.INSTANCE.getProperty("db.local.password");
        } else if (typeOfConnection.equals("remote")) {
            url_key = ConfigurationManager.INSTANCE.getProperty("db.elephant.url");
            user_key = ConfigurationManager.INSTANCE.getProperty("db.elephant.user");
            password_key = ConfigurationManager.INSTANCE.getProperty("db.elephant.password");
        }
        if (connection == null) {
            try {
                log.info("Connection with database");
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url_key, user_key, password_key);
            } catch (ClassNotFoundException | SQLException e) {
                log.error(e);
            }
        }
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
