package com.example.trytothemain;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class All_Data extends AppCompatActivity {
    myDbAdapter helper = new myDbAdapter(this);

    public All_Data () {
    }

    public void insert(String desc , String cate ) {
        long id = helper.insertData(desc, cate);
        if(id<=0)
        {
            Message.message(getApplicationContext(),"Insertion Unsuccessful");
        }
        else
        {
            Message.message(getApplicationContext(),"Insertion Successful");
        }
    }


    public String getData(){
        String data = helper.getData();
        return data;
    }






}
