package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDao {
    
    public Connection connection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/futureresumedb";
        String username = "root";
        String password = "Joker02";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
    
}
