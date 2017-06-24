package com.example.abdullah.traveltourism.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.abdullah.traveltourism.Fragments.Member;
import com.example.abdullah.traveltourism.StaticDatas.Members;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdullah on 5/25/17.
 */

public class MembersDB extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "tourtravel";

    // Contacts table name
    private static final String TABLE_CONTACTS = "Member";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_DE_MO = "deposited_money";


    public MembersDB(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," +KEY_DE_MO + " TEXT"+ ")";
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
    // Adding new contact
    public void addMembers(Members members) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,members.getName() ); // Contact Name
        values.put(KEY_PH_NO, members.getPhn()); // Contact Phone Number
        values.put(KEY_DE_MO, members.getDepositeMoney()); // Contact Deposited Money

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }
    // Getting All Contacts
    public List<Members> getAllContacts() {

        List<Members> membersList = new ArrayList<Members>();

        try {
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Members contact = new Members();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setPhn(cursor.getString(2));
                    contact.setDepositeMoney(Double.parseDouble(cursor.getString(3)));
                    // Adding contact to list
                    membersList.add(contact);
                } while (cursor.moveToNext());
            }

        }catch (SQLiteException e)
        {
            resetTable();
            String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Members contact = new Members();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setPhn(cursor.getString(2));
                    contact.setDepositeMoney(Double.parseDouble(cursor.getString(3)));
                    // Adding contact to list
                    membersList.add(contact);
                } while (cursor.moveToNext());
            }

        }

        // return contact list
        return membersList;
    }
    // Updating single contact
    public int updateContact(Members contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, contact.getId());
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhn());
        values.put(KEY_DE_MO, contact.getDepositeMoney());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }
    // Deleting single contact
    public void deleteContact(Members contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        /*db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
                */
        db.delete(TABLE_CONTACTS,KEY_ID+"="+contact.getId(),null);
        db.close();
    }

}
