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

public class DetailsLoans extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView tv;
    ArrayList<String> LoanList = new ArrayList<String>();
    ListView LoanTV;
    LoanHelper loans = new LoanHelper(this);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_loans);



        tv = (TextView)findViewById(R.id.mLoan);
        tv.setText( "הלוואות  ");
        LoanTV = (ListView)findViewById(R.id.listViewloans);
        LoanTV.setOnItemClickListener(this);





    }


    protected void onStart() {
        super.onStart();

        if(loans.getArrayData().isEmpty()){
            Message.message(getApplicationContext() , "אין הלוואות");
        }//fff
        else{
            LoanList = loans.getArrayData();
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.list_loans, LoanList);
            LoanTV.setAdapter(dataAdapter);


        }
    }

    public void deleteAllLoans(View view){
        if(LoanList.isEmpty()){
            Message.message(getApplicationContext() , "אין מה למחוק הכל ריק");
        }
        else{
            loans.deleteAllRows();
            LoanList = loans.getArrayData();
            LoanList.clear();
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.list_loans, LoanList);
            LoanTV.setAdapter(dataAdapter);
        }



    }



    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String thatLoan = parent.getItemAtPosition(position).toString();

        String [] arrayA  = thatLoan.split("\n");
        String [] arrayB = arrayA[0].split(":");
        String nameLoan = arrayB[1];
        Intent intent = new Intent(this ,InfoLoans.class);
        intent.putExtra("name" , nameLoan);
        startActivity(intent);

    }
}