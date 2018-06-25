/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nhtoan
 */
public class MonthlyCars {
    private int monthlyCarIndex;
    private String carNumberPlate;
    private int ownerId;

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

    public int getMonthlyCarIndex() {
        return monthlyCarIndex;
    }

    public void setMonthlyCarIndex(int monthlyCarIndex) {
        this.monthlyCarIndex = monthlyCarIndex;
    }
    
}
