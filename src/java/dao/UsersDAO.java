/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import connection.DBConnection;
import java.sql.*;
import model.Users;

/**
 *
 * @author nhtoan
 */
public class UsersDAO {

    public Users login(String username, String password) {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "select * from users where username='" + username + "' and password='" + password + "'";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Users u = new Users();
                u.setUserId(rs.getInt("user_id"));
                u.setUserName(rs.getString("username"));
                u.setPhoneNumber(rs.getString("phone_number"));
                u.setUserMail(rs.getString("email"));
                u.setUserPass(rs.getString("password"));
                con.close();
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
//        UsersDAO ud = new UsersDAO();
//        Users u = ud.login("nhtoan", "123456");
//        System.out.println(u.getPhoneNumber());
        
    }
}

