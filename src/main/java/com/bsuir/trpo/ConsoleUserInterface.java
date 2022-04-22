package com.bsuir.trpo;

import com.bsuir.trpo.datasource.SqlInitializer;

public final class ConsoleUserInterface {

    private static ConsoleUserInterface instance;

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
        checkDatabase();
        printMenu();
        while (true) {

        }
    }

    private void checkDatabase() {
        System.out.println("=============Проверка системы=============");
        SqlInitializer sqlInitializer = new SqlInitializer();
        sqlInitializer.checkTablesInDB();
        System.out.println("=============Проверка завершена=============");
    }

    private void printMenu() {

    }

    private void printHelloWord() {
        System.out.println("\n--Добро пожаловать в программу распределения мест в общежитии!--\n");
    }

}
