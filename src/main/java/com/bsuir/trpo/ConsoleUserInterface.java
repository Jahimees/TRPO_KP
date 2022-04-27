package com.bsuir.trpo;

import com.bsuir.trpo.datasource.SqlInitializer;
import com.bsuir.trpo.model.User;
import com.bsuir.trpo.service.user.RegistrationService;
import com.bsuir.trpo.service.menu.*;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;


public final class ConsoleUserInterface {

    private static final AccountMenuService ADMIN_MENU_SERVICE = new AdminMenuService();
    private static final AccountMenuService USER_MENU_SERVICE = new UserMenuService();
    private static final MenuService AUTHORIZATION_REGISTRATION_MENU_SERVICE = new AuthorizationRegistrationMenuService();
    private static final MenuService CONFIRM_ACCOUNT_MENU_SERVICE = new ConfirmAccountMenuService();
    private static final MenuService ACCOUNT_MANAGER_MENU_SERVICE = new AccountManagerMenuService();
    private static final MenuService STUDENT_LIST_MANAGER_MENU_SERVICE = new StudentListManagerMenuService();
    public static final String ADMIN_MENU_SERVICE_NAME = "adminMenuService";
    public static final String USER_MENU_SERVICE_NAME = "userMenuService";
    public static final String AUTHORIZATION_REGISTRATION_MENU_SERVICE_NAME = "authorizationRegistrationMenuService";
    public static final String CONFIRM_ACCOUNT_MENU_SERVICE_NAME = "confirmAccountMenuService";
    public static final String ACCOUNT_MANAGER_MENU_SERVICE_NAME = "accountManagerMenuService";
    public static final String STUDENT_LIST_MANAGER_MENU_SERVICE_NAME = "studentListManagerMenuService";

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
            System.out.println(SYSTEM_CREATED_JUST_NOW);
            RegistrationService registrationService = new RegistrationService();
            while (!registrationService.register(true, true)) ;
        }

        setCurrentMenu(AUTHORIZATION_REGISTRATION_MENU_SERVICE_NAME);

        while (true) {
            currentMenu.chooseMenuAction();
        }
    }

    private boolean connectionIsOk() {
        System.out.println(CHECKING_SYSTEM);
        SqlInitializer sqlInitializer = new SqlInitializer();
        boolean result = sqlInitializer.isTablesOk();
        System.out.println(CHECKING_COMPLETE);
        return result;
    }

    private void printHelloWord() {
        System.out.println(WELCOME_WORDS);
    }

    public static User getActiveAdminUser() {
        return ADMIN_MENU_SERVICE.getActiveUser();
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
            case CONFIRM_ACCOUNT_MENU_SERVICE_NAME: {
                currentMenu = CONFIRM_ACCOUNT_MENU_SERVICE;
                break;
            }
            case ACCOUNT_MANAGER_MENU_SERVICE_NAME: {
                currentMenu = ACCOUNT_MANAGER_MENU_SERVICE;
                break;
            }
            case STUDENT_LIST_MANAGER_MENU_SERVICE_NAME: {
                currentMenu = STUDENT_LIST_MANAGER_MENU_SERVICE;
                break;
            }
        }
    }
}
