package com.bsuir.trpo.service.user;

import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;
import com.bsuir.trpo.service.HashPasswordService;

import java.util.HashMap;
import java.util.Scanner;

import static com.bsuir.trpo.constant.LoggerMessageConstant.PASSWORD_CHANGED;
import static com.bsuir.trpo.constant.LoggerMessageConstant.USER_NOT_EXISTS;
import static com.bsuir.trpo.constant.ParamConstant.*;

public class ChangePasswordService implements UserService {

    public void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВведите логин аккаунта, на котором будет изменен пароль");

        String login = scanner.nextLine();
        System.out.println("Введите новый пароль");
        String password = scanner.nextLine();

        if (login == null || login.equals("") || password == null || password.equals("")) {
            System.err.println("Значения не могут быть пустыми!");
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put(LOGIN, login);
        params.put(CLEAR_PASSWORD, password);

        execute(params);
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        HashPasswordService hashPasswordService = new HashPasswordService();
        String login = (String) params.get(LOGIN);
        String password = (String) params.get(CLEAR_PASSWORD);

        UserDBService userDBService = new UserDBService();
        User user = userDBService.getUser(login);

        if (user == null) {
            System.err.println(USER_NOT_EXISTS);
            return new HashMap<>();
        }

        HashMap<String, Object> convertedPassword = hashPasswordService.convertPasswordForStorage(password);

        userDBService.changePassword(login, (String) convertedPassword.get(CONVERTED_PASSWORD), (String) convertedPassword.get(SALT));
        System.out.println(PASSWORD_CHANGED);

        return new HashMap<>();
    }
}
