package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.ConsoleUserInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.ConsoleUserInterface.*;
import static com.bsuir.trpo.constant.LoggerMessageConstant.*;

public class AdminMenuService extends AccountMenuService {

    @Override
    protected String userInput() {
        int userInput;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextInt();

                if (userInput < 0 || userInput > 3) {
                    throw new InputMismatchException();
                }

                break;
            } catch (InputMismatchException e) {
                System.err.println(NEED_NUMBER_0_3);
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
                case "3": {
                    ConsoleUserInterface.getInstance().setCurrentMenu(STUDENT_LIST_MANAGER_MENU_SERVICE_NAME);
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
        System.out.println(CHOOSE_MENU_POINT);
        System.out.println(CONFIRM_APPLICATIONS);
        System.out.println(MANAGE_ACCOUNTS);
        System.out.println(MANAGE_STUDENTS);
        System.out.println(SYSTEM_EXIT);
    }
}
