package com.example.abdullah.traveltourism.StaticDatas;

import com.example.abdullah.traveltourism.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by abdullah on 5/14/17.
 */

public class MembersInfo
{
    private static final String TAG_NAME = "Name";
    private static final String TAG_PHONE = "Phone";
    private static final String TAG_DEPOSITE = "DepositeMoney";


    private static ArrayList< HashMap<String, String>> lists = new ArrayList<HashMap<String, String>>();
    private String name;
    private String phn;
    private Double DepositeMoney=0.0;
    public MembersInfo() {

    }
    public MembersInfo(String name, String phn, Double depositeMoney) {
        this.name = name;
        this.phn = phn;
        DepositeMoney = depositeMoney;
/*
        // creating new HashMap
        HashMap<String, String> map = new HashMap<String, String>();

        // adding each child node to HashMap key => value
        map.put(TAG_NAME, name);
        map.put(TAG_PHONE, phn);
        map.put(TAG_DEPOSITE, DepositeMoney.toString());
        lists.add(map);
        */
        MainActivity.memdb.addMembers(new Members(name,phn,DepositeMoney));

    }

    public ArrayList getdetails()
    {
        lists.clear();
        List<Members> membersList = new ArrayList<Members>();
        membersList= MainActivity.memdb.getAllContacts();

        for(int i=0;i<membersList.size();i++)
        {
            Members ex=membersList.get(i);
            HashMap<String, String> map = new HashMap<String, String>();

            // adding each child node to HashMap key => value
            map.put(TAG_NAME,ex.getName());
            map.put(TAG_PHONE, ex.getPhn());
            map.put(TAG_DEPOSITE, ex.getDepositeMoney().toString());

            lists.add(map);
        }
        return lists;
    }


    public static void updateDepositeMoney(Double money)
    {


        List<Members> membersList = new ArrayList<Members>();
        membersList= MainActivity.memdb.getAllContacts();

        for(int i=0;i<membersList.size();i++)
        {
            Members ex=membersList.get(i);
            Double m=ex.getDepositeMoney();
            m-=money;
            ex.setDepositeMoney(m);

                Members e=new Members(ex.getId(),ex.getName(),ex.getPhn(),ex.getDepositeMoney());
                MainActivity.memdb.updateContact(e);


        }

        /*
        for(int i=0;i<lists.size();i++)
        {
            // creating new HashMap
            HashMap<String, String> map = lists.get(i);
            String currentMoney=map.get(TAG_DEPOSITE);
            Double UpdateMoney=Double.parseDouble(currentMoney);
            UpdateMoney-=money;
            map.put(TAG_DEPOSITE,Double.toString(UpdateMoney));

        }
        */
    }
    public static void updateMemberDetails(String name,String phone,String Deposite){


        List<Members> membersList = new ArrayList<Members>();
        membersList= MainActivity.memdb.getAllContacts();

        for(int i=0;i<membersList.size();i++)
        {
            Members ex=membersList.get(i);
            if(ex.getName().equals(name))
            {
                Members e=new Members(ex.getId(),name,phone,Double.parseDouble(Deposite));
                MainActivity.memdb.updateContact(e);
                break;
            }

        }

        /*
        for(int i=0;i<lists.size();i++)
        {
            // creating new HashMap
            HashMap<String, String> map = lists.get(i);
            if(map.get(TAG_NAME).equals(name))
            {
                map.put(TAG_PHONE,phone);
                map.put(TAG_DEPOSITE,Deposite);
                break;
            }

        }
        */
    }
    public static void DeleteMember(String name,String phone,String Deposite){


        List<Members> membersList = new ArrayList<Members>();
        membersList= MainActivity.memdb.getAllContacts();

        for(int i=0;i<membersList.size();i++)
        {
            Members ex=membersList.get(i);
            if(ex.getName().equals(name))
            {
                Members e=new Members(ex.getId(),name,phone,Double.parseDouble(Deposite));
                MainActivity.memdb.deleteContact(e);
                break;
            }

        }
        /*
        for(int i=0;i<lists.size();i++)
        {
            // creating new HashMap
            HashMap<String, String> map = lists.get(i);
            if(map.get(TAG_NAME).equals(name))
            {
                lists.remove(i);
                break;
            }

        }
        */

    }


}
