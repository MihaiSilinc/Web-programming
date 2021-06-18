package webubb.controller;

/**
 * Created by forest.
 */


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webubb.domain.ImageTile;
import webubb.model.DBManager;
import webubb.domain.User;



public class Login extends HttpServlet {

    public Login() {
        super();
    }

    public User getUser(String username){
        try{
            Connection connection = DBManager.connect();
            Statement statement = connection.createStatement();
            String SqlStatement = "SELECT * FROM users WHERE username = '" + username + "'";
            System.out.println(SqlStatement);
            ResultSet resultSet = statement.executeQuery(SqlStatement);

            if (!resultSet.next()){
                return null;
            }
            User user = new User();
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            resultSet.close();
            statement.close();
            connection.close();
            return user;
        }
        catch (Exception err){
            err.printStackTrace();
        }
        return null;
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = this.getUser(username);


        boolean isValid = true;
        String errors = "";
        if (user == null ){
            errors = "Username not found!";
            isValid = false;
        }
        if (!user.getPassword().equals(password)){
            errors = "Incorrect password!";
            isValid = false;
        }
        RequestDispatcher requestDispatcher;
        if (isValid){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("username", username);
            requestDispatcher = request.getRequestDispatcher("/main.jsp");
        }
        else{
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("errors", errors);
            requestDispatcher = request.getRequestDispatcher("/failed.jsp");
        }
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("username", username);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
        requestDispatcher.forward(request, response);
    }

}