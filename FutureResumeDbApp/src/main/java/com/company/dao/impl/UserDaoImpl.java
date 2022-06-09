package com.company.dao.impl;

import com.company.bean.Nationality;
import com.company.bean.User;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {
    private User getUser(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        int nationalityId = rs.getInt("nationality_id");
        int birthPlaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthPlaceStr = rs.getString("birthplace");
        Date birthDate = rs.getDate("birthdate");

        Nationality nationality = new Nationality(nationalityId, nationalityStr, null);
        Nationality birthPlace = new Nationality(birthPlaceId, birthPlaceStr, null);


        return new User(id, name, surname, phone, email, birthDate, nationality, birthPlace);
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connection()){
            Statement stmt = c.createStatement();
            stmt.execute("select " +
                    "u.*, " +
                    "n.`name` as nationality, " +
                    "c.`country_name` as birthplace " +
                    "from `user` u " +
                    "LEFT JOIN nationality n on u.nationality_id = n.id " +
                    "LEFT JOIN nationality c on u.birthplace_id = c.id");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                result.add(getUser(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int id) {
        User result = null;
        try (Connection c = connection()){
            PreparedStatement stmt = c.prepareStatement("select " +
                    "u.*, " +
                    "n.`name` as nationality, " +
                    "c.`country_name` as birthplace " +
                    "from `user` u " +
                    "LEFT JOIN nationality n on u.nationality_id = n.id " +
                    "LEFT JOIN nationality c on u.birthplace_id = c.id where id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                result = getUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connection()){
            PreparedStatement stmt = c.prepareStatement("update user set name = ?, surname = ?, phone = ?, email = ? where id = ?"); // prevent sql injection
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setInt(5, u.getId());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connection()){
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id = 1");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connection()){
            PreparedStatement stmt = c.prepareStatement("insert into user(name, surname, phone, email) values(?, ?, ?, ?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
