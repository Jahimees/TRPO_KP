package com.bsuir.trpo.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataSource {

    private static Connection connection;
    private static final String connectionString = "jdbc:sqlite:";
    private static final String dbName = "src/kp.s3db";

    private DataSource() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");

            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(connectionString + dbName);
            }
        } catch (SQLException ex) {
            System.out.println("Невозможно получить соединение... Файл базы данных не найден");
        } catch (ClassNotFoundException e) {
            System.out.println("Невозможно определить драйвер org.sqlite.JDBC");
        }

        return connection;
    }
}
