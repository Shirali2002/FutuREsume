package com.company.dao.inter;

import com.company.bean.User;

import java.sql.PreparedStatement;
import java.util.List;

public interface UserDaoInter {
    public List<User> getAll();
    public User getById(int id);
    public boolean updateUser(User u);
    public boolean removeUser(int id);
    public boolean addUser(User u);
}
