package com.example.abdullah.traveltourism;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.abdullah.traveltourism.Fragments.Expenses;
import com.example.abdullah.traveltourism.Fragments.Member;
import com.example.abdullah.traveltourism.StaticDatas.Expense;
import com.example.abdullah.traveltourism.StaticDatas.ExpensesInfo;
import com.example.abdullah.traveltourism.StaticDatas.MembersInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by abdullah on 6/29/16.
 */
public class AlertDialogueBuilder {
    String Title="Message !!!";
    public void setTitle(String title)
    {
        Title=title;
    }
    public final String POPUP_LOGIN_TITLE="Details...";
    public final String POPUP_LOGIN_TEXT="Please fill the details to place order";
    public final String Name="Name";
    public final String Phone="Phone No";
    public final String DepositeMoney="Amount of Deposite Money";
    public final String ExpenseInfos="Expenses Info";
    public final String Dates="Date";
    public final String Totals="Total";
    public final String PerHeadTotal="Per Head Total";

    private EditText name;
    private EditText phone;
    private EditText DepositMoney;


    private EditText info;
    private EditText Date;
    private EditText Total;
    private EditText PerTotal;


    public String getName()
    {
        return name.getText().toString();
    }

    public String getPhone() {
        return phone.getText().toString();
    }

    public Double getDepositMoney() {
        return Double.parseDouble(DepositMoney.getText().toString());
    }


    public String getinfo()
    {
        return info.getText().toString();
    }
    public String getDate()
    {
        return Date.getText().toString();
    }
    public Double getTotal()
    {
        return Double.parseDouble(Total.getText().toString());
    }
    public Double getPerHeadTotal()
    {

        return Double.parseDouble(PerTotal.getText().toString());
    }

    public void DialogueBox(Activity m)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(m);

        alert.setTitle(POPUP_LOGIN_TITLE);
        alert.setMessage(POPUP_LOGIN_TEXT);

        // Set an EditText view to get user input
        name = new EditText(m);
        name.setHint(Name);
        phone = new EditText(m);
        phone.setHint(Phone);
        DepositMoney = new EditText(m);
        DepositMoney.setHint(DepositeMoney);

        //making layout to hold the options
        LinearLayout layout = new LinearLayout(m.getApplicationContext());


        layout.setOrientation(LinearLayout.VERTICAL);

        //adding into layout
        layout.addView(name);
        layout.addView(phone);
        layout.addView(DepositMoney);



        alert.setView(layout);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try
                {
                    if(getName()!=null&&getPhone()!=null&&getDepositMoney()!=null)
                    {

                        MembersInfo mem=new MembersInfo(getName(),getPhone(),getDepositMoney());
                        ArrayList< HashMap<String, String>> det=new ArrayList<>();
                        det=mem.getdetails();
                        HashMap<String, String> tmpData = (HashMap<String,String>) det.get(det.size()-1);
                        String TAG_NAME = "Name";
                        Member.v.add(tmpData.get(TAG_NAME));
                        Member.adapter.notifyDataSetChanged();
                    }



                }catch(Exception e)
                {

                }

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();

    }
    public void DialogueBoxMembersUpdate(Activity m,String n,String p,String d)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(m);

        alert.setTitle(POPUP_LOGIN_TITLE);
        alert.setMessage(POPUP_LOGIN_TEXT);

        // Set an EditText view to get user input
        name = new EditText(m);
        name.setHint(Name);
        phone = new EditText(m);
        phone.setHint(Phone);
        DepositMoney = new EditText(m);
        DepositMoney.setHint(DepositeMoney);

        name.setText(n);
        name.setEnabled(false);
        phone.setText(p);
        DepositMoney.setText(d);

        //making layout to hold the options
        LinearLayout layout = new LinearLayout(m.getApplicationContext());


        layout.setOrientation(LinearLayout.VERTICAL);

        //adding into layout
        layout.addView(name);
        layout.addView(phone);
        layout.addView(DepositMoney);



        alert.setView(layout);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try
                {
                    if(getName()!=null&&getPhone()!=null&&getDepositMoney()!=null)
                    {

                        MembersInfo mem=new MembersInfo();
                        mem.updateMemberDetails(getName(),getPhone(),Double.toString(getDepositMoney()));
                        Member.reloadV();
                        Member.adapter.notifyDataSetChanged();
                    }



                }catch(Exception e)
                {

                }

            }
        });
        alert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                MembersInfo mem=new MembersInfo();
                mem.DeleteMember(getName(),getPhone(),Double.toString(getDepositMoney()));
                Member.reloadV();
                Member.adapter.notifyDataSetChanged();
            }
        });

        alert.show();

    }
    public void DialogueBoxExpenses(Activity m)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(m);

        alert.setTitle(POPUP_LOGIN_TITLE);
        alert.setMessage(POPUP_LOGIN_TEXT);

        // Set an EditText view to get user input
        info = new EditText(m);
        info.setHint(ExpenseInfos);
        Date = new EditText(m);
        Date.setHint(Dates);
        Total = new EditText(m);
        Total.setHint(Totals);
        PerTotal=new EditText(m);
        PerTotal.setHint(PerHeadTotal);

        PerTotal.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // your code here....
                MembersInfo mem=new MembersInfo();
                PerTotal.setText(Double.toString((getTotal()/MainActivity.memdb.getAllContacts().size())));
                return false;
            }
        });
        Calendar c = Calendar.getInstance();
        int munites = c.get(Calendar.MINUTE);
        int hour=c.get(Calendar.HOUR);
        int date=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        int year =c.get(Calendar.YEAR);
        String day=hour+":"+munites+"-"+date+"/"+month+"/"+year;
        Date.setText(day);
        //making layout to hold the options
        LinearLayout layout = new LinearLayout(m.getApplicationContext());


        layout.setOrientation(LinearLayout.VERTICAL);

        //adding into layout
        layout.addView(info);
        layout.addView(Date);
        layout.addView(Total);
        layout.addView(PerTotal);



        alert.setView(layout);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try
                {
                    if(getinfo()!=null&&getDate()!=null&&getTotal()!=null&&getPerHeadTotal()!=null)
                    {

                        ExpensesInfo mem=new ExpensesInfo(getinfo(),getDate(),getTotal(),getPerHeadTotal());
                        ArrayList< HashMap<String, String>> det=new ArrayList<>();
                        det=mem.getdetails();
                        HashMap<String, String> tmpData = (HashMap<String,String>) det.get(det.size()-1);
                        String  TAG_EXINFO=  "Info";
                        Expenses.vExpense.add(tmpData.get(TAG_EXINFO));

                        Expenses.adapterExpense.notifyDataSetChanged();

                        MembersInfo m=new MembersInfo();
                        m.updateDepositeMoney(getPerHeadTotal());

                        Expenses.setTotalexpenses();
                        Expenses.setPerheadexpenses();
                    }



                }catch(Exception e)
                {

                }

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();

    }

    private String m_Text = "";
    public void DialogueBox(Activity m, String message)
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(m);
        builder.setTitle(Title);
        builder.setMessage(message);


        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            // continue with delete
        }
        });
            builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            });
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.show();
    }

}
