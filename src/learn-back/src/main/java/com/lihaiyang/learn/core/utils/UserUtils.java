package com.lihaiyang.learn.core.utils;

import java.util.List;
import com.lihaiyang.learn.core.account.AccountLocals;
import com.lihaiyang.learn.entity.Menu;
import com.lihaiyang.learn.entity.Role;
import com.lihaiyang.learn.entity.User;
import com.lihaiyang.learn.service.IMenuService;
import com.lihaiyang.learn.service.IRoleService;

public class UserUtils {
    private static IRoleService roleService = (IRoleService) SpringContextHolder.getBean(IRoleService.class);
    private static IMenuService menuService = (IMenuService) SpringContextHolder.getBean(IMenuService.class);


    public UserUtils() {
    }

    public static User getUser() {
        return (User) AccountLocals.getAccount();
    }

    public static List<Role> getRoleList() {
        User user = getUser();
        Role role = new Role();
        role.setUserId(user.getId());
        return  roleService.findByUserId(role);
    }

    public static List<Menu> getMenuList() {
        User user = getUser();
        Menu m = new Menu();
        m.setUserId(user.getId());
        return menuService.findByUserId(m);
    }

}



