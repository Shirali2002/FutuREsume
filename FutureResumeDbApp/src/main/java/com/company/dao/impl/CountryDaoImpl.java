package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {
    public Country getCountry(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String countryName = rs.getString("country_name");
        String nationalityName = rs.getString("nationality_name");

        return new Country(id, countryName, nationalityName);
    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> result = new ArrayList<>();
        try(Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("select " +
                    " c.id,   " +
                    " c.`name` as country_name, " +
                    " c.nationality as nationality_name " +
                    "FROM country as c");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()){
                result.add(getCountry(rs));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
