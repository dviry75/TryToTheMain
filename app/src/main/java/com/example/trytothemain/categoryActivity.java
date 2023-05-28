
package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class categoryActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    Dialog categoryDialog , PressCategoryDialog ,  updateCategory;
    EditText writeCategory , updateCurrent , updateNew;
    ArrayAdapter<String> dataAdapter;
    ArrayList<String> category = new ArrayList<String>();
    ListView categoryLV;
    int num = 0;
    CategoryHelper DbCategory = new CategoryHelper(this);
    String ActionCategory;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        categoryLV = findViewById(R.id.category_lv);
        categoryLV.setOnItemLongClickListener(this);
        if (DbCategory.getData() == null){

        }
        else{
            category = DbCategory.getArrayData();
            dataAdapter = new ArrayAdapter<String>(this, R.layout.item_category, category);
            categoryLV.setAdapter(dataAdapter);
        }

    }









    public void addCategory(View view){

        createAddCategoryDialog();
    } //  הפתיחה עצמה של דיאלטג ההוספה



    public void saveCategory(View view){
        if (num < 7){
            if (writeCategory.getText().toString().isEmpty()){
                Message.message(getApplicationContext() , "הכנס שם קטיגוריה");
            }
            else {
                long id =  DbCategory.insert(writeCategory.getText().toString());
                if(id<=0)
                {
                    Message.message(getApplicationContext(),"השמירה נכשלה");
                }
                else
                {
                    Message.message(getApplicationContext(),"השמירה בוצעה בהצלחה");
                    category = DbCategory.getArrayData();
                    dataAdapter = new ArrayAdapter<String>(this, R.layout.item_category, category);
                    categoryLV.setAdapter(dataAdapter);
                    categoryDialog.dismiss();
                    num++;
                }


            }
        }
        else Message.message(getApplicationContext() , "הגעת למגבלה של שישה קטיגוריות");




    } // הוספת הקטיגוריות





















    public void createAddCategoryDialog() //פונקציית פתיחת הדיאלוג של ההוספה
    {
        categoryDialog = new Dialog(this);
        categoryDialog.setContentView(R.layout.add_category_dialog);
        categoryDialog.setTitle("הוספת קטיגוריה");
        categoryDialog.setCancelable(true);
        writeCategory =(EditText)categoryDialog.findViewById(R.id.writeCategory);
        categoryDialog.show();



    }

    public void createPressCategoryDialog() //פונקציית פתיחת הדיאלוג של הלחיצה הארוכה
    {
        PressCategoryDialog = new Dialog(this);
        PressCategoryDialog.setContentView(R.layout.press_category);
        PressCategoryDialog.setTitle("מה תרצה לעשות");
        PressCategoryDialog.setCancelable(true);
        PressCategoryDialog.show();
    }





    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ActionCategory = parent.getItemAtPosition(position).toString();
        createPressCategoryDialog();
        return false;
    }







    public void createUpdateCategoryDialog() //פונקציית פתיחת הדיאלוג של העדכון
    {
        updateCategory = new Dialog(this);
        updateCategory.setContentView(R.layout.update_category);
        updateCategory.setTitle("עדכון קטיגוריה");
        updateCategory.setCancelable(true);
        updateNew =(EditText)updateCategory.findViewById(R.id.etNewCattegory);
        updateCategory.show();
    }
    public void update(View view){
        String u2 = updateNew.getText().toString();
        if(ActionCategory.isEmpty() || u2.isEmpty())
        {
            Message.message(getApplicationContext(),"הכנס נתונים");
        }
        else
        {
            int a= DbCategory.updateName( ActionCategory, u2);
            if(a<=0)
            {
                Message.message(getApplicationContext(),"העדכון לא צלח");
                updateCurrent.setText("");
                updateNew.setText("");
            } else {
                Message.message(getApplicationContext(),"עודכן בהצלחה");
                updateCategory.dismiss();
                PressCategoryDialog.dismiss();
                category = DbCategory.getArrayData();
                dataAdapter = new ArrayAdapter<String>(this, R.layout.item_category, category);
                categoryLV.setAdapter(dataAdapter);

            }
        }

    } // ונקציית העידכון
    public void saveEditCategory(View view){
        createUpdateCategoryDialog();
    } // פתיחת עצמה של דיאלוג העדכון





    public void removeCategory(View view){
        int a = DbCategory.delete(ActionCategory);

        if (a <= 0) {
            Message.message(getApplicationContext(), "המחיקה נכשלה");

        } else {
            Message.message(this, "נמחק בהצלחה");
            category = DbCategory.getArrayData();
            dataAdapter = new ArrayAdapter<String>(this, R.layout.item_category, category);
            categoryLV.setAdapter(dataAdapter);
            PressCategoryDialog.dismiss();
        }


    } // הסרת קטיגוריה




















}