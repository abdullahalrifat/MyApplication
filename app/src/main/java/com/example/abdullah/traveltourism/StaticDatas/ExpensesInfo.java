package com.example.abdullah.traveltourism.StaticDatas;

import com.example.abdullah.traveltourism.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by abdullah on 5/15/17.
 */

public class ExpensesInfo
{

    private static final String TAG_EXINFO = "Info";
    private static final String TAG_DATE = "Date";
    private static final String TAG_TOTALMONEY = "TotalMoney";
    private static final String TAG_PERHEADMONEY = "PerHeadMoney";
    private String expenseinfo;
    private String Date;
    private Double totalMoney=0.0;
    private Double perHeadMoney=0.0;
    private static ArrayList< HashMap<String, String>> Expenselists = new ArrayList<HashMap<String, String>>();
    public ExpensesInfo() {
    }

    public ExpensesInfo(String expenseinfo, String date, Double totalMoney,Double perHeadMoney) {
        this.expenseinfo = expenseinfo;
        this.Date = date;
        this.totalMoney = totalMoney;
        this. perHeadMoney = perHeadMoney;
        /*
        // creating new HashMap
        HashMap<String, String> map = new HashMap<String, String>();

        // adding each child node to HashMap key => value
        map.put(TAG_EXINFO, expenseinfo);
        map.put(TAG_DATE, Date);
        map.put(TAG_TOTALMONEY, totalMoney.toString());
        map.put(TAG_PERHEADMONEY, perHeadMoney.toString());
        Expenselists.add(map);
        */
        MainActivity.exdb.addExpense(new Expense(expenseinfo,date,totalMoney,perHeadMoney));
    }
    public ArrayList getdetails()
    {
        Expenselists.clear();
        List<Expense> membersList = new ArrayList<Expense>();
        membersList= MainActivity.exdb.getAllExpenses();

        for(int i=0;i<membersList.size();i++)
        {
            Expense ex=membersList.get(i);
            HashMap<String, String> map = new HashMap<String, String>();

            // adding each child node to HashMap key => value
            map.put(TAG_EXINFO,ex.getExpenseinfo());
            map.put(TAG_DATE, ex.getDate());
            map.put(TAG_TOTALMONEY, ex.getTotalMoney().toString());
            map.put(TAG_PERHEADMONEY, ex.getPerHeadMoney().toString());
            Expenselists.add(map);
        }
        return Expenselists;
    }

}
