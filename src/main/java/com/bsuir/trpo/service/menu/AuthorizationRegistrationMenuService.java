package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.service.user.AuthorizationService;
import com.bsuir.trpo.service.user.RegistrationService;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;

public class AuthorizationRegistrationMenuService extends MenuService {

    @Override
    protected String userInput() {
        int userInput;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextInt();

                if (userInput < 1 || userInput > 3) {
                    throw new InputMismatchException();
                }

                break;
            } catch (InputMismatchException e) {
                System.err.println(NEED_NUMBER_1_3);
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
                    if (new AuthorizationService().authorize()) {
                        flag = false;
                    }
                    break;
                }
                case "2": {
                    if (new RegistrationService().register(false, false)) {
                        System.out.println(REGISTRATION_APPLICATION);
                    }
                    break;
                }
                case "3": {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    protected void printMenu() {
        System.out.println(CHOOSE_MENU_POINT);
        System.out.println(AUTHORIZE);
        System.out.println(REGISTER);
        System.out.println(END_WORK);
    }
}
