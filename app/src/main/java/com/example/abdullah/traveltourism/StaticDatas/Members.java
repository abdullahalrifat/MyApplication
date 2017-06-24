package com.example.abdullah.traveltourism.StaticDatas;

import java.util.HashMap;

/**
 * Created by abdullah on 5/25/17.
 */

public class Members
{
    private int id;
    private String name;
    private String phn;
    private Double DepositeMoney=0.0;
    public Members() {

    }
    public Members(String name, String phn, Double depositeMoney) {
        this.name = name;
        this.phn = phn;
        this.DepositeMoney = depositeMoney;

    }
    public Members(int id,String name, String phn, Double depositeMoney) {
       this.id=id;
        this.name = name;
        this.phn = phn;
        this.DepositeMoney = depositeMoney;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public Double getDepositeMoney() {
        return DepositeMoney;
    }

    public void setDepositeMoney(Double depositeMoney) {
        DepositeMoney = depositeMoney;
    }

}
