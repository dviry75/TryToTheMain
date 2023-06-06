package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoCategory extends AppCompatActivity {
    TextView sumCate , nameCate;
    All_Data expenses = new All_Data(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_category);
        sumCate = (TextView) findViewById(R.id.infoSumCate);
        nameCate = (TextView) findViewById(R.id.infoCategoryIn);
        Intent intent=getIntent();
        String category = intent.getExtras().getString("cate");

        nameCate.setText("קטיגוריה - " + category);
        if (Integer.valueOf(expenses.getSumExpensesOnCategory(category)) == 0){
            Message.message(getApplicationContext() , "בקטיגוריה זו לא הוזנו הוצאות");
            sumCate.setText("סכום ההוצאה - 0 " );

        }
        else{
            String sum = expenses.getSumExpensesOnCategory(category);
            sumCate.setText("סכום ההוצאה - " + sum);

        }





    }
}