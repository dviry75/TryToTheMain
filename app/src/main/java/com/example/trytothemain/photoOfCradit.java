package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class photoOfCradit extends AppCompatActivity implements View.OnClickListener{
    Button saveOpen , takeCredit , saveNewCreditDialog;
    ImageView newCredit , loadCredit;
    Dialog carditPhotoDialog;
    Bitmap credit_photo;

    EditText yourCode;
    TextView tvCredit;
    String showNew = "בשביל לצלם תמונה חדשה הכנס כאן קוד שיאשר שהכרטיס אכן שייך לך";
    String showopen = "בשביל להגיע לתמונה של הכרטיס שלך כתוב א הקוד שרשמת בזמן שצילמת אותו";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_of_cradit);
        saveOpen = (Button) findViewById(R.id.saveOrOpen);
        yourCode = (EditText) findViewById(R.id.codeCredit);
        tvCredit = (TextView) findViewById(R.id.tvCredit);


    }















    public void newCredit(View view){ //הוספת כרטיס חדש


        tvCredit.setText(showNew);
        tvCredit.setVisibility(View.VISIBLE);
        yourCode.setVisibility(View.VISIBLE);
        saveOpen.setVisibility(View.VISIBLE);



    }



    public void yourCredit(View view){ // להסתכל בכרטיס הישן
        tvCredit.setText(showopen);
        tvCredit.setVisibility(View.VISIBLE);
        yourCode.setVisibility(View.VISIBLE);
        saveOpen.setVisibility(View.VISIBLE);

    }



    public void onClick(View view) {

    }





    public void open(View view){ //  פתיחה
        createLoginDialog();

    }
    public void createLoginDialog() //פונקציית פתיחת הדיאלוג
    {
        carditPhotoDialog = new Dialog(this);
        carditPhotoDialog.setContentView(R.layout.photo_credit_dialog);
        carditPhotoDialog.setTitle("Login");
        carditPhotoDialog.setCancelable(false);
        takeCredit=(Button)carditPhotoDialog.findViewById(R.id.takeCredit);
        saveNewCreditDialog=(Button)carditPhotoDialog.findViewById(R.id.saveNewCredit);
        newCredit=(ImageView) carditPhotoDialog.findViewById(R.id.thePhotoNew);

        carditPhotoDialog.show();

    }
    public void saveNewCredit(View view) { // פונקציית האונקליק
        if (view == takeCredit) {
            Toast.makeText(this, "Work", Toast.LENGTH_SHORT).show();//צילום כרטיס האשראי
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        }
        else if(saveNewCreditDialog == view){ // סגירת הדיאלוג
            carditPhotoDialog.dismiss();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // פונקציית הצילום
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)//coming from camera
        {
            if (resultCode == RESULT_OK) {
                credit_photo= (Bitmap)data.getExtras().get("data");
                newCredit.setImageBitmap(credit_photo);
            }
        }
    }
































}