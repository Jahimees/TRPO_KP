package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.model.User;

public abstract class AccountMenuService extends MenuService {
    private User activeUser;

    public void setActiveUser(User user) {
        this.activeUser = user;
    }

    public User getActiveUser() {
        return activeUser;
    }
}
