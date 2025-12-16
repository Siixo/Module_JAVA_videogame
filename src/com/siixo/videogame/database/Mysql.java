package com.siixo.videogame.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.siixo.videogame.Env.*;

public class Mysql {
    private static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}


