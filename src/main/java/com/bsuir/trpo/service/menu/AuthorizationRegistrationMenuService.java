package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.model.User;
import com.bsuir.trpo.service.AuthorizationService;
import com.bsuir.trpo.service.RegistrationService;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.ConsoleUserInterface.ADMIN_MENU_SERVICE_NAME;
import static com.bsuir.trpo.ConsoleUserInterface.USER_MENU_SERVICE_NAME;
import static com.bsuir.trpo.constant.ParamConstant.*;

public class AuthorizationRegistrationMenuService extends MenuService {

    private boolean authorize() {
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
        AuthorizationService authorizationService = new AuthorizationService();
        HashMap<String, Object> resultParams = authorizationService.execute(params);

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
                    "\n(Возможно у Вашего аккаунта пока нет доступа, обратитесь к администратору)");
        }

        return (boolean) resultParams.get(CONFIRMED);
    }

    public static boolean register(boolean isAdmin) {
        System.out.println("Введите логин: ");

        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();

        if (login == null || login.equals("") || password == null || password.equals("")) {
            System.err.println("Значения не могут быть пустыми!");
            return false;
        }

        RegistrationService registrationService = new RegistrationService();

        HashMap<String, Object> params = new HashMap<>();
        params.put(LOGIN, login);
        params.put(CLEAR_PASSWORD, password);
        params.put(ROLE, isAdmin);
        params.put(ACCESS, isAdmin);

        return (boolean) registrationService.execute(params).get(SUCCESS);
    }

    @Override
    protected int userInput() {
        int userInput;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextInt();

                if (userInput < 1 || userInput > 3) {
                    throw new InputMismatchException("Необходимо ввести числовое значение (1-3)");
                }

                break;
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
            }
        }
        return userInput;
    }

    @Override
    public void chooseMenuAction() {
        boolean flag = true;
        while (flag) {
            printMenu();
            int choose = userInput();

            switch (choose) {
                case 1: {
                    if (authorize()) {
                        flag = false;
                    }
                    break;
                }
                case 2: {
                    if (register(false)) {
                        System.out.println("ВНИМАНИЕ! На регистрацию была подана только заявка! Вы не можете авторизоваться сейчас!");
                    }
                    break;
                }
                case 3: {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    protected void printMenu() {
        System.out.println("Выберите вариант:");
        System.out.println("1: Авторизоваться");
        System.out.println("2: Зарегистрироваться");
        System.out.println("3: Завершить работу");
    }
}
