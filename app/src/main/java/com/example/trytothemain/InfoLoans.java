package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoLoans extends AppCompatActivity {
    LoanHelper loans = new LoanHelper(this);
    TextView nameLoan , sumLoan;
    Button callNum;
    String name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_loans);
        nameLoan = (TextView) findViewById(R.id.infoNameLoan);
        sumLoan = (TextView)findViewById(R.id.infoSumLoan);
        callNum = (Button)findViewById(R.id.btnCall);


        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        nameLoan.setText("שם פרטי - " + name);
        sumLoan.setText("סכום ההלוואה - " + String.valueOf(loans.getSumOnName(name)));
        callNum.setText(loans.getNumOnName(name));
    }

    public void openDialLoan(View v){
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        String contactsDial = loans.getNumOnName(name);
        String phoneNumber = "tel:" + contactsDial;
        dialIntent.setData(Uri.parse(phoneNumber));
        startActivity(dialIntent);
    }
}