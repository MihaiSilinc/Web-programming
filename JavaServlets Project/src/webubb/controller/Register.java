package webubb.controller;

import webubb.domain.User;
import webubb.domain.UserValidator;
import webubb.model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String againPassword = request.getParameter("againPassword");

        boolean isValid = true;
        System.out.println(username);
        System.out.println(password);
        System.out.println(againPassword);
        if (!password.equals(againPassword)){
            isValid=false;
        }
        User user = new User(username, password);
        String errors = UserValidator.validate(user);
        if (errors.length()!=0){
            isValid=false;
        }
        RequestDispatcher requestDispatcher = null;
        if (isValid){
            Connection connection = DBManager.connect();
            try
            {
                String SqlStatement = "INSERT INTO users (username,password) values(?,?)";
                PreparedStatement statement = connection.prepareStatement(SqlStatement);
                statement.setString(1,username);
                statement.setString(2,password);
                statement.execute();
                connection.close();
            }
            catch (Exception err) {
                err.printStackTrace();
            }
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

    }
}
