package com.bsuir.trpo.service;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;
import com.bsuir.trpo.service.menu.AccountMenuService;

import java.util.HashMap;
import java.util.Scanner;

import static com.bsuir.trpo.ConsoleUserInterface.ADMIN_MENU_SERVICE_NAME;
import static com.bsuir.trpo.ConsoleUserInterface.USER_MENU_SERVICE_NAME;
import static com.bsuir.trpo.constant.ParamConstant.*;

public class AuthorizationService implements ActionService {

    public boolean authorize() {
        System.out.println("Пожалуйста, авторизуйтесь.");

        System.out.println("Введите логин: ");

        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();

        HashMap<String, Object> params = new HashMap<>();
        params.put(LOGIN, login);
        params.put(CLEAR_PASSWORD, password);

        System.out.println("Проверяем введенные данные...");
        HashMap<String, Object> resultParams = execute(params);

        if ((boolean) resultParams.get(CONFIRMED)) {
            System.out.println("Авторизация прошла успешно!");

            ConsoleUserInterface instance = ConsoleUserInterface.getInstance();
            User user = ((User) resultParams.get(USER));
            if (user.getRole()) {
                instance.setCurrentMenu(ADMIN_MENU_SERVICE_NAME);
            } else {
                instance.setCurrentMenu(USER_MENU_SERVICE_NAME);
            }
            ((AccountMenuService) instance.getCurrentMenu()).setActiveUser(user);
        } else {
            System.out.println("Логин или пароль неверны! Попробуйте ещё раз! " +
                    "\n(Возможно у Вашего аккаунта нет доступа, обратитесь к администратору)");
        }

        return (boolean) resultParams.get(CONFIRMED);
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        String login = (String) params.get(LOGIN);
        String clearPassword = (String) params.get(CLEAR_PASSWORD);

        UserDBService userDBService = new UserDBService();
        User user = userDBService.getUser(login);

        HashPasswordService hashPasswordService = new HashPasswordService();
        String saltedHashPassword = hashPasswordService.convertPasswordForComparing(clearPassword, user.getSalt());

        HashMap<String, Object> resultParams = new HashMap<>();
        resultParams.put(CONFIRMED,  saltedHashPassword.equals(user.getSaltedHashPassword()) && user.isAccess());
        resultParams.put(USER, user);

        return resultParams;
    }
}
