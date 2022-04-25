package com.bsuir.trpo.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.bsuir.trpo.constant.SQLConstant.*;

public class SqlInitializer {

    public boolean checkTablesInDB() {
        Connection connection = DataSource.getConnection();

        System.out.println("Проверяем таблицы базы данных...");
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USER);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Проверка успешно пройдена");
                return true;
            } else {
                System.err.println("База данных не существует.");
                System.out.println("Запускаю скрипт инициализации...\n");
                initializeTables(connection);
            }


        } catch (SQLException e) {
            System.err.println("Невозможно выполнить запрос. База данных не существует");
            System.out.println("Запускаю скрипт инициализации...\n");
            try {
                initializeTables(connection);
            } catch (SQLException ex) {
                System.err.println("Критическая ошибка. Не могу инициализировать базу данных...");
                System.err.println();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Невозможно закрыть соединение");
                System.err.println(e);
            }
        }

        return false;
    }

    private void initializeTables(Connection connection) throws SQLException {
        System.out.println("Создание таблицы пользователей...");
        PreparedStatement statement = connection.prepareStatement(CREATE_USER_TABLE);
        statement.execute();
        System.out.println("Таблица пользователей создана!\n");

        System.out.println("Создание таблицы списка студентов...");
        statement = connection.prepareStatement(CREATE_STUDENT_LIST);
        statement.execute();
        System.out.println("Таблица списка студентов создана!\n");
    }

}
