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

    public long insert(int cost , String cate, String desc,String location , byte[] imageBytes) {
        long id = helper.insertData(cost , cate, desc , location ,imageBytes );
        return id;
    }
    public long insertDataWithoutLocation(int cost , String cate, String desc,byte[] imageBytes ) {
        long id = helper.insertDataWithoutLocation(cost , cate, desc , imageBytes);
        return id;
    }
    public long insertDataWithoutBill(int cost , String cate, String desc,String location ) {
        long id = helper.insertDataWithoutBill(cost , cate, desc ,  location);
        return id;
    }
    public long insertDataJustInfo(int cost , String cate, String desc) {
        long id = helper.insertDataJustInfo(cost , cate, desc);
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

    public int getCostOnDesc(String desc){
        return helper.getCostOnDesc(desc);
    }
    public String getCateOnDesc(String desc){
        return helper.getCateOnDesc(desc);
    }

    public String getDateOnDesc(String desc){
        return helper.getDateOnDesc(desc);
    }
    public String getLocationOnDesc(String desc){
        return helper.getLocationOnDesc(desc);
    }

    public int deleteByDesc(String desc){
        int a = helper.deleteByDesc(desc);
        return a;
    }
    public ImageData getBillOnDesc(String desc){
        return helper.getBillOnDesc(desc);
    }






    public String getSumExpenses(){

        return helper.getSumExpenses();

    }
    public String getSumExpensesOnCategory(String cate){

        return helper.getSumExpensesOnCategory(cate);

    }





}
