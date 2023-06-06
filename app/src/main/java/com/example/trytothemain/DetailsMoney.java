package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailsMoney extends  AppCompatActivity implements AdapterView.OnItemClickListener  {
    TextView tv;
    All_Data expenses = new All_Data(this);
    ArrayList<String> expensesList = new ArrayList<String>();
    ListView expenseTv;









    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_money);



        tv = (TextView)findViewById(R.id.your_month);
        tv.setText(CurrentDate.currentDate() + " הוצאות - ");
        expenseTv = (ListView)findViewById(R.id.listView);
        expenseTv.setOnItemClickListener(this);





    }

    @Override
    protected void onStart() {
        super.onStart();

        if(expenses.getArrayData() == null){

        }
        else{
            expensesList = expenses.getArrayData();
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.list_expenses, expensesList);
            expenseTv.setAdapter(dataAdapter);


        }
    }

    public void deleteAll(View view){
        if(expensesList.isEmpty()){
            Message.message(getApplicationContext() , "אין מה למחוק הכל ריק");
        }
        else{
            expenses.deleteAllRows();
            expensesList = expenses.getArrayData();
            expensesList.clear();
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.list_expenses, expensesList);
            expenseTv.setAdapter(dataAdapter);
        }



    }



    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String thatExpen = parent.getItemAtPosition(position).toString();
        String [] arrayA  = thatExpen.split("\n");
        String [] arrayA1  = arrayA[1].split("\n");
        String [] arrayB = arrayA1[0].split(":");
        String description = arrayB[1];
        Intent intent = new Intent(this ,infoDetail.class);
        intent.putExtra("desc" , description);
        startActivity(intent);

    }
}