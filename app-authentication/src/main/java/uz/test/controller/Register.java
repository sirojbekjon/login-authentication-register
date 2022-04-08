package uz.test.controller;

import uz.test.model.Result;
import uz.test.model.User;
import uz.test.service.DbService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register2.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            PrintWriter printWriter = resp.getWriter();
        User user = new User();
        String userName = req.getParameter("userName");
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String password = req.getParameter("password");
        String prePassword = req.getParameter("prePassword");
        String phoneNumber = req.getParameter("phoneNumber");

            DbService dbService = new DbService();
        if (password.equals(prePassword)) {
            user.setUserName(userName);
            user.setLastName(lastName);
            user.setFirstName(firstName);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);
            Result result= dbService.registerUser(user);
            if (result.isSuccess()){
            printWriter.write("<h1 style='color:green'>"+result.getMessage()+"</h1>");
        }else{
            printWriter.write("<h1 style='color:red'>"+result.getMessage()+"</h1>");
        }
    }
    else{
        printWriter.write("<h1 style='color:red'>Passwords not equals</h1>");
    }
}
}
