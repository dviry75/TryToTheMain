package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class YourExpense extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    Spinner choose_category;
    List<String> category;
    Button takeBill;
    ImageView bill;
    Bitmap bitBill;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_expense);

        takeBill = (Button) findViewById(R.id.take_invoicing);
        bill = (ImageView) findViewById(R.id.invoicing);
        takeBill.setOnClickListener(this);




        //עשיית הקטיוגריות באובייקט ספינר
        setContentView(R.layout.activity_your_expense);
        choose_category = (Spinner) findViewById(R.id.open_category);  // לבדוק איך מעדכנים מאקטביטי אחר
        category = new ArrayList<String>();
        category.add("בגדים");
        category.add("אוכל");
        category.add("כלי מטבח");
        category.add("חברים");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, category);
        choose_category.setAdapter(dataAdapter);
        choose_category.setOnItemSelectedListener(this);

    }
    public void onClick(View view) {
        if(view == takeBill){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,0);
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)//coming from camera
        {
            if (resultCode == RESULT_OK) {
                bitBill= (Bitmap)data.getExtras().get("data");
                bill.setImageBitmap(bitBill);
            }
        }
    }
















    //הפונקצייה של האובייקט ספינר
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> parent) {
    }



}