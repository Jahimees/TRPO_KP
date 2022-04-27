package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;
import com.bsuir.trpo.service.user.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;

public class AccountManagerMenuService extends MenuService {

    private void viewAllAccounts() {
        UserDBService userDBService = new UserDBService();
        List<User> userList = userDBService.getAllUsers();

        System.out.println(ALL_USER_ACCOUNTS);
        System.out.println(HEADER_USER_ACCOUNTS);
        for (int i = 1; i <= userList.size(); i++) {
            User user = userList.get(i - 1);
            System.out.println(" " + i + " | " + user.getLogin() + " | " + user.getRole());
        }
    }

    @Override
    protected String userInput() {
        int userInput;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextInt();

                if (userInput < 0 || userInput > 4) {
                    throw new InputMismatchException();
                }

                break;
            } catch (InputMismatchException e) {
                System.err.println(NEED_NUMBER_0_4);
            }
        }
        return userInput + "";
    }

    @Override
    public void chooseMenuAction() {
        boolean flag = true;
        while (flag) {
            printMenu();
            String userInput = userInput();

            switch (userInput) {
                case "1": {
                    viewAllAccounts();
                    break;
                }
                case "2": {
                    new RegistrationService().register(false, true);
                    break;
                }
                case "3": {
                    boolean innerFlag = true;

                    while (innerFlag) {
                        printEditMenu();
                        String userInputEditMenu = userInput();

                        switch (userInputEditMenu) {
                            case "1": {
                                new ChangePasswordService().changePassword();
                                break;
                            }
                            case "2": {
                                new ChangeRoleMenuService().changeUserRole();
                                break;
                            }
                            case "3": {
                                new ChangeAccessService().changeUserAccess(false);
                                break;
                            }
                            case "4": {
                                new ChangeAccessService().changeUserAccess(true);
                                break;
                            }
                            case "0": {
                                innerFlag = false;
                            }
                        }
                    }

                    break;
                }
                case "4": {
                    new RemoveAccountService().removeAccount();
                    break;
                }
                case "0": {
                    ConsoleUserInterface.getInstance().setCurrentMenu(ConsoleUserInterface.ADMIN_MENU_SERVICE_NAME);
                    flag = false;
                }
            }
        }
    }

    @Override
    protected void printMenu() {
        System.out.println(CHOOSE_MENU_POINT);
        System.out.println(SHOW_ALL_USER_ACCOUNTS);
        System.out.println(CREATE_NEW_USER_ACCOUNT);
        System.out.println(EDIT_USER_ACCOUNT);
        System.out.println(DELETE_USER_ACCOUNT);
        System.out.println(PREVIOUS_MENU);
    }

    private void printEditMenu() {
        System.out.println(CHOOSE_MENU_POINT);
        System.out.println(CHANGE_PASSWORD);
        System.out.println(CHANGE_ROLE);
        System.out.println(BLOCK_ACCOUNT);
        System.out.println(UNBLOCK_ACCOUNT);
        System.out.println(PREVIOUS_MENU);
    }
}
