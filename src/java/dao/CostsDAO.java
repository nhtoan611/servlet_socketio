/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Costs;

/**
 *
 * @author nhtoan
 */
public class CostsDAO {

    public ArrayList<Costs> getListCosts() throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM costs ORDER BY cost_id";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Costs> list = new ArrayList<>();
        while (rs.next()) {
            Costs cost = new Costs();
            cost.setCostId(rs.getInt("cost_id"));
            cost.setTime(rs.getInt("time"));
            cost.setUnitPrice(rs.getInt("unit_price"));
            list.add(cost);
        }
        return list;
    }

    public Costs getCostById(int id) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM costs WHERE cost_id='" + id + "'";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        Costs cost = new Costs();
        while (rs.next()) {
            cost.setCostId(rs.getInt("cost_id"));
            cost.setTime(rs.getInt("time"));
            cost.setUnitPrice(rs.getInt("unit_price"));
        }
        return cost;
    }

    public void insertCosts(int unitPrice, int time) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "INSERT INTO costs(unit_price, time ) VALUES(?,?)";
        PreparedStatement ps = con.prepareCall(sql);
        ps.setInt(1, unitPrice);
        ps.setInt(2, time);
        ps.executeUpdate();
    }
    
    public void updateCosts(int unitPrice, int time, int costId) throws SQLException{
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "UPDATE costs SET unit_price = ?, time = ? WHERE cost_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, unitPrice);
        ps.setInt(2, time);
        ps.setInt(3, costId);
        ps.executeUpdate();
    }
    
    public void deleteCosts(int costId) throws SQLException{
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "DELETE FROM costs WHERE cost_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, costId);
        ps.executeUpdate();
    }
    
    public static void main(String[] args) throws SQLException {
//        CostsDAO cd = new CostsDAO();
//        for(Costs c: cd.getListCosts()){
//            System.out.println(c.getCostId()+ " "+ c.getTime()+ " "+c.getUnitPrice());
//        }
//        System.out.println(cd.getCostById(1).getTime());;
  //      cd.insertCosts(50000, 4);
//        cd.updateCosts(15000, 3, 2);
//            cd.deleteCosts(3);
    }
}
