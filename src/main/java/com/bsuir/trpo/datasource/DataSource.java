package com.bsuir.trpo.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.bsuir.trpo.constant.LoggerMessageConstant.DB_DRIVER_NOT_DEFINED;
import static com.bsuir.trpo.constant.LoggerMessageConstant.DB_FILE_NOT_FOUND;

public final class DataSource {

    private static Connection connection;
    private static final String connectionString = "jdbc:sqlite:";
    private static final String dbName = "src/kp.s3db";
    private static final String driverName = "org.sqlite.JDBC";

    private DataSource() {
    }

    public static Connection getConnection() {
        try {
            Class.forName(driverName);

            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(connectionString + dbName);
            }
        } catch (SQLException ex) {
            System.out.println(DB_FILE_NOT_FOUND);
        } catch (ClassNotFoundException e) {
            System.out.println(DB_DRIVER_NOT_DEFINED);
        }

        return connection;
    }
}
