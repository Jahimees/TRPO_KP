package com.bsuir.trpo.service.user;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;
import com.bsuir.trpo.service.ActionService;

import java.util.HashMap;
import java.util.Scanner;

import static com.bsuir.trpo.constant.ParamConstant.*;

public class ChangeAccessService implements ActionService {

    public void changeUserAccess(boolean access) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВведите логин аккаунта, на котором будет изменен доступ");
        String login = scanner.nextLine();

        if (login == null || login.equals("")) {
            System.err.println("Значения не могут быть пустыми!");
            return;
        }

        if (ConsoleUserInterface.getActiveAdminUser().getLogin().equals(login)) {
            System.err.println("Невозможно изменить доступ самому себе!");
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

        if (user == null) {
            System.err.println("Пользователя " + user.getLogin() + " не существует!");
            return new HashMap<>();
        }

        userDBService.setAccessUser(login, access);

        System.out.println("Пользователь " + login + (access ? " разблокирован" : " заблокирован"));
        return new HashMap<>();
    }
}
