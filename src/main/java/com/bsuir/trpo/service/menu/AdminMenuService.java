package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.ConsoleUserInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.ConsoleUserInterface.*;

public class AdminMenuService extends AccountMenuService {

    @Override
    protected String userInput() {
        int userInput;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextInt();

                if (userInput < 0 || userInput > 2) {
                    throw new InputMismatchException();
                }

                break;
            } catch (InputMismatchException e) {
                System.err.println("Необходимо ввести числовое значение (0-2)");
            }
        }
        return userInput + "";
    }

    @Override
    public void chooseMenuAction() {
        boolean flag = true;
        while (flag) {
            printMenu();
            String choose = userInput();

            switch (choose) {
                case "1": {
                    ConsoleUserInterface.getInstance().setCurrentMenu(CONFIRM_ACCOUNT_MENU_SERVICE_NAME);
                    flag = false;
                    break;
                }
                case "2": {
                    ConsoleUserInterface.getInstance().setCurrentMenu(ACCOUNT_MANAGER_MENU_SERVICE_NAME);
                    flag = false;
                    break;
                }
                case "0": {
                    setActiveUser(null);
                    ConsoleUserInterface.getInstance().setCurrentMenu(AUTHORIZATION_REGISTRATION_MENU_SERVICE_NAME);
                    flag = false;
                }
            }
        }
    }

    @Override
    protected void printMenu() {
        System.out.println("Выберите вариант:");
        System.out.println("1: Заявки на подтверждение регистрации");
        System.out.println("2: Управление учетными записями пользователей");
        System.out.println("0: Выйти из системы");
    }
}
