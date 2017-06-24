package com.example.abdullah.traveltourism.StaticDatas;

import java.util.HashMap;

/**
 * Created by abdullah on 5/25/17.
 */

public class Expense
{

    private int id;
    private String expenseinfo;
    private String Date;
    private Double totalMoney=0.0;
    private Double perHeadMoney=0.0;
    public Expense() {
    }

    public Expense(String expenseinfo, String date, Double totalMoney,Double perHeadMoney) {
        this.expenseinfo = expenseinfo;
        this.Date = date;
        this.totalMoney = totalMoney;
        this. perHeadMoney = perHeadMoney;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getExpenseinfo() {
        return expenseinfo;
    }

    public void setExpenseinfo(String expenseinfo) {
        this.expenseinfo = expenseinfo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getPerHeadMoney() {
        return perHeadMoney;
    }

    public void setPerHeadMoney(Double perHeadMoney) {
        this.perHeadMoney = perHeadMoney;
    }

}
