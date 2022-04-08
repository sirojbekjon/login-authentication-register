package uz.pdp.controller;

import uz.pdp.mode.User;
import uz.pdp.service.DbService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        DbService dbService = new DbService();
        User user = dbService.login(new User());
        printWriter.write("Kabinetga Hush kelibsiz xurmatli"+username);
if (user == null){
    printWriter.write("<h1> password or login error</h1>");
}else{
    printWriter.write("<h1> Welcome to system "+user.getFirstName()+" "+user.getLastName()+"</h1>");
    printWriter.write("<h1> Your phone number is "+user.getPhoneNumber()+"</h1>");
}

    }




}
