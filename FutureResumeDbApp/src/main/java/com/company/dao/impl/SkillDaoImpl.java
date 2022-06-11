package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;

import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {
    @Override
    public List<Skill> getAll() {
        return null;
    }
}
