package com.bsuir.trpo.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;
import static com.bsuir.trpo.constant.SQLConstant.*;

public class SqlInitializer {

    public boolean isTablesOk() {
        Connection connection = DataSource.getConnection();

        System.out.println(CHECKING_DB_TABLES);
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USER);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println(CHECKING_SUCCESSFUL);
                return true;
            } else {
                System.err.println(DB_NOT_EXISTS);
                System.out.println(STARTING_INITIALIZATION_SCRIPT);
                initializeTables(connection);
            }


        } catch (SQLException e) {
            System.err.println(CANNOT_EXECUTE_QUERY_DB_NOT_EXISTS);
            System.out.println(STARTING_INITIALIZATION_SCRIPT);
            try {
                initializeTables(connection);
            } catch (SQLException ex) {
                System.err.println(CRITICAL_ERROR_CANNOT_INITIALIZE_DB);
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(CANNOT_CLOSE_CONNECTION);
            }
        }

        return false;
    }

    private void initializeTables(Connection connection) throws SQLException {
        System.out.println(CREATING_USER_TABLE);
        PreparedStatement statement = connection.prepareStatement(CREATE_USER_TABLE);
        statement.execute();
        System.out.println(USER_TABLE_CREATED);

        System.out.println(CREATING_STUDENT_TABLE);
        statement = connection.prepareStatement(CREATE_STUDENT_LIST);
        statement.execute();
        System.out.println(STUDENT_TABLE_CREATED);
    }

}
