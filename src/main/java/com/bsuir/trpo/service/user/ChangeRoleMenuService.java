package com.bsuir.trpo.service.user;

import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;
import static com.bsuir.trpo.constant.ParamConstant.LOGIN;
import static com.bsuir.trpo.constant.ParamConstant.ROLE;

public class ChangeRoleMenuService implements UserService {

    public void changeUserRole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(INPUT_LOGIN);
        String login = scanner.nextLine();
        System.out.println(CHANGE_ROLE_HELPER);
        int role = 0;
        try {
            role = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println(NEED_NUMBER);
            return;
        }

        if (login == null || login.equals("") || role < 0 || role > 1) {
            System.err.println(VALUES_EMPTY_OR_INCORRECT);
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put(LOGIN, login);
        params.put(ROLE, role == 1);

        execute(params);
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        UserDBService userDBService = new UserDBService();
        String login = (String) params.get(LOGIN);
        boolean role = (boolean) params.get(ROLE);
        User user = userDBService.getUser(login);

        if (userDBService.getAllAdmins().size() == 1 && user.getRole() && !role) {
            System.err.println(CANNOT_CHANGE_ROLE);
            return new HashMap<>();
        }

        if (user.getLogin() == null) {
            System.err.println(USER_NOT_EXISTS);
            return new HashMap<>();
        }

        userDBService.setRole(login, role);
        System.out.println(ROLE_LOG + login + CHANGED_ROLE + (role ? ADMIN_LOG : USERA_LOG));

        return new HashMap<>();
    }
}
