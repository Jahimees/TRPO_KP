package com.bsuir.trpo.datasource;

import com.bsuir.trpo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;
import static com.bsuir.trpo.constant.ParamConstant.*;
import static com.bsuir.trpo.constant.SQLConstant.*;

public class UserDBService {

    public User getUser(String login) {
        Connection connection = DataSource.getConnection();

        User user = new User();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USER_BY_LOGIN);

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
               fillUserFields(user, resultSet);
            }

        } catch (SQLException e) {
            System.err.println(CANNOT_EXECUTE_QUERY);
            return new User();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(CANNOT_CLOSE_CONNECTION);
                return new User();
            }
        }

        return user;
    }

    public List<User> getAllUsers() {
        Connection connection = DataSource.getConnection();
        List<User> userList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USER);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                fillUserFields(user, resultSet);

                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println(CANNOT_EXECUTE_QUERY);
            return new ArrayList<>();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(CANNOT_CLOSE_CONNECTION);
                return new ArrayList<>();
            }
        }

        return userList;
    }

    public List<User> getAllAdmins() {
        Connection connection = DataSource.getConnection();
        List<User> userList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ADMINS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                fillUserFields(user, resultSet);

                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println(CANNOT_EXECUTE_QUERY);
            return new ArrayList<>();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(CANNOT_CLOSE_CONNECTION);
                return new ArrayList<>();
            }
        }

        return userList;
    }

    public List<User> getNotAccessedUsers() {
        List<User> userList = new ArrayList<>();
        Connection connection = DataSource.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USER_WITHOUT_ACCESS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                fillUserFields(user, resultSet);

                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println(CANNOT_EXECUTE_QUERY);
            return new ArrayList<>();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(CANNOT_CLOSE_CONNECTION);
                return new ArrayList<>();
            }
        }

        return userList;
    }

    public void setAccessUser(String userLogin, boolean access) {
        Connection connection = DataSource.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(SET_ACCESS_BY_LOGIN);
            statement.setBoolean(1, access);
            statement.setString(2, userLogin);

            boolean isSuccess = statement.execute();

            if (isSuccess) {
                System.out.println(userLogin + USER_ACCESSED);
            } else {
                System.out.println(userLogin + NOT_EXISTS);
            }
        } catch (SQLException e) {
            System.err.println(CANNOT_EXECUTE_QUERY);
            return;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(CANNOT_CLOSE_CONNECTION);
                return;
            }
        }
    }

    public void removeUser(String userLogin) {
        Connection connection = DataSource.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setString(1, userLogin);

            statement.execute();
            System.out.println(USER_DELETED);
        } catch (SQLException e) {
            System.err.println(CANNOT_EXECUTE_QUERY);
            return;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(CANNOT_CLOSE_CONNECTION);
                return;
            }
        }
    }

    public void changePassword(String login, String saltedHashPassword, String salt) {
        Connection connection = DataSource.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD);
            statement.setString(1, saltedHashPassword);
            statement.setString(2, salt);
            statement.setString(3, login);

            statement.execute();
        } catch (SQLException e) {
            System.err.println(CANNOT_EXECUTE_QUERY);
            return;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(CANNOT_CLOSE_CONNECTION);
                return;
            }
        }
    }

    public void setRole(String login, boolean role) {
        Connection connection = DataSource.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE);
            statement.setBoolean(1, role);
            statement.setString(2, login);

            statement.execute();
        } catch (SQLException e) {
            System.err.println(CANNOT_EXECUTE_QUERY);
            return;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(CANNOT_CLOSE_CONNECTION);
                return;
            }
        }
    }

    private void fillUserFields(User user, ResultSet resultSet) throws SQLException {
        user.setLogin(resultSet.getString(LOGIN));
        user.setSaltedHashPassword(resultSet.getString(SALTED_HASH_PASSWORD));
        user.setSalt(resultSet.getString(SALT));
        user.setRole(resultSet.getBoolean(ROLE));
        user.setAccess(resultSet.getBoolean(ACCESS));
    }
}
