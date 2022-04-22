package com.bsuir.trpo.service;

import com.bsuir.trpo.datasource.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import static com.bsuir.trpo.constant.ParamConstant.*;
import static com.bsuir.trpo.constant.SQLConstant.INSERT_INTO_USER;

public class RegistrationService implements ActionService {
    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        System.out.println("Регистрируем нового пользователя...");
        String login = (String) params.get(LOGIN);
        String clearPassword = (String) params.get(CLEAR_PASSWORD);
        boolean role = (boolean) params.get(ROLE);
        boolean access = (boolean) params.get(ACCESS);

        HashPasswordService hashPasswordService = new HashPasswordService();
        HashMap<String, Object> complexPassword = hashPasswordService.convertPasswordForStorage(clearPassword);

        String convertedPassword = (String) complexPassword.get(CONVERTED_PASSWORD);
        String salt = (String) complexPassword.get(SALT);

        Connection connection = DataSource.getConnection();

        HashMap<String, Object> resultParams = new HashMap<>();
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_USER);

            statement.setString(1, login);
            statement.setString(2, convertedPassword);
            statement.setString(3, salt);
            statement.setBoolean(4, role);
            statement.setBoolean(5, access);

            statement.execute();
            System.out.println("Регистрация прошла успешно!");
            resultParams.put(SUCCESS, true);
        } catch (SQLException e) {
            System.err.println("Невозможно зарегистрировать пользователя.");
            System.err.println(e);
            resultParams.put(SUCCESS, false);
        }

        return resultParams;
    }
}
