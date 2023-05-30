package com.example.trytothemain;

import android.content.Context;

public class LoanHelper {
    myDbLoans helper;
    public LoanHelper(Context context){
        helper = new myDbLoans(context);
    }

    public long insert(String name , int sum , int phone){
        return helper.insertData(name,sum,phone);
    }
}
