package com.bsuir.trpo.service.user;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.constant.LoggerMessageConstant.NEED_1_or_2;
import static com.bsuir.trpo.constant.LoggerMessageConstant.WARNING_DELETE_ACCOUNT;
import static com.bsuir.trpo.constant.ParamConstant.LOGIN;

public class RemoveAccountService implements UserService {

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

        if (user.getLogin() == null) {
            System.err.println("Пользователя " + login + " не существует!");
            return new HashMap<>();
        }

        if (!confirmDeletion(login)) {
            return new HashMap<>();
        }

        userDBService.removeUser(login);
        return new HashMap<>();
    }

    public boolean confirmDeletion(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(WARNING_DELETE_ACCOUNT);

        int userInput = 0;
        try {
            userInput = scanner.nextInt();
            if (userInput < 1 || userInput > 2) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println(NEED_1_or_2);
            return false;
        }

        return userInput == 1;
    }
}
