/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.futuresumewebapp;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;
import org.graalvm.compiler.lir.LIRInstruction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Shireli
 */
@WebServlet(name = "MyServletPage", urlPatterns = "/MyServletPage")
public class MyServletPage extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<User> users = userDao.getAll();
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MyServletPage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("I got GET request");
            for (User u : users) {
                out.println(u);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = String.valueOf(request.getAttribute("name"));
        String surname = String.valueOf(request.getAttribute("surname"));

        User u = new User(0, name, surname, null, null, null, null, null, null, null);
        boolean users = userDao.addUser(u);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MyServletPage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("I got POST request");
            out.println("User inserted: " + u.getId() + "<br>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    pub
}
