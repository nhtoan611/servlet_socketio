/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsersDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author nhtoan
 */
public class UsersServlet extends HttpServlet {

    UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        switch(command){
            case "logout":
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("/ParkingSystem/login.jsp");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users users = new Users();
        String url = "";
        HttpSession session = request.getSession();
        users = usersDAO.login(request.getParameter("username"), request.getParameter("password"));
        if (users != null) {
            url = "/index.jsp";
            session.setAttribute("users", users);
        } else {
            url = "/login.jsp";
            request.setAttribute("error", "Invalid username or password");
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

}
