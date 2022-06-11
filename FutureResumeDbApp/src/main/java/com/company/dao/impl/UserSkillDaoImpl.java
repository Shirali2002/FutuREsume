package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

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


public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {
    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
//        int userId = rs.getInt("user_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        User user = new UserDaoImpl().getById(userId);

        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connection()){
            PreparedStatement stmt = c.prepareStatement("SELECT  " +
                    "  u.*,  " +
                    "  us.user_id,  " +
                    "  us.skill_id,  " +
                    "  s.`name` as skill_name,  " +
                    "  us.power  " +
                    "FROM `user_skill` us  " +
                    "  LEFT JOIN `user` u on u.id = us.user_id " +
                    "  LEFT JOIN `skill` s on s.id = us.skill_id " +
                    "WHERE us.user_id = ?;");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                result.add(getUserSkill(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
