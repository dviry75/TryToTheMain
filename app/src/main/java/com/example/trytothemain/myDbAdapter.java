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



    public long insertData(Integer cost , String cate, String desc )
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
        String[] columns = {myDbHelper.UID,myDbHelper.COLUMN_COST,myDbHelper.COLUMN_CATEGORY,myDbHelper.COLUMN_DESCRIPTION};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            int cexp =cursor.getInt(cursor.getColumnIndex(myDbHelper.COLUMN_COST));
            String cate =cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_CATEGORY));
            String  desc =cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_DESCRIPTION));
            buffer.append( "   "+cexp +" " + cate + "   " + desc +" \n");
        }
        return buffer.toString();
    }



    public int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.COLUMN_CATEGORY+" = ?",whereArgs);
        return  count;
    }




    public void deleteCustomerByRow1(int rowId)
    {
        String str_sql = "delete from " + myDbHelper.TABLE_NAME + " where " + myDbHelper.UID  + " = "  + rowId ;
        dv.execSQL(str_sql);
    }






    public void deleteAllRows() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.delete(myDbHelper.TABLE_NAME, null, null);
        db.close();
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
        String query = "SELECT cost, category, description FROM " + myDbHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Integer arrayCost = 0;
        String arrayCate = null;
        String arrayDesc = null;



        while (cursor.moveToNext()){
            arrayCost = cursor.getInt(cursor.getColumnIndex(myDbHelper.COLUMN_COST));
            arrayCate = cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_DESCRIPTION));
            arrayDesc = cursor.getString(cursor.getColumnIndex(myDbHelper.COLUMN_CATEGORY));



            String getArrayDescirbationAndCategory =  "ההוצאה: " + String.valueOf(arrayCost) +" , הקטיגוריה: "+ arrayCate + " , תיאור ההוצאה: " + arrayDesc;

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
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COST + " INTEGER, " +
                COLUMN_DATE + " VARCHAR(255), " + COLUMN_CATEGORY + " VARCHAR(255), " +
                COLUMN_DESCRIPTION + " VARCHAR(255));";

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