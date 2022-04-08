package uz.pdp.service;

import uz.pdp.mode.Result;
import uz.pdp.mode.User;


import java.sql.*;

public class DbService {
    String url="jdbc:postgresql://localhost:5432/app-auth";
    String dbUser="postgres";
    String dbPassword="siroj1294";


    public Result registerUser(User user){
    try {
            Class.forName("org.postgresql.Driver");
            //DB ga ulanish uchun connection ochdik
            Connection connection = DriverManager.getConnection(url,dbUser,dbPassword);

            //Querylarni yuborish uchun statement ochdik
            Statement statement = connection.createStatement();

            //phoneNumber orqali tekshirish
            String checkPhoneNumber="select count(*) from users where phone_number='"+user.getPhoneNumber()+"'";
          ResultSet resultSet = statement.executeQuery(checkPhoneNumber);
                int countUserByfields=0;
                while (resultSet.next()){
                    countUserByfields=resultSet.getInt(1);
                }
                if (countUserByfields>0){
                    return new Result("Phone number already exist",false);
                }


            //username orqali tekshirish
                String checkUsernameQuery="select count(*) from users where username='"+user.getUsername()+"'";
                ResultSet resultSetUsername = statement.executeQuery(checkUsernameQuery);
                       while(resultSetUsername.next()){
                    countUserByfields=resultSetUsername.getInt(1);
                }if (countUserByfields>0){
                    return new Result("username already exist", false);
                    }


            //USERNI DB GA SAQLASH
                String query = "insert into users (username,first_name,last_name,phone_number,password)values('"+user.getUsername()+"',"+"'"+user.getFirstName()+"',"+"'"+user.getLastName()+"',"+"'" +user.getPhoneNumber()+"',"+"'"+user.getPassword()+"');";

                boolean execute=statement.execute(query);
                return new Result("Succesfully register",true);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Result("Error in serverr",false);
    }


    public User login(User user){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,dbUser,dbPassword);
            String query = "select * from users where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,username);
//            preparedStatement.setString(2,password);
//            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int id = resultSet.getInt(1);
                String username=resultSet.getString(2);
                String firstName=resultSet.getString(3);
                String lastName=resultSet.getString(4);
                String phoneNumber=resultSet.getString(5);

                return new User(
                        id,
                        firstName,
                        lastName,
                        phoneNumber,
                        username
                );
            }
            return null;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
