package com.example.trytothemain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class myDbLoans {

    myDbHelper myhelper;
    SQLiteDatabase dv;



    public myDbLoans(Context context)
    {

        myhelper = new myDbHelper(context);
    }



    public long insertData(String name , Integer sum , String numPhone)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.CLOANS, sum);
        contentValues.put(myDbHelper.NUMBER, numPhone);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;

    }



    /*public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            buffer.append( name + "   "  +" \n");
        }
        return buffer.toString();
    }


    public int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME , myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }


    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        return count;
    }*/

    public ArrayList<String> getArrayData(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ArrayList<String> helperGetArrayData = new ArrayList<>();
        String query = "SELECT nameOfContact, sumLoan FROM " + myDbHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String arrayName = null;
        Integer arraySum = 0;

        while (cursor.moveToNext()){
            arrayName = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            arraySum = cursor.getInt(cursor.getColumnIndex(myDbHelper.CLOANS));



            String getArrayDescirbationAndCategory =  "שם פרטי: " + arrayName + "\nכמה הלווית: " + String.valueOf(arraySum) ;

            helperGetArrayData.add(getArrayDescirbationAndCategory);
        }
        return helperGetArrayData;
    }
    public void deleteAllRows() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.delete(myDbHelper.TABLE_NAME, null, null);
        db.close();
    }

    public int getSumOnName(String name) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        int sum = 0;

        String query = "SELECT " + myDbHelper.CLOANS +
                " FROM " + myDbHelper.TABLE_NAME +
                " WHERE " + myDbHelper.NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name.trim()});

        if (cursor.moveToFirst()) {
            sum = cursor.getInt(cursor.getColumnIndex(myDbHelper.CLOANS));
        }
        cursor.close();
        db.close();
        // Return the cost or do something with the retrieved data
        return sum;
    }
    public String getNumOnName(String name) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String phone = null;

        String query = "SELECT " + myDbHelper.NUMBER +
                " FROM " + myDbHelper.TABLE_NAME +
                " WHERE " + myDbHelper.NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name.trim()});

        if (cursor.moveToFirst()) {
            phone = cursor.getString(cursor.getColumnIndex(myDbHelper.NUMBER));
        }
        cursor.close();
        db.close();
        // Return the cost or do something with the retrieved data
        return phone;
    }












    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabaseLoans";    // Database Name
        private static final String TABLE_NAME = "Loan";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "nameOfContact";//Column II
        private static final String CLOANS = "sumLoan";//Column III
        private static final String NUMBER = "phoneOfContact";//Column IIII

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " +
                CLOANS + " INTEGER, " + NUMBER + " VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;



        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }


        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}

