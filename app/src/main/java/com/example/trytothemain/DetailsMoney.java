package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailsMoney extends  AppCompatActivity  {
    TextView tv;
    All_Data expenses = new All_Data(this);
    ArrayList<String> expensesList = new ArrayList<String>();
    ListView expenseTv;








    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_money);

        expenseTv = (ListView)findViewById(R.id.listView);
        String allExpenses = expenses.getData();
        expensesList.add(allExpenses);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.list_expenses, expensesList);
        expenseTv.setAdapter(dataAdapter);

















    }






    public void deleteAll(View view){
        if(expensesList.isEmpty()){
            Message.message(getApplicationContext() , "אין מה למחוק הכל ריק");
        }
        else{
            expenses.deleteAllRows();
            expensesList.remove(0);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.list_expenses, expensesList);
            expenseTv.setAdapter(dataAdapter);
        }



    }















}