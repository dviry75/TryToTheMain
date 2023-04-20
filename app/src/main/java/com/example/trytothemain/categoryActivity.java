
package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class categoryActivity extends AppCompatActivity {
    Dialog categoryDialod , removeCategoryDialod;
    EditText writeCategory , removeDialogCategory;
    String anotherCategory;
    LinearLayout main;
    ArrayAdapter<String> dataAdapter;
    List<String> category = new ArrayList<String>();
    ListView categoryLV;
    int num = 0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        categoryLV = findViewById(R.id.category_lv);

    }








//585858

    public void addCategory(View view){
        createAddCategoryDialog();
    }



    public void saveCategory(View view){

        category.add(num+1 + ". " +writeCategory.getText().toString()); //כשתתחרט שעשית את זה פנה אליי
        dataAdapter = new ArrayAdapter<String>(this, R.layout.item_category, category);
        categoryLV.setAdapter(dataAdapter);

        categoryDialod.dismiss();
        num++;
    }


    public void removeCategory(View view){
        createRemoveCategoryDialog();
    }

    public void saveRemoveCategory(View view){
        if(num < 0){
            Toast.makeText(this, "אין קטיגוריות למחיקה", Toast.LENGTH_SHORT).show();
        }
        else {
            category.remove(Integer.parseInt(removeDialogCategory.getText().toString())-1);
            dataAdapter = new ArrayAdapter<>(this, R.layout.item_category, category);
            categoryLV.setAdapter(dataAdapter);
            num--;
            removeCategoryDialod.dismiss();

        }

    }










    public void createRemoveCategoryDialog() //פונקציית פתיחת הדיאלוג של המחיקה
    {
        removeCategoryDialod = new Dialog(this);
        removeCategoryDialod.setContentView(R.layout.remove_category);
        removeCategoryDialod.setTitle("מחיקת קטיגוריה");
        removeCategoryDialod.setCancelable(true);
        removeDialogCategory =(EditText)removeCategoryDialod.findViewById(R.id.removeET);
        removeCategoryDialod.show();
    }


    public void createAddCategoryDialog() //פונקציית פתיחת הדיאלוג של ההוספה
    {
        categoryDialod = new Dialog(this);
        categoryDialod.setContentView(R.layout.add_category_dialog);
        categoryDialod.setTitle("הוספת קטיגוריה");
        categoryDialod.setCancelable(true);
        writeCategory =(EditText)categoryDialod.findViewById(R.id.writeCategory);
        categoryDialod.show();



    }






}