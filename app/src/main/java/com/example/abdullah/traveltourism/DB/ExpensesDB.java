package com.example.abdullah.traveltourism.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.abdullah.traveltourism.StaticDatas.Expense;
import com.example.abdullah.traveltourism.StaticDatas.Members;

import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by abdullah on 5/25/17.
 */

public class ExpensesDB extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "tourtravel";

    // Contacts table name
    private static final String TABLE_CONTACTS = "Expenses";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_INFO = "info";
    private static final String KEY_DATE = "date";
    private static final String KEY_TO_MO = "total_money";
    private static final String KEY_PE_MO = "per_head_money";


    public ExpensesDB(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INFO + " TEXT,"
                + KEY_DATE+ " TEXT,"+ KEY_TO_MO+ " TEXT," +KEY_PE_MO + " TEXT"+ ")";


        db.execSQL(CREATE_CONTACTS_TABLE);

    }
    public void resetTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }
    // Adding new Expense
    public void addExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INFO,expense.getExpenseinfo()); // Expense Info
        values.put(KEY_DATE,expense.getDate()); // Date
        values.put(KEY_TO_MO,expense.getTotalMoney()); // Total Money of expenses
        values.put(KEY_PE_MO,expense.getPerHeadMoney()); // per head expense cost


        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }
    // Getting All Contacts
    public List<Expense> getAllExpenses() {
        List<Expense> membersList = new ArrayList<Expense>();
        // Select All Query

        try {
            String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Expense contact = new Expense();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setExpenseinfo(cursor.getString(1));
                    contact.setDate(cursor.getString(2));
                    contact.setTotalMoney(Double.parseDouble(cursor.getString(3)));
                    contact.setPerHeadMoney(Double.parseDouble(cursor.getString(4)));
                    // Adding contact to list
                    membersList.add(contact);
                } while (cursor.moveToNext());
            }

        }catch(SQLiteException e)
        {
            resetTable();
            String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Expense contact = new Expense();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setExpenseinfo(cursor.getString(1));
                    contact.setDate(cursor.getString(2));
                    contact.setTotalMoney(Double.parseDouble(cursor.getString(3)));
                    contact.setPerHeadMoney(Double.parseDouble(cursor.getString(4)));
                    // Adding contact to list
                    membersList.add(contact);
                } while (cursor.moveToNext());
            }
        }


        // return contact list
        return membersList;
    }

}
