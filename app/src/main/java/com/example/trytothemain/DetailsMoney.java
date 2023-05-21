package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsMoney extends  AppCompatActivity  {
    TextView tv;

    //All_Data expenses = new All_Data();




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_money);
        tv = (TextView) findViewById(R.id.tv);
       // String allExpenses = expenses.getData();
        //tv.setText(allExpenses);




    }







}