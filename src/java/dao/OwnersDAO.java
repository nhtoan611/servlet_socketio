/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Owners;

/**
 *
 * @author nhtoan
 */
public class OwnersDAO {

    public ArrayList<Owners> getListOwner() throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM owners";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Owners> list = new ArrayList<>();
        while (rs.next()) {
            Owners owner = new Owners();
            owner.setOwnerId(rs.getInt("owner_id"));
            owner.setOwnerName(rs.getString("owner_name"));
            owner.setOwnerPhoneNumber(rs.getString("owner_phone_number"));
            list.add(owner);
        }
        return list;
    }

    public Owners getOwners(int ownerId) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM owners WHERE owner_id='" + ownerId + "'";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        Owners owner = new Owners();
        while (rs.next()) {
            owner.setOwnerId(rs.getInt("owner_id"));
            owner.setOwnerName(rs.getString("owner_name"));
            owner.setOwnerPhoneNumber(rs.getString("owner_phone_number"));
        }
        return owner;
    }
    
        public Owners getOwnersByPhone(String phoneNumber) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM owners WHERE owner_phone_number='"+phoneNumber+"'";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        Owners owner = new Owners();
        while (rs.next()) {
            owner.setOwnerId(rs.getInt("owner_id"));
            owner.setOwnerName(rs.getString("owner_name"));
            owner.setOwnerPhoneNumber(rs.getString("owner_phone_number"));
        }
        return owner;
    }

    public void insertOwners(String ownerName, String ownerPhoneNumber) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "INSERT INTO owners(owner_name, owner_phone_number ) VALUES(?,?)";
        PreparedStatement ps = con.prepareCall(sql);
        ps.setString(1, ownerName);
        ps.setString(2, ownerPhoneNumber);
        ps.executeUpdate();
    }
    
    public void deleteOwner(int ownerId) throws SQLException{
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "DELETE FROM owners WHERE owner_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ownerId);
        ps.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        OwnersDAO dao = new OwnersDAO();
//        for (Owners o : dao.getListOwner()) {
//            System.out.println(o.getOwnerId() + " " + o.getOwnerName() + " " + o.getOwnerPhoneNumber());
//        }
//
//        Owners x = dao.getOwners();
//        System.out.println(x.getOwnerName());
//        dao.insertOwners("Nguyen Thi Dung", "123141234");
//            Owners y = dao.getOwnersByPhone("0969220842");
//            System.out.println(y.getOwnerName());
//        dao.deleteOwner(11);
    }
}
