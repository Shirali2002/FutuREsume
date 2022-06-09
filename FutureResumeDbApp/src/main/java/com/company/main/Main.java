package com.company.main;

import com.company.bean.User;
import com.company.dao.inter.UserDaoInter;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        UserDaoInter userDao = Context.instanceUserDao();
        for (User u: userDao.getAll()) {
            System.out.println(u.toString());
        }
    }

}
