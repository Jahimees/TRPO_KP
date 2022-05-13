package com.bsuir.trpo.service.user;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.UserDBService;
import com.bsuir.trpo.model.User;
import com.bsuir.trpo.service.HashPasswordService;
import com.bsuir.trpo.service.menu.AccountMenuService;

import java.util.HashMap;
import java.util.Scanner;

import static com.bsuir.trpo.ConsoleUserInterface.ADMIN_MENU_SERVICE_NAME;
import static com.bsuir.trpo.ConsoleUserInterface.USER_MENU_SERVICE_NAME;
import static com.bsuir.trpo.constant.LoggerMessageConstant.*;
import static com.bsuir.trpo.constant.ParamConstant.*;

public class AuthorizationService implements UserService {

    public boolean authorize() {
        System.out.println(PLEASE_AUTHORIZE);

        System.out.println(INPUT_LOGIN);

        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        System.out.println(INPUT_PASSWORD);
        String password = scanner.nextLine();

        HashMap<String, Object> params = new HashMap<>();
        params.put(LOGIN, login);
        params.put(CLEAR_PASSWORD, password);

        System.out.println(CHECKING_INPUT_DATA);
        HashMap<String, Object> resultParams = execute(params);

        if ((boolean) resultParams.get(CONFIRMED)) {
            System.out.println(AUTHORIZE_SUCCESSFUL);

            ConsoleUserInterface instance = ConsoleUserInterface.getInstance();
            User user = ((User) resultParams.get(USER));
            if (user.getRole()) {
                instance.setCurrentMenu(ADMIN_MENU_SERVICE_NAME);
            } else {
                instance.setCurrentMenu(USER_MENU_SERVICE_NAME);
            }
            ((AccountMenuService) instance.getCurrentMenu()).setActiveUser(user);
        } else {
            System.out.println(LOGIN_OR_PASSWORD_INCORRECT);
        }

        return (boolean) resultParams.get(CONFIRMED);
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        String login = (String) params.get(LOGIN);
        String clearPassword = (String) params.get(CLEAR_PASSWORD);

        UserDBService userDBService = new UserDBService();
        User user = userDBService.getUser(login);

        HashPasswordService hashPasswordService = new HashPasswordService();
        String saltedHashPassword = hashPasswordService.convertPasswordForComparing(clearPassword, user.getSalt());

        HashMap<String, Object> resultParams = new HashMap<>();
        resultParams.put(CONFIRMED,  saltedHashPassword.equals(user.getSaltedHashPassword()) && user.isAccess());
        resultParams.put(USER, user);

        return resultParams;
    }
}
