package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bsuir.trpo.ConsoleUserInterface.ADMIN_MENU_SERVICE_NAME;

public class ConfirmAccountMenuService extends MenuService {

    @Override
    protected String userInput() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("0")) {
                return input;
            }

            if (input.matches("\\A\\w+\\s[12]\\z")) {
                return input;
            }

            System.err.println("Введенные данные не соответствуют требованиям! Попробуйте ещё раз");
        }
    }

    @Override
    public void chooseMenuAction() {
        boolean flag = true;
        while (flag) {
            printMenu();
            String userInput = userInput();

            switch (userInput) {
                case "0": {
                    ConsoleUserInterface.getInstance().setCurrentMenu(ADMIN_MENU_SERVICE_NAME);
                    flag = false;
                    break;
                }
                default: {
                    Pattern pattern = Pattern.compile("\\A\\w+");
                    Matcher matcher = pattern.matcher(userInput);
                    String login = "";
                    if (matcher.find()) {
                        login = userInput.substring(matcher.start(), matcher.end());
                    }

                    if (userInput.matches("\\A\\w+\\s[1]\\z")) {
                        UserDBService userDBService = new UserDBService();
                        userDBService.setAccessUser(login, true);
                    } else {
                        UserDBService userDBService = new UserDBService();
                        userDBService.removeUser(login);
                    }
                }
            }
        }
    }

    @Override
    protected void printMenu() {
        UserDBService userDBService = new UserDBService();
        List<User> userList = userDBService.getNotAccessedUsers();

        System.out.println("Введите логин пользователя, а затем через пробел значение 1 (разрешить доступ) или 2 (запретить доступ)");
        System.out.println("Внимание, при запрете доступа, заявка удаляется навсегда!");

        if (userList.isEmpty()) {
            System.out.println("\nСейчас нет заявок на регистрацию.");
        }

        for (User user : userList) {
            System.out.println("\n=======");
            System.out.println(user.getLogin());
            System.out.println("=======");
        }

        System.out.println("Введите 0, чтобы вернуться в предыдущее меню");
    }
}
