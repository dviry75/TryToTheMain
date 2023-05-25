package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class All_Data extends AppCompatActivity {
    myDbAdapter helper;

    public All_Data(Context context) {

        helper = new myDbAdapter(context);

    }

    public long insert(int cost , String desc, String cate ) {
        long id = helper.insertData(cost , desc, cate);
        return id;
    }



    public String getData(){
        String data = helper.getData();
        return data;
    }


    public void deleteAllRows(){

        helper.deleteAllRows();
    }



    public String getLastInsertedData(){

        return helper.getLastInsertedData();
    }

    public ArrayList<String> getArrayData(){
        return  helper.getArrayData();
    }






}
