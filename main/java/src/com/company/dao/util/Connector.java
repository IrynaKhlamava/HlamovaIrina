package com.company.dao.util;

import com.company.config.annotation.ConfigProperty;
import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

//@Component
public class Connector {
//    //все настройки к базе данных нужно вывести в проперти
//    @ConfigProperty
//    private String URL;
//    @ConfigProperty
//    private String NAME;
//    @ConfigProperty
//    private String PASSWORD;
//    @ConfigProperty
//    private String DRIVER;

    //    @Autowired
//    private Connector instance;//должен через депендеси инжекшен быть
//
//    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(Connector.class.getName());
        private static final String URL = "jdbc:mysql://127.0.0.1:3306/hotel?autoReconnect=true&useSSL=false";
    private static final String NAME = "root";
    private static final String PASSWORD = "1111";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    @Autowired
    private static Connector instance;//должен через депендеси инжекшен быть
    private Connection connection;

//    public Connector() {
//    }

    private Connector(){
      connect();
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
            return connection;
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static Connector getInstance() {

        if (instance == null){
            instance = new Connector();
        }
        return instance;
    }

    private void connect() {
        try {
            Class.forName(DRIVER);//регистрируем драйвер
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);//устанавливаем соединение
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.warning(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
