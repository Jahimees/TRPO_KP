package com.bsuir.trpo.service.user;

import com.bsuir.trpo.datasource.DataSource;
import com.bsuir.trpo.service.HashPasswordService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;
import static com.bsuir.trpo.constant.ParamConstant.*;
import static com.bsuir.trpo.constant.SQLConstant.INSERT_INTO_USER;

public class RegistrationService implements UserService {

    public boolean register(boolean isAdmin, boolean access) {
        System.out.println(INPUT_LOGIN);

        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        System.out.println(INPUT_PASSWORD);
        String password = scanner.nextLine();

        if (login == null || login.equals("") || password == null || password.equals("")) {
            System.err.println(VALUE_CANNOT_BE_EMPTY);
            return false;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put(LOGIN, login);
        params.put(CLEAR_PASSWORD, password);
        params.put(ROLE, isAdmin);
        params.put(ACCESS, access);

        return (boolean) execute(params).get(SUCCESS);
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
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
            System.out.println(REGISTER_SUCCESSFUL);
            resultParams.put(SUCCESS, true);
        } catch (SQLException e) {
            System.err.println(USER_ALREADY_EXISTS);
            resultParams.put(SUCCESS, false);
        }

        return resultParams;
    }
}
