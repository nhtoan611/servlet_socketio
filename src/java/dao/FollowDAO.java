/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Follow;

/**
 *
 * @author nhtoan
 */
public class FollowDAO {

    public int getFollowCar(String numberPlate) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM follow WHERE car_number_plate='" + numberPlate + "'";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return 1;
        }
        return 0;
    }

    public Follow getFollowCarById(int followId) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM follow WHERE follow_id='" + followId + "'";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        Follow follow = new Follow();
        while (rs.next()) {
            follow.setCarNumberPlate(rs.getString("car_number_plate"));
            follow.setFollowId(rs.getInt("follow_id"));
        }
        return follow;
    }

    public ArrayList<Follow> getListCar() throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM follow ORDER BY follow_id";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Follow> list = new ArrayList<>();
        while (rs.next()) {
            Follow follow = new Follow();
            follow.setCarNumberPlate(rs.getString("car_number_plate"));
            follow.setFollowId(rs.getInt("follow_id"));
            list.add(follow);
        }
        return list;
    }

    public void insertCar(String numberPlate) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "INSERT INTO follow(car_number_plate) VALUES(?)";
        PreparedStatement ps = con.prepareCall(sql);
        ps.setString(1, numberPlate);
        ps.executeUpdate();
    }

    public void updateFollow(String numberPlate, int followId) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "UPDATE follow SET car_number_plate = ? WHERE follow_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, numberPlate);
        ps.setInt(2, followId);
        ps.executeUpdate();
    }

    public void deleteFollow(int followId) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "DELETE FROM follow WHERE follow_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, followId);
        ps.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
//        FollowDAO fd = new FollowDAO();
//        System.out.println(fd.getFollowCar("29X1001"));
//        for(Follow f: fd.getListCar()){
//            System.out.println(f.getFollowId()+ ": "+ f.getCarNumberPlate());
//        }

//         fd.insertCar("22222");
//    fd.updateFollow("3", 8);
//        fd.deleteFollow(9);
//        Follow follow = fd.getFollowCarById(1);
//        System.out.println(follow.getCarNumberPlate());
    }
}
