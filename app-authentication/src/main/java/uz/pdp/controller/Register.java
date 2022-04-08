package uz.pdp.controller;

import uz.pdp.mode.Result;
import uz.pdp.mode.User;
import uz.pdp.service.DbService;

import javax.servlet.ServletException;
import javax.servlet.ServletSecurityElement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
resp.sendRedirect("register.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String firstName = req.getParameter("firstName");
      String lastName = req.getParameter("lastName");
      String phoneNumber = req.getParameter("phoneNumber");
      String username = req.getParameter("username");
      String password = req.getParameter("password");
      String prePassword = req.getParameter("prePassword");
      PrintWriter printWriter = resp.getWriter();
      if (password.equals(prePassword)){
          DbService dbService = new DbService();
          User user = new User();
          user.setUsername(username);
          user.setFirstName(firstName);
          user.setLastName(lastName);
          user.setPassword(password);
          user.setPhoneNumber(phoneNumber);
          Result result = dbService.registerUser(user);
//           PrintWriter printWriter = resp.getWriter();
        if (result.isSuccess())
        {
           printWriter.write("<h1 style='color:green'>"+result.getMessage()+"</h1>");
        }else
        {
           printWriter.write("<h1 'style=color:red'>"+result.getMessage()+"</h1>");
        }
      }
      else {
           printWriter.write("<h1 style='color:red'>Passwords not equals</h1>");

      }
    }
}
