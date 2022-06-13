package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
select
u.*,
n.`nationality`,
c.`name` as birthplace
from `user` u
LEFT JOIN country n on u.nationality_id = n.id
LEFT JOIN country c on u.birthplace_id = c.id
*/


public class UserDaoImpl extends AbstractDao implements UserDaoInter {
    private User getUser(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthPlaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthPlaceStr = rs.getString("birthplace");
        Date birthDate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthPlace = new Country(birthPlaceId, birthPlaceStr, null);


        return new User(id, name, surname, phone, email, profileDesc, address, birthDate, nationality, birthPlace);
    }

        @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connection()){
            Statement stmt = c.createStatement();
            stmt.execute("select " +
                    "u.*, " +
                    "n.`nationality`, " +
                    "c.`name` as birthplace " +
                    "from `user` u " +
                    "LEFT JOIN country n on u.nationality_id = n.id " +
                    "LEFT JOIN country c on u.birthplace_id = c.id");
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
                    "n.`nationality`, " +
                    "c.`name` as birthplace " +
                    "from `user` u " +
                    "LEFT JOIN country n on u.nationality_id = n.id " +
                    "LEFT JOIN country c on u.birthplace_id = c.id where u.id = ?");
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
            PreparedStatement stmt = c.prepareStatement("update user set name = ?, surname = ?, phone = ?, email = ?, profile_description=?, address=?, birthdate=? where id = ?"); // prevent sql injection
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAddress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getId());
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
            PreparedStatement stmt = c.prepareStatement("insert into user(name, surname, phone, email, profile_description, address) values(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAddress());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
