/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CarsDAO;
import dao.FollowDAO;
import dao.MonthlyCarsDAO;
import dao.OwnersDAO;
import dao.StatisticDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cars;
import model.Owners;
import model.Statistic;
import model.Users;
import tools.SendMail;

/**
 *
 * @author nhtoan
 */
public class CarsServlet extends HttpServlet {

    CarsDAO carsDao = new CarsDAO();
    OwnersDAO ownersDao = new OwnersDAO();
    MonthlyCarsDAO monthlyCarsDao = new MonthlyCarsDAO();
    FollowDAO followDao = new FollowDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        try {
            String command = request.getParameter("command");
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("users");
            String carIndexs = request.getParameter("carIndex");
            switch (command) {
                case "getDetail":
                    Cars cars = carsDao.getCars(Integer.parseInt(carIndexs));
//                    System.out.println(cars.getCarInImage());
                    request.setAttribute("cars", cars);
                    url = "/detail.jsp";
                    break;
                case "getoDetail":
                    Cars ocars = carsDao.getCars(Integer.parseInt(carIndexs));
//                    System.out.println(cars.getCarInImage());
                    request.setAttribute("cars", ocars);
                    url = "/odetail.jsp";
                    break;
                case "saveIn":
                    String inCarNumberPlate = request.getParameter("inCarNumberPlate");
                    String imagePath = request.getParameter("imagePath");
                    Cars x = new Cars();
                    java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
                    x.setCarNumberPlate(inCarNumberPlate);
                    if (monthlyCarsDao.getOwnerIdByCar(inCarNumberPlate) != 0) {
                        x.setCarType(1);
                        x.setOwnerId(monthlyCarsDao.getOwnerIdByCar(inCarNumberPlate));
                    } else {
                        x.setCarType(2);
                        x.setOwnerId(0);
                    }
                    x.setCarInImage(imagePath);
                    x.setCarInTime(ts);
                    x.setCarOutImage(null);
                    x.setCarOutTime(null);
                    carsDao.insertInCar(x);
                    url = "/index.jsp";
                    break;
                case "caculate":
                    String outCarNumberPlate = request.getParameter("outCarNumberPlate");
                    String oimagePath = request.getParameter("oimagePath");
//                    oimagePath.replace("%2F", "/");
                    java.sql.Timestamp ots = new Timestamp(System.currentTimeMillis());
                    carsDao.updateOutTime(outCarNumberPlate, ots, oimagePath);
                    if(followDao.getFollowCar(outCarNumberPlate)==1){
                        SendMail sm = new SendMail();
                        sm.sendMail(user.getUserMail(), "Thong bao", "Xe theo doi "+outCarNumberPlate+" vua di ra");
                    }
                    request.setAttribute("outCarNumberPlate", outCarNumberPlate);
                    url = "/caculate.jsp";
                    break;
                case "saveOut":
                    String prices = request.getParameter("price");
                    float price = Float.parseFloat(prices);
                    StatisticDAO sd = new StatisticDAO();
                    Statistic st = sd.checkDate();
                    if (st != null) {
                        System.out.println("Update");
                        sd.updateMoney(price + st.getRevenue(), st.getStatisticId());
                    } else {
                        System.out.println("Insert");
                        sd.insertMoney(price);
                    }
                    url="/outcar.jsp";
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        try {
            String command = request.getParameter("command");
            String date = request.getParameter("date");
            switch (command) {
                case "findByDate":
                    ArrayList<Cars> list = carsDao.getListInCarByDate(date);
                    request.setAttribute("list", list);
                    url = "/index.jsp";
                    break;
                case "findOutByDate":
                    ArrayList<Cars> listOut = carsDao.getListOutCarByDate(date);
                    request.setAttribute("listOut", listOut);
                    url = "/outcar.jsp";
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CarsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

}
