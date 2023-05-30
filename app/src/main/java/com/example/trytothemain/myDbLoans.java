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



    public long insertData(String name , Integer sum , Integer numPhone)
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

    public ArrayList<String> getArrayData(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ArrayList<String> helperGetArrayData = new ArrayList<>();
        String query = "SELECT nameOfCategory FROM " + myDbHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String arrayCate = null;




        while (cursor.moveToNext()){
            arrayCate = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));



            helperGetArrayData.add(arrayCate);
        }
        return helperGetArrayData;


    } //משתמש.

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        return count;
    }*/











    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabaseLoans";    // Database Name
        private static final String TABLE_NAME = "Loan";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "nameOfContacts";//Column II
        private static final String CLOANS = "theLoan";//Column III
        private static final String NUMBER = "phoneOfContacts";//Column IIII

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " +
                CLOANS + " INTEGER, " + NUMBER + " INTEGER);";
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

