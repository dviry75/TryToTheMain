package com.example.trytothemain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class myDbAdapter {
    myDbHelper myhelper;
    SQLiteDatabase dv;
    public myDbAdapter(Context context)
    {

        myhelper = new myDbHelper(context);
    }



    public long insertData(Integer cost , String cate, String desc, String location , byte[] imageBytes)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.COLUMN_COST, cost);
        contentValues.put(myDbHelper.COLUMN_CATEGORY, cate);
        contentValues.put(myDbHelper.COLUMN_DESCRIPTION, desc);
        String yourDate = CurrentDate.currentDate();
        contentValues.put(myDbHelper.COLUMN_DATE, yourDate);
        contentValues.put(myDbHelper.COLUMN_LOCATION , location);
        contentValues.put(myDbHelper.COLUMN_BILL , imageBytes);
        long id = dbb.insert(myDbHelper.TABLE_NAME , null , contentValues);
        return id;
    }
    public long insertDataWithoutLocation(Integer cost , String cate, String desc , byte[] imageBytes)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.COLUMN_COST, cost);
        contentValues.put(myDbHelper.COLUMN_CATEGORY, cate);
        contentValues.put(myDbHelper.COLUMN_DESCRIPTION, desc);
        String yourDate = CurrentDate.currentDate();
        contentValues.put(myDbHelper.COLUMN_DATE, yourDate);
        contentValues.put(myDbHelper.COLUMN_BILL , imageBytes);
        long id = dbb.insert(myDbHelper.TABLE_NAME , null , contentValues);
        return id;
    }

    public long insertDataWithoutBill(Integer cost , String cate, String desc , String location)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.COLUMN_COST, cost);
        contentValues.put(myDbHelper.COLUMN_CATEGORY, cate);
        contentValues.put(myDbHelper.COLUMN_DESCRIPTION, desc);
        String yourDate = CurrentDate.currentDate();
        contentValues.put(myDbHelper.COLUMN_DATE, yourDate);
        contentValues.put(myDbHelper.COLUMN_LOCATION , location);
        long id = dbb.insert(myDbHelper.TABLE_NAME , null , contentValues);
        return id;
    }

    public long insertDataJustInfo(Integer cost , String cate, String desc)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.COLUMN_COST, cost);
        contentValues.put(myDbHelper.COLUMN_CATEGORY, cate);
        contentValues.put(myDbHelper.COLUMN_DESCRIPTION, desc);
        String yourDate = CurrentDate.currentDate();
        contentValues.put(myDbHelper.COLUMN_DATE, yourDate);
        long id = dbb.insert(myDbHelper.TABLE_NAME , null , contentValues);
        return id;
    }





   /* public void open()
    {
        dv=myhelper.getWritableDatabase();
    }

    public void close() {


    }*/






    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.COLUMN_COST,myDbHelper.COLUMN_CATEGORY,myDbHelper.COLUMN_DESCRIPTION};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cexp =cursor.getInt(cursor.getColumnIndex(myDbHelper.COLUMN_COST));
            String cate =cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_CATEGORY));
            String  desc =cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_DESCRIPTION));
            buffer.append( "   "+cexp +" " + cate + "   " + desc +" \n");
        }
        return buffer.toString();
    }



    public String getSumExpenses()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        int cSum = 0;
        String[] columns = {myDbHelper.COLUMN_COST};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cexp =cursor.getInt(cursor.getColumnIndex(myDbHelper.COLUMN_COST));

            cSum += cexp;

        }
        buffer.append( "   "+cSum +" " );
        return buffer.toString();
    }
    public String getSumExpensesOnCategory(String cate)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        int cSum = 0;
        String[] columns = {myDbHelper.COLUMN_COST};
        String query = "SELECT " + myDbHelper.COLUMN_COST +
                " FROM " + myDbHelper.TABLE_NAME +
                " WHERE " + myDbHelper.COLUMN_CATEGORY + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{cate.trim()});
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cexp =cursor.getInt(cursor.getColumnIndex(myDbHelper.COLUMN_COST));

            cSum += cexp;

        }
        buffer.append(""+cSum +"");
        return buffer.toString();
    }



    public int deleteByDesc(String desc)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={desc.trim()};
        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.COLUMN_DESCRIPTION+" = ?",whereArgs);
        return  count;
    }









    public void deleteAllRows() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.delete(myDbHelper.TABLE_NAME, null, null);
        db.close();
    }


    public int getCostOnDesc(String desc) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        int cost = 0;

        String query = "SELECT " + myDbHelper.COLUMN_COST +
                " FROM " + myDbHelper.TABLE_NAME +
                " WHERE " + myDbHelper.COLUMN_DESCRIPTION + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{desc.trim()});

        if (cursor.moveToFirst()) {
            cost = cursor.getInt(cursor.getColumnIndex(myDbHelper.COLUMN_COST));
        }
        cursor.close();
        db.close();
        // Return the cost or do something with the retrieved data
        return cost;
    }

    public String getCateOnDesc(String desc) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String cate = null;

        String query = "SELECT " + myDbHelper.COLUMN_CATEGORY +
                " FROM " + myDbHelper.TABLE_NAME +
                " WHERE " + myDbHelper.COLUMN_DESCRIPTION + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{desc.trim()});

        if (cursor.moveToFirst()) {
            cate = cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_CATEGORY));
        }
        cursor.close();
        db.close();
        // Return the cost or do something with the retrieved data
        return cate;
    }
    public String getLocationOnDesc(String desc) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String cate = null;

        String query = "SELECT " + myDbHelper.COLUMN_LOCATION +
                " FROM " + myDbHelper.TABLE_NAME +
                " WHERE " + myDbHelper.COLUMN_DESCRIPTION + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{desc.trim()});

        if (cursor.moveToFirst()) {
            cate = cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_LOCATION));
        }
        cursor.close();
        db.close();
        // Return the cost or do something with the retrieved data
        return cate;
    }


    public String getDateOnDesc(String desc) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String date = null;

        String query = "SELECT " + myDbHelper.COLUMN_DATE +
                " FROM " + myDbHelper.TABLE_NAME +
                " WHERE " + myDbHelper.COLUMN_DESCRIPTION + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{desc.trim()});

        if (cursor.moveToFirst()) {
            date = cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_DATE));
        }
        cursor.close();
        db.close();
        // Return the cost or do something with the retrieved data
        return date;
    }

    public ImageData getBillOnDesc(String desc) {
        SQLiteDatabase db = myhelper.getWritableDatabase();

        String query = "SELECT " + myDbHelper.COLUMN_BILL +
                " FROM " + myDbHelper.TABLE_NAME +
                " WHERE " + myDbHelper.COLUMN_DESCRIPTION + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{desc.trim()});

        ImageData imageData = null;
        if (cursor.moveToFirst()) {
            byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(myDbHelper.COLUMN_BILL));
            imageData = new ImageData(imageBytes);
        }

        cursor.close();
        db.close();

        return imageData;
    }

















    public String getLastInsertedData() {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String query = "SELECT " +myDbHelper.COLUMN_COST + ", "+ myDbHelper.COLUMN_DESCRIPTION + ", " + myDbHelper.COLUMN_CATEGORY +
                " FROM " + myDbHelper.TABLE_NAME +
                " WHERE " + myDbHelper.UID + " = (SELECT MAX(" + myDbHelper.UID + ") FROM " + myDbHelper.TABLE_NAME + ")";
        Cursor cursor = db.rawQuery(query, null);

        int cexp = 0;
        String lastInsertedCategory = null;
        String lastInsertedDescription = null;

        if (cursor.moveToFirst()) {

            cexp = cursor.getInt(cursor.getColumnIndex(myDbHelper.COLUMN_COST));
            lastInsertedCategory = cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_DESCRIPTION));
            lastInsertedDescription = cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_CATEGORY));
        }

        cursor.close();
        db.close();

        // You can use the retrieved values here or create an object to hold them
        // and return the object instead.
        return "Your cost: " + String.valueOf(cexp) + "Category: " + lastInsertedCategory + ", Description: " + lastInsertedDescription;
    }
    public ArrayList<String> getArrayData(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ArrayList<String> helperGetArrayData = new ArrayList<>();
        String query = "SELECT cost, category, description, date FROM " + myDbHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Integer arrayCost = 0;
        String arrayCate = null;
        String arrayDate = null;
        String arrayDesc = null;

        while (cursor.moveToNext()){
            arrayCost = cursor.getInt(cursor.getColumnIndex(myDbHelper.COLUMN_COST));
            arrayDesc = cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_DESCRIPTION));
            arrayCate = cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_CATEGORY));
            arrayDate = cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_DATE));


            String getArrayDescirbationAndCategory =  "הוצאה: " + String.valueOf(arrayCost) + "\nתיאור ההוצאה: " + arrayDesc + "\nקטיגוריה: "+ arrayCate + "\nתאריך ההוצאה: " + arrayDate;

            helperGetArrayData.add(getArrayDescirbationAndCategory);
        }
        return helperGetArrayData;
    }


















    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabaseExpenses";    // Database Name
        private static final String TABLE_NAME = "Expense";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String COLUMN_COST = "cost"; //Column II
        private static final String COLUMN_CATEGORY = "category";    //Column III
        private static final String COLUMN_DESCRIPTION= "description";    // Column IIII
        private static final String COLUMN_DATE = "date"; // Column IIIII
        private static final String COLUMN_LOCATION = "location"; // Column IIIII
        private static final String COLUMN_BILL = "bill";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COST + " INTEGER, " +
                COLUMN_DATE + " VARCHAR(255), " + COLUMN_CATEGORY + " VARCHAR(255), " +
                COLUMN_DESCRIPTION + " VARCHAR(255), " + COLUMN_BILL + " BLOB, " + COLUMN_LOCATION  + " VARCHAR(255));";

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





        @Override
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