/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Statistic;

/**
 *
 * @author nhtoan
 */
public class StatisticDAO {
    public ArrayList<Statistic> getStatistic() throws SQLException{
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM statistic ORDER BY statistic_date";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Statistic> list = new ArrayList<>();
        while (rs.next()) {
            Statistic st = new Statistic();
            st.setStatisticId(rs.getInt("statistic_id"));
            st.setStatisticDate(rs.getTimestamp("statistic_date"));
            st.setRevenue(rs.getFloat("revenue"));
            list.add(st);
        }
        return list;

    }
    
    public void updateMoney(float money, int id) throws SQLException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "UPDATE statistic SET revenue = ? WHERE statistic_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setFloat(1, money);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
    
    public void insertMoney(float money) throws SQLException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
//        dateFormat.format(ts);
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "INSERT INTO statistic(statistic_date, revenue) VALUES(?,?)";
        PreparedStatement ps = con.prepareCall(sql);
        ps.setTimestamp(1, ts);
        ps.setFloat(2, money);
        ps.executeUpdate();
    }
    
    public Statistic checkDate() throws SQLException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM statistic ";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        Statistic st = new Statistic();
        while(rs.next()){
            if(dateFormat.format(date).equals(dateFormat.format(rs.getTimestamp("statistic_date")))){
                st.setRevenue(rs.getFloat("revenue"));
                st.setStatisticDate(rs.getTimestamp("statistic_date"));
                st.setStatisticId(rs.getInt("statistic_id"));
                return st;
            }
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {
        StatisticDAO sd = new StatisticDAO();
//        for(Statistic st: sd.getStatistic()){
//            System.out.println(st.getStatisticId()+" "+st.getStatisticDate()+" "+st.getRevenue());
//        }
//        Statistic st = sd.checkDate();
//        if(st!=null){
//            System.out.println("Update");
//            sd.updateMoney(10000+st.getRevenue(), st.getStatisticId());
//        }else{
//            System.out.println("Insert");
//            sd.insertMoney(10000);
//        }
    }
}
