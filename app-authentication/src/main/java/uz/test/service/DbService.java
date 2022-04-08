package uz.test.service;

import uz.test.model.Result;
import uz.test.model.User;

import java.io.PrintWriter;
import java.sql.*;

public class DbService {
    String url="jdbc:postgresql://localhost:5432/app-auth";
    String dbUsers="postgres";
    String dbPassword="siroj1294";

public Result registerUser(User user){

            try {
                Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,dbUsers,dbPassword);
            Statement statement = connection.createStatement();
            String query = "select count(*) from users where username='"+user.getUserName()+"';";
            int countByFields=0;
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                countByFields= resultSet.getInt(1);
            }
            if (countByFields>0){
                return new Result("username already exist", false);
            }

            String query2 = "select count(*) from users where phone_number='"+user.getPhoneNumber()+"'";
            ResultSet resultSet1=statement.executeQuery(query2);
            while(resultSet1.next()){
                countByFields= resultSet1.getInt(1);
            }if(countByFields>0){
                return new Result("phone number already exist",false);
                }



            String saveQuery = "insert into users (username,first_name,last_name,phone_number,password) values ('"+user.getUserName()+"','"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getPhoneNumber()+"','"+user.getPassword()+"');";
            boolean resultSet2=statement.execute(saveQuery);
            return new Result("save success",true);


            } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
             return new Result("notugri",false);


}
}
