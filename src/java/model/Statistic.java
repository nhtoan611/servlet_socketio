/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author nhtoan
 */
public class Statistic {
    private int statisticId;
    private Timestamp statisticDate;
    private float revenue;

    public int getStatisticId() {
        return statisticId;
    }

    public void setStatisticId(int statisticId) {
        this.statisticId = statisticId;
    }

    public Timestamp getStatisticDate() {
        return statisticDate;
    }

    public void setStatisticDate(Timestamp statisticDate) {
        this.statisticDate = statisticDate;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }
    
}
