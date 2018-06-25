/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CarsDAO;
import dao.FollowDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Follow;

/**
 *
 * @author nhtoan
 */
public class FollowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url="";
        try {
            CarsDAO cd = new CarsDAO();
            FollowDAO fd = new FollowDAO();
            String command = request.getParameter("command");
            switch(command){
                case "search":
                    String snumberPlate= request.getParameter("snumberPlate");
                    ArrayList<String> listResult = new ArrayList<>();
                    listResult = cd.searchCars(snumberPlate);
                    request.setAttribute("listResult", listResult);
                    url = "/result.jsp";
                    break;
                case "insert":
                    String inumberPlate = request.getParameter("inumberPlate");
                    fd.insertCar(inumberPlate);
                    url = "/follow.jsp";
                    break;
                case "detail":
                    String followId = request.getParameter("followId");
                    Follow follow = fd.getFollowCarById(Integer.parseInt(followId));
                    request.setAttribute("follow", follow);
                    url = "/detailfollow.jsp";
                    break;
                case "delete":
                    String dfollowId = request.getParameter("followId");
                    fd.deleteFollow(Integer.parseInt(dfollowId));
                    url = "/follow.jsp";
                    break;
                case "update":
                    String ufollowId = request.getParameter("ufollowId");
                    String unumberPlate = request.getParameter("unumberPlate");
                    fd.updateFollow(unumberPlate, Integer.parseInt(ufollowId));
                    url = "/follow.jsp";
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FollowServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


}
