package com.bsuir.trpo.service;

import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.constant.ParamConstant.LOGIN;
import static com.bsuir.trpo.constant.ParamConstant.ROLE;

public class ChangeRoleMenuService implements ActionService {

    public void changeUserRole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите логин аккаунта, на котором следует изменить роль:");
        String login = scanner.nextLine();
        System.out.println("Введите 1, чтобы аккаунт стал администраторским, 0 - пользовательским");
        int role = 0;
        try {
            role = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Необходимо вводить числовое значение!");
            return;
        }

        if (login == null || login.equals("") || role < 0 || role > 1) {
            System.err.println("Значения пусты, либо являются неверными!");
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put(LOGIN, login);
        params.put(ROLE, role == 1);

        execute(params);
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        UserDBService userDBService = new UserDBService();
        String login = (String) params.get(LOGIN);
        boolean role = (boolean) params.get(ROLE);
        User user = userDBService.getUser(login);

        if (userDBService.getAllAdmins().size() == 1 && user.getRole() && !role) {
            System.err.println("ВНИМАНИЕ. ВЫ НЕ МОЖЕТЕ ИЗМЕНИТЬ РОЛЬ ЭТОГО ПОЛЬЗОВАТЕЛЯ, ТАК КАК ОН ПОСЛЕДНИЙ АДМИНИСТРАТОР!");
            return new HashMap<>();
        }

        if (user == null) {
            System.err.println("Пользователя " + login + " не существует в системе");
            return new HashMap<>();
        }

        userDBService.setRole(login, role);
        System.out.println("Роль " + login + " успешно изменена на роль" + (role ? " администратора" : " пользователя"));

        return new HashMap<>();
    }
}
