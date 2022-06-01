package com.bsuir.trpo.service.user;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;

import java.util.HashMap;
import java.util.Scanner;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;
import static com.bsuir.trpo.constant.ParamConstant.ACCESS;
import static com.bsuir.trpo.constant.ParamConstant.LOGIN;

public class ChangeAccessService implements UserService {

    public void changeUserAccess(boolean access) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_LOGIN_FOR_CHANGE);
        String login = scanner.nextLine();

        if (login == null || login.equals("")) {
            System.err.println(VALUE_CANNOT_BE_EMPTY);
            return;
        }

        if (ConsoleUserInterface.getActiveAdminUser().getLogin().equals(login)) {
            System.err.println(CANNOT_CHANGE_ACCESS);
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put(LOGIN, login);
        params.put(ACCESS, access);

        execute(params);
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        UserDBService userDBService = new UserDBService();
        String login = (String) params.get(LOGIN);
        boolean access = (boolean) params.get(ACCESS);

        User user = userDBService.getUser(login);

        if (user.getLogin() == null) {
            System.err.println(USER_NOT_EXISTS);
            return new HashMap<>();
        }

        userDBService.setAccessUser(login, access);

        System.out.println(USER_LOG + login + (access ? UNBLOCKED : BLOCKED));
        return new HashMap<>();
    }
}
