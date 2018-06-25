/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.MonthlyCars;

/**
 *
 * @author nhtoan
 */
public class MonthlyCarsDAO {

    public int getOwnerIdByCar(String numberPlate) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM monthly_cars WHERE car_number_plate='" + numberPlate + "'";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("owner_id");
        }
        return 0;
    }

    public ArrayList<MonthlyCars> getMonthlyCarsList() throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM monthly_cars ORDER BY owner_id";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<MonthlyCars> list = new ArrayList<>();
        while (rs.next()) {
            MonthlyCars mc = new MonthlyCars();
            mc.setOwnerId(rs.getInt("owner_id"));
            mc.setCarNumberPlate(rs.getString("car_number_plate"));
            mc.setMonthlyCarIndex(rs.getInt("monthly_cars_index"));
            list.add(mc);
        }
        return list;
    }

    public void insertMonthlyCar(String carNumberPlate, int ownerId) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "INSERT INTO monthly_cars(car_number_plate, owner_id) VALUES(?,?)";
        PreparedStatement ps = con.prepareCall(sql);
        ps.setString(1, carNumberPlate);
        ps.setInt(2, ownerId);
        ps.executeUpdate();
    }

    public void deleteMonthlyCar(int index) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "DELETE FROM monthly_cars WHERE monthly_cars_index = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, index);
        ps.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        //MonthlyCarsDAO mcd = new MonthlyCarsDAO();
////        System.out.println(mcd.getOwnerIdByCar("17N1-083"));
//        for (MonthlyCars mc : mcd.getMonthlyCarsList()) {
//            System.out.println(mc.getOwnerId() + " " + mc.getCarNumberPlate()+ " "+mc.getMonthlyCarIndex());
//        }
        // mcd.insertMonthlyCar("17X1111", 2);
        //mcd.deleteMonthlyCar(10);
    }
}
