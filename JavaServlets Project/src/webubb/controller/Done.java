package webubb.controller;

import webubb.repository.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Done extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("uname");
        String status = request.getParameter("status");
        HttpSession httpSession = request.getSession();
        Repository table = new Repository(username);
        httpSession.setAttribute("username", username);
        httpSession.setAttribute("status", status);
        httpSession.setAttribute("moves", table.getMoves(username).toString());
        table.initialize(username);
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("/done.jsp");
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
