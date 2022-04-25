package com.bsuir.trpo.service.user;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;
import com.bsuir.trpo.service.ActionService;

import java.util.HashMap;
import java.util.Scanner;

import static com.bsuir.trpo.constant.ParamConstant.LOGIN;

public class RemoveAccountService implements ActionService {

    public void removeAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВведите логин аккаунта, который будет удален");

        String login = scanner.nextLine();

        if (login == null || login.equals("")) {
            System.err.println("Значения не могут быть пустыми!");
            return;
        }

        if (ConsoleUserInterface.getActiveAdminUser().getLogin().equals(login)) {
            System.err.println("Невозможно удалить самого себя");
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put(LOGIN, login);

        execute(params);
    }


    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        String login = (String) params.get(LOGIN);

        UserDBService userDBService = new UserDBService();
        User user = userDBService.getUser(login);

        if (user == null) {
            System.err.println("Пользователя " + login + " не существует!");
            return new HashMap<>();
        }

        userDBService.removeUser(login);
        return new HashMap<>();
    }
}
