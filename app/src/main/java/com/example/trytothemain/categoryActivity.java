package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class categoryActivity extends AppCompatActivity {
    Dialog categoryDialod;
    EditText writeCategory;//hfjdth

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);






    }










    public void addCategory(View view){
        createLoginDialog();

    }

    public void saveCategory(View view){
        categoryDialod.dismiss();
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