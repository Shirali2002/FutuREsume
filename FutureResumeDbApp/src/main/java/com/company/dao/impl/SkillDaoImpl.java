package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {
    public Skill getSkill(ResultSet rs) throws Exception{
        Integer skillId = rs.getInt("id");
        String skillName = rs.getString("name");

        return new Skill(skillId, skillName);
    }

    @Override
    public List<Skill> getAllSkills() {
        List<Skill> result = new ArrayList<>();

        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("select * from skill");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                result.add(getSkill(rs));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
