package com.example.trytothemain;

import android.content.Context;

import java.util.ArrayList;

public class LoanHelper {
    myDbLoans helper;
    public LoanHelper(Context context){
        helper = new myDbLoans(context);
    }

    public long insert(String name , int sum , String phone){
        return helper.insertData(name,sum,phone);
    }
    public ArrayList<String> getArrayData(){

        return  helper.getArrayData();
    }
    public void deleteAllRows(){

        helper.deleteAllRows();
    }
    public int getSumOnName(String name){
        return helper.getSumOnName(name);
    }
    public String getNumOnName(String name){
        return helper.getNumOnName(name);
    }
}
