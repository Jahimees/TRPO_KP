package com.bsuir.trpo;

import com.bsuir.trpo.datasource.SqlInitializer;
import com.bsuir.trpo.model.User;
import com.bsuir.trpo.service.AuthorizationService;
import com.bsuir.trpo.service.RegistrationService;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;

import static com.bsuir.trpo.constant.ParamConstant.*;

public final class ConsoleUserInterface {

    private static ConsoleUserInterface instance;
    private User activeUser;

    private ConsoleUserInterface() {
    }

    public static ConsoleUserInterface getInstance() {
        if (instance == null) {
            instance = new ConsoleUserInterface();
        }

        return instance;
    }

    public void start() {
        printHelloWord();
        if (!connectionIsOk()) {
            registerAdmin();
        }

        while (true) {
            if (authorize()) {
                break;
            }
        }
        printMenu();
        while (true) {

        }
    }

    private boolean connectionIsOk() {
        System.out.println("=============Проверка системы=============");
        SqlInitializer sqlInitializer = new SqlInitializer();
        boolean result = sqlInitializer.checkTablesInDB();
        System.out.println("=============Проверка завершена=============");
        return result;
    }

    private void registerAdmin() {
        System.out.println("\nСистема была только что создана, необходимо зарегистрировать администратора:");
        System.out.println("Введите логин: ");

        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();

        RegistrationService registrationService = new RegistrationService();

        HashMap<String, Object> params = new HashMap<>();
        params.put(LOGIN, login);
        params.put(CLEAR_PASSWORD, password);
        params.put("role", true);
        params.put("access", true);

        registrationService.execute(params);
    }

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
            activeUser = (User) resultParams.get(USER);
        } else {
            System.out.println("Логин или пароль неверны! Попробуйте ещё раз!");
        }

        return (boolean) resultParams.get(CONFIRMED);
    }

    private void printMenu() {
        System.out.println("\nВведите номер пункта");
        System.out.println("1: ");
        System.out.println("0: Выйти из системы");
    }

    private void printAdminMenu() {

    }

    private void printHelloWord() {
        System.out.println("\n--Добро пожаловать в программу распределения мест в общежитии!--\n");
    }

}
