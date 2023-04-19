package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class categoryActivity extends AppCompatActivity {
    Dialog categoryDialod;
    EditText writeCategory;
    String anotherCategory;
    LinearLayout main;

    TextView [] category = new TextView[10];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        main = (LinearLayout) findViewById(R.id.main);





    }








//585858

    public void addCategory(View view){

        createLoginDialog();



    }



    public void saveCategory(View view){
        anotherCategory = writeCategory.getText().toString();
        int index = returnNull(category);
        if(index == 99){
            Toast.makeText(this, "השתמשת בכל הקטיגוריות ", Toast.LENGTH_SHORT).show();
        }
        else {
            int num = index + 1;
            category[index] = new TextView(this);
            category[index].setText(num + ". " + anotherCategory);
            category[index].setTextSize(40);
            category[index].setTextColor(getResources().getColor(R.color.black));
            int leftMargin = 5;
            int topMargin = 0;
            int rightMargin = 100;
            int bottomMargin = 5;


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(750, 135);
            layoutParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            category[index].setLayoutParams(layoutParams);
            main.addView(category[index]);

        }
        categoryDialod.dismiss();

    }


    public void removeCategory(View view){



    }//dd












    public void createLoginDialog() //פונקציית פתיחת הדיאלוג
    {
        categoryDialod = new Dialog(this);
        categoryDialod.setContentView(R.layout.add_category_dialog);
        categoryDialod.setTitle("הוספת קטיגוריה");
        categoryDialod.setCancelable(true);
        writeCategory =(EditText)categoryDialod.findViewById(R.id.writeCategory);
        categoryDialod.show();



    }



    public int returnNull(TextView caegories[]){ // פעולה המקבלת את המערך של הקטיגוריות ומחזירה את הטקסט שריק
        for(int i =0 ; i<category.length; i++){
            if(category[i] == null){
                return i;
            }
        }
        return  99;
    }






}