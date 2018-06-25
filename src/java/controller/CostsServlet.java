/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CostsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Costs;

/**
 *
 * @author nhtoan
 */
public class CostsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url="";
        try {
            CostsDAO cd = new CostsDAO();
            String command = request.getParameter("command");    
            switch(command){
                case "detail":
                    String costId = request.getParameter("costId");
                    Costs cost = cd.getCostById(Integer.parseInt(costId));
                    request.setAttribute("cost", cost);
                    url = "/detailprice.jsp";
                    break;
                case "update":
                    String ucostId = request.getParameter("costId");
                    String utime = request.getParameter("time");
                    String uunitPrice = request.getParameter("unitPrice");
                    cd.updateCosts(Integer.parseInt(uunitPrice), Integer.parseInt(utime), Integer.parseInt(ucostId));
                    url = "/price.jsp";
                    break;
                case "delete":
                    String dcostId = request.getParameter("costId");
                    cd.deleteCosts(Integer.parseInt(dcostId));
                    url = "/price.jsp";
                    break;
                case "insert":
                    String itime = request.getParameter("itime");
                    String iunitPrice = request.getParameter("iunitPrice");
                    cd.insertCosts(Integer.parseInt(iunitPrice), Integer.parseInt(itime));
                    url = "/price.jsp";
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CostsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
