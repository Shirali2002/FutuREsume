package com.company.main;

import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.EmploymentHistory;

public class Main {
    public static void main(String[] args) throws Exception{
        EmploymentHistoryDaoInter dao = Context.instanceEmploymentHistoryDao();

        System.out.println(dao.getAllEmploymentHistoryByUserId(5));
//        for (User u: userDao.getAll()) {
//            System.out.println(u.toString());
//        }
    }

}
