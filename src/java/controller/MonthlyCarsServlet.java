/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MonthlyCarsDAO;
import dao.OwnersDAO;
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
import model.Owners;

/**
 *
 * @author nhtoan
 */
public class MonthlyCarsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        try {
            MonthlyCarsDAO mcd = new MonthlyCarsDAO();
            OwnersDAO od = new OwnersDAO();
            String iownerName = request.getParameter("iownerName");
            String inumberPlate = request.getParameter("inumberPlate");
            String iownerPhoneNumber = request.getParameter("iownerPhoneNumber");
            String command = request.getParameter("command");
            switch (command) {
                case "insert":
                    od.insertOwners(iownerName, iownerPhoneNumber);
                    Owners owner = od.getOwnersByPhone(iownerPhoneNumber);
                    mcd.insertMonthlyCar(inumberPlate, owner.getOwnerId());
                    url = "/monthlycars.jsp";
                    break;
                case "delete":
                    String downerId = request.getParameter("downerId");
                    String dmcIndex = request.getParameter("dmcIndex");
                    mcd.deleteMonthlyCar(Integer.parseInt(dmcIndex));
                    od.deleteOwner(Integer.parseInt(downerId));
                    url = "/monthlycars.jsp";
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MonthlyCarsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
