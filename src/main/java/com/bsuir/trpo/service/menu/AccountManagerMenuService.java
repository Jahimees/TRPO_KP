package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;
import com.bsuir.trpo.service.user.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AccountManagerMenuService extends MenuService {

    private void viewAllAccounts() {
        UserDBService userDBService = new UserDBService();
        List<User> userList = userDBService.getAllUsers();

        System.out.println("Все пользовательские аккаунты:");
        System.out.println(" # |   Логин   | Админ ");
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
                System.err.println("Необходимо ввести числовое значение (0-4)");
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
        System.out.println("\nВыберите один из следующих пунктов:");
        System.out.println("1: Просмотреть все учетные записи пользователей");
        System.out.println("2: Создать новую учетную запись пользователя");
        System.out.println("3: Редактировать учетные записи пользователей (смена пароля, роли, блокировка)");
        System.out.println("4: Удаление учетных записей пользователей");
        System.out.println("0: Вернуться на прошлую страницу\n");
    }

    private void printEditMenu() {
        System.out.println("\nВыберите один из следующих вариантов:");
        System.out.println("1: Изменить пароль");
        System.out.println("2: Изменить роль");
        System.out.println("3: Заблокировать учетную запись");
        System.out.println("4: Разблокировать учетную запись");
        System.out.println("0: Вернуться в предыдущее меню");
    }
}
