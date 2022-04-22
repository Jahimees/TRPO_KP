package com.bsuir.trpo;

import com.bsuir.trpo.datasource.SqlInitializer;
import com.bsuir.trpo.service.menu.AdminMenuService;
import com.bsuir.trpo.service.menu.AuthorizationRegistrationMenuService;
import com.bsuir.trpo.service.menu.MenuService;
import com.bsuir.trpo.service.menu.UserMenuService;

import static com.bsuir.trpo.service.menu.AuthorizationRegistrationMenuService.register;

public final class ConsoleUserInterface {

    private static final MenuService ADMIN_MENU_SERVICE = new AdminMenuService();
    private static final MenuService USER_MENU_SERVICE = new UserMenuService();
    private static final MenuService AUTHORIZATION_REGISTRATION_MENU_SERVICE = new AuthorizationRegistrationMenuService();
    public static final String ADMIN_MENU_SERVICE_NAME = "adminMenuService";
    public static final String USER_MENU_SERVICE_NAME = "userMenuService";
    public static final String AUTHORIZATION_REGISTRATION_MENU_SERVICE_NAME = "authorizationRegistrationMenuService";

    private static ConsoleUserInterface instance;
    private static MenuService currentMenu;

    private ConsoleUserInterface() {
    }

    public static ConsoleUserInterface getInstance() {
        if (instance == null) {
            instance = new ConsoleUserInterface();
        }

        return instance;
    }

    public MenuService getCurrentMenu() {
        return currentMenu;
    }

    public void start() {
        printHelloWord();
        if (!connectionIsOk()) {
            System.out.println("Система была только что создана! Пожалуйста, зарегистрируйте аккаунт администратора!");
            while (!register(true)) ;
        }

        setCurrentMenu(AUTHORIZATION_REGISTRATION_MENU_SERVICE_NAME);

        while (true) {
            currentMenu.chooseMenuAction();
        }
    }

    private boolean connectionIsOk() {
        System.out.println("=============Проверка системы=============");
        SqlInitializer sqlInitializer = new SqlInitializer();
        boolean result = sqlInitializer.checkTablesInDB();
        System.out.println("=============Проверка завершена=============");
        return result;
    }

    private void printHelloWord() {
        System.out.println("\n--Добро пожаловать в программу распределения мест в общежитии!--\n");
    }

    public void setCurrentMenu(String currentMenuName) {
        switch (currentMenuName) {
            case ADMIN_MENU_SERVICE_NAME: {
                currentMenu = ADMIN_MENU_SERVICE;
                break;
            }
            case USER_MENU_SERVICE_NAME: {
                currentMenu = USER_MENU_SERVICE;
                break;
            }
            case AUTHORIZATION_REGISTRATION_MENU_SERVICE_NAME: {
                currentMenu = AUTHORIZATION_REGISTRATION_MENU_SERVICE;
                break;
            }
        }
    }
}
