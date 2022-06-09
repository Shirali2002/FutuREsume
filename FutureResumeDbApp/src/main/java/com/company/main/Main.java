package com.company.main;

import com.company.bean.User;
import com.company.dao.inter.UserDaoInter;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        UserDaoInter userDao = Context.instanceUserDao();
        User u = new User(0, "shirali", "alihummatov", "+994122344256", "shirali@gmail.com");
        userDao.addUser(u);
    }

}
