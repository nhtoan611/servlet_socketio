/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Cars;
import model.Costs;

/**
 *
 * @author nhtoan
 */
public class CarsDAO {

    public ArrayList<Cars> getListInCar() throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM cars ORDER BY car_in_time";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Cars> list = new ArrayList<>();
        while (rs.next()) {
            Cars car = new Cars();
            car.setCarIndex(rs.getInt("car_index"));
            car.setCarNumberPlate(rs.getString("car_number_plate"));
            car.setCarInTime(rs.getTimestamp("car_in_time"));
            car.setCarOutTime(rs.getTimestamp("car_out_time"));
            car.setCarType(rs.getInt("car_type"));
            car.setOwnerId(rs.getInt("owner_id"));
            car.setCarInImage(rs.getString("car_in_image"));
            car.setCarOutImage(rs.getString("car_out_image"));
            list.add(car);
        }
        return list;
    }

    public ArrayList<Cars> getListInCarByDate(String date) throws SQLException, ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM cars ORDER BY car_in_time";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Cars> list = new ArrayList<>();
        while (rs.next()) {
            String sqlDate = df.format(rs.getDate("car_in_time"));
            Cars car = new Cars();
            car.setCarIndex(rs.getInt("car_index"));
            car.setCarNumberPlate(rs.getString("car_number_plate"));
            car.setCarInTime(rs.getTimestamp("car_in_time"));
            car.setCarOutTime(rs.getTimestamp("car_out_time"));
            car.setCarType(rs.getInt("car_type"));
            car.setOwnerId(rs.getInt("owner_id"));
            car.setCarInImage(rs.getString("car_in_image"));
            car.setCarOutImage(rs.getString("car_out_image"));
            if (sqlDate.equals(date)) {
                list.add(car);
            };
        }
        return list;
    }

    public ArrayList<Cars> getListOutCarByDate(String date) throws SQLException, ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM cars ORDER BY car_out_time";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Cars> list = new ArrayList<>();
        while (rs.next()) {
            if (rs.getDate("car_out_time") != null) {
                String sqlDate = df.format(rs.getDate("car_out_time"));
                Cars car = new Cars();
                car.setCarIndex(rs.getInt("car_index"));
                car.setCarNumberPlate(rs.getString("car_number_plate"));
                car.setCarInTime(rs.getTimestamp("car_in_time"));
                car.setCarOutTime(rs.getTimestamp("car_out_time"));
                car.setCarType(rs.getInt("car_type"));
                car.setOwnerId(rs.getInt("owner_id"));
                car.setCarInImage(rs.getString("car_in_image"));
                car.setCarOutImage(rs.getString("car_out_image"));
                if (sqlDate.equals(date)) {
                    list.add(car);
                };
            }

        }
        return list;
    }

    public Cars getCars(String numberPlate) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM cars WHERE car_number_plate='" + numberPlate + "'";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        Cars car = new Cars();
        while (rs.next()) {
            car.setCarIndex(rs.getInt("car_index"));
            car.setCarNumberPlate(rs.getString("car_number_plate"));
            car.setCarInTime(rs.getTimestamp("car_in_time"));
            car.setCarOutTime(rs.getTimestamp("car_out_time"));
            car.setCarType(rs.getInt("car_type"));
            car.setOwnerId(rs.getInt("owner_id"));
            car.setCarInImage(rs.getString("car_in_image"));
            car.setCarOutImage(rs.getString("car_out_image"));
        }
        return car;
    }

    public Cars getCars(int carIndex) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT * FROM cars WHERE car_index='" + carIndex + "'";
        PreparedStatement ps = con.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        Cars car = new Cars();
        while (rs.next()) {
            car.setCarIndex(rs.getInt("car_index"));
            car.setCarNumberPlate(rs.getString("car_number_plate"));
            car.setCarInTime(rs.getTimestamp("car_in_time"));
            car.setCarOutTime(rs.getTimestamp("car_out_time"));
            car.setCarType(rs.getInt("car_type"));
            car.setOwnerId(rs.getInt("owner_id"));
            car.setCarInImage(rs.getString("car_in_image"));
            car.setCarOutImage(rs.getString("car_out_image"));
        }
        return car;
    }

    public void insertInCar(Cars car) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "INSERT INTO cars(car_number_plate, car_in_time, car_out_time, car_type, owner_id, car_in_image, car_out_image) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareCall(sql);
        ps.setString(1, car.getCarNumberPlate());
        ps.setTimestamp(2, car.getCarInTime());
        ps.setTimestamp(3, car.getCarOutTime());
        ps.setInt(4, car.getCarType());
        ps.setInt(5, car.getOwnerId());
        ps.setString(6, car.getCarInImage());
        ps.setString(7, car.getCarOutImage());
        ps.executeUpdate();
    }

    public void updateOutTime(String numberPlate, Timestamp ts, String imagePath) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "UPDATE cars SET car_out_time = ?, car_out_image = ? WHERE car_number_plate = ? AND car_out_time IS NULL";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setTimestamp(1, ts);
        ps.setString(2, imagePath);
        ps.setString(3, numberPlate);
        ps.executeUpdate();
    }

//    public ArrayList<Cars> searchCars(String numberPlate) throws SQLException {
//        Connection con = connection.DBConnection.getConnecttion();
//        String sql = "SELECT  * FROM cars WHERE car_number_plate LIKE ?";
//        PreparedStatement ps = con.prepareCall(sql);
//        ps.setString(1, "%"+numberPlate+"%");
//        ResultSet rs = ps.executeQuery();
//        ArrayList<Cars> list = new ArrayList<>();
//        while (rs.next()) {
//            Cars car = new Cars();
//            car.setCarIndex(rs.getInt("car_index"));
//            car.setCarNumberPlate(rs.getString("car_number_plate"));
//            car.setCarInTime(rs.getTimestamp("car_in_time"));
//            car.setCarOutTime(rs.getTimestamp("car_out_time"));
//            car.setCarType(rs.getInt("car_type"));
//            car.setOwnerId(rs.getInt("owner_id"));
//            car.setCarInImage(rs.getString("car_in_image"));
//            car.setCarOutImage(rs.getString("car_out_image"));
//            list.add(car);
//        }
//        return list;
//    }
    public ArrayList<String> searchCars(String numberPlate) throws SQLException {
        Connection con = connection.DBConnection.getConnecttion();
        String sql = "SELECT DISTINCT car_number_plate FROM cars WHERE car_number_plate LIKE ?";
        PreparedStatement ps = con.prepareCall(sql);
        ps.setString(1, "%" + numberPlate + "%");
        ResultSet rs = ps.executeQuery();
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            String result = rs.getString("car_number_plate");
            list.add(result);
        }
        return list;
    }

    public long getPrice(Cars car, ArrayList<Costs> list) {
        int i, n = list.size();
        long price = 0;
        long sum = 0;
        long totalPrice = 0;
        long totalTime = 0;
        long substract = car.getCarOutTime().getTime() - car.getCarInTime().getTime();
        long sumi = 0;
        for (i = 0; i < n; i++) {
            totalPrice = totalPrice + list.get(i).getTime() * list.get(i).getUnitPrice();
         
            totalTime = totalTime + list.get(i).getTime();
        }
        System.out.println("totalPrice: "+ totalPrice);
        System.out.println("totalTime: "+totalTime);
        if (totalTime * 3600000 < substract) {
            return totalPrice + (substract - totalTime * 3600000) * list.get(n - 1).getUnitPrice()/3600000;
        } else {
            for (i = 0; i < n; i++) {
                sum = (sum + list.get(i).getTime()) * 3600000;
                if (substract < sum) {
                    price = price + (substract - sumi) * list.get(i).getUnitPrice() / 3600000;
                    return price;
                } else {
                    price = price + list.get(i).getUnitPrice() * list.get(i).getTime();
                    sumi = sumi + list.get(i).getTime() * 3600000;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) throws SQLException, ParseException {
//        CarsDAO dao = new CarsDAO();
//        for(Cars c: dao.getListInCar()){
//            System.out.println(c.getCarIndex()+ " " +c.getCarNumberPlate()+ " "+ c.getCarInTime());
//        }
//      
//        java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
//        dao.updateOutTime("29X1005",ts,"images/out/29X1005.jpg");
//    
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        for (Cars c : dao.getListOutCarByDate(dateFormat.format(date))) {
//            System.out.println(c.getCarIndex() + " " + c.getCarNumberPlate());
//        }
//        
//        Cars car = dao.getCars(8);
//        CostsDAO cd = new CostsDAO();
//        System.out.println(dao.getPrice(car, cd.getListCosts()));;
//        System.out.println(car.getCarIndex() + " " + car.getCarNumberPlate() + " " + car.getCarInTime()+ " "+car.getCarOutTime());
//        System.out.println(car.getCarOutTime().getTime() - car.getCarInTime().getTime());
//        java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
//        Cars x = new Cars();
//        x.setCarNumberPlate("29X1005");
//        x.setCarType(2);
//        x.setOwnerId(0);
//        x.setCarInImage("/images/in/1.jpg");
//        x.setCarInTime(ts);
//        x.setCarOutImage(null);
//        x.setCarOutTime(null);
//        dao.insertInCar(x);
//          for(String c:dao.searchCars("29")){
//              System.out.println(c);
//          }
    }
}
