package com.company.main;

import com.company.dao.impl.EmploymentHistoryDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.impl.UserSkillDaoImpl;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;

public class Context {
    public static UserDaoInter instanceUserDao(){
        return new UserDaoImpl();
    }

    public static UserSkillDaoInter instanceUserSkillDao(){
        return new UserSkillDaoImpl();
    }

    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao(){
        return new EmploymentHistoryDaoImpl();
    }
}
