package com.bsuir.trpo.service.menu;

public class AdminMenuService extends AccountMenuService {

    @Override
    protected int userInput() {
        return 0;
    }

    @Override
    public void chooseMenuAction() {
        printMenu();
    }

    @Override
    protected void printMenu() {
        System.out.println("Всё получилось");
    }
}
