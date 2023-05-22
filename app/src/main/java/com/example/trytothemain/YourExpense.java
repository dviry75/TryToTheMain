package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
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

public class YourExpense extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    All_Data expenses = new All_Data(this);

    Spinner choose_category;
    List<String> category;
    Button takeBill , save;
    ImageView bill;
    Bitmap bitBill;
    Switch saveLocation;
    EditText etDescription;

    String nameOfCategory;
    ArrayAdapter<String> dataAdapter ;






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_expense);

        saveLocation = (Switch)findViewById(R.id.save_location);
        saveLocation.setOnCheckedChangeListener(this);





        etDescription = (EditText)findViewById(R.id.et_description);

        takeBill = (Button) findViewById(R.id.take_invoicing);
        bill = (ImageView) findViewById(R.id.invoicing);
        takeBill.setOnClickListener(YourExpense.this);
        save = (Button) findViewById(R.id.saveBill);

        //עשיית הקטיוגריות באובייקט ספינר
        choose_category = (Spinner) findViewById(R.id.open_category);  // לבדוק איך מעדכנים מאקטביטי אחר
        category = new ArrayList<String>();
        category.add("בגדים");
        category.add("אוכל");
        category.add("כלי מטבח");
        category.add("חברים");
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, category);
        choose_category.setAdapter(dataAdapter);
        choose_category.setOnItemSelectedListener(this);












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
        nameOfCategory = parent.getItemAtPosition(position).toString();    //בדיקה איך קוראים לפריט

    }
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onClick(View view) {
        if(view == takeBill){
            save.setVisibility(View.VISIBLE);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,0);
        }

    }

    public void saveExpense(View view){// t1 וt2 הם התיאור והקטיגוריה שכרגע אני רוצה להכניס לדאטה בייס
        String totalDescribation = etDescription.getText().toString();
        if(totalDescribation.isEmpty() || nameOfCategory.isEmpty())
        {
            Message.message(getApplicationContext(),"לא הכנסת את כל הפרטים");
        }
        else {


            long id = expenses.insert(totalDescribation, nameOfCategory);
            if(id<=0)
            {
                Message.message(getApplicationContext(),"השמירה נכשלה");

            }
            else
            {
                Message.message(getApplicationContext(),"נשמר בהצלחה");
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        }






    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean b) { // פעולת השמירת מיקום
        if(b){
            save.setVisibility(View.VISIBLE);

        }
    }
}