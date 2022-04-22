package com.bsuir.trpo.service;

import com.bsuir.trpo.datasource.DataSource;
import com.bsuir.trpo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static com.bsuir.trpo.constant.ParamConstant.*;
import static com.bsuir.trpo.constant.SQLConstant.SELECT_FROM_USER_BY_LOGIN;

public class AuthorizationService implements ActionService {

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        Connection connection = DataSource.getConnection();
        String login = (String) params.get(LOGIN);
        String clearPassword = (String) params.get(CLEAR_PASSWORD);

        User user = new User();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USER_BY_LOGIN);

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user.setLogin(resultSet.getString(LOGIN));
                user.setSaltedHashPassword(resultSet.getString(SALTED_HASH_PASSWORD));
                user.setSalt(resultSet.getString(SALT));
                user.setRole(resultSet.getBoolean(ROLE));
                user.setAccess(resultSet.getBoolean(ACCESS));
            }

        } catch (SQLException e) {
            System.err.println("Невозможно выполнить запрос");
            System.err.println(e);
        }

        HashPasswordService hashPasswordService = new HashPasswordService();
        String saltedHashPassword = hashPasswordService.convertPasswordForComparing(clearPassword, user.getSalt());

        HashMap<String, Object> resultParams = new HashMap<>();
        resultParams.put(CONFIRMED,  saltedHashPassword.equals(user.getSaltedHashPassword()) && user.isAccess());
        resultParams.put(USER, user);

        return resultParams;
    }
}
