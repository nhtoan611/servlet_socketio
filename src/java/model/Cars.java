/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author nhtoan
 */
public class Cars {
    private int carIndex;

    public int getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(int carIndex) {
        this.carIndex = carIndex;
    }
    private String carNumberPlate;
    private int ownerId;
    private Timestamp carInTime;
    private Timestamp carOutTime;
    private int carType;
    private String carInImage;
    private String carOutImage;

    public Cars() {
    }


    public String getCarInImage() {
        return carInImage;
    }

    public void setCarInImage(String carInImage) {
        this.carInImage = carInImage;
    }

    public String getCarOutImage() {
        return carOutImage;
    }

    public void setCarOutImage(String carOutImage) {
        this.carOutImage = carOutImage;
    }

    public int getCarType() {
        return carType;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }

    public String getCarNumberPlate() {
        return carNumberPlate;
    }

    public void setCarNumberPlate(String carNumberPlate) {
        this.carNumberPlate = carNumberPlate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Timestamp getCarInTime() {
        return carInTime;
    }

    public void setCarInTime(Timestamp carInTime) {
        this.carInTime = carInTime;
    }

    public Timestamp getCarOutTime() {
        return carOutTime;
    }

    public void setCarOutTime(Timestamp carOutTime) {
        this.carOutTime = carOutTime;
    }

    
}
