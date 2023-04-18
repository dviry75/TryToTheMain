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










    public void addCategory(View view){
        createLoginDialog();
    }

    public void saveCategory(View view){
        anotherCategory = writeCategory.getText().toString();
        categoryDialod.dismiss();
        for (int i =0;i<category.length;i++){
            if(category[i] == null){
                category[i] = new TextView(this);
                category[i].setText(anotherCategory);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100,100);
                category[i].setLayoutParams(layoutParams);
                main.addView(category[i]);
                i++;
            }
            else Toast.makeText(this, "השתמשת בכל הקטיגוריות ", Toast.LENGTH_SHORT).show();
        }
    }



    public void createLoginDialog() //פונקציית פתיחת הדיאלוג
    {
        categoryDialod = new Dialog(this);
        categoryDialod.setContentView(R.layout.add_category_dialog);
        categoryDialod.setTitle("Login");
        categoryDialod.setCancelable(false);
        writeCategory =(EditText)categoryDialod.findViewById(R.id.writeCategory);

        categoryDialod.show();

    }






}