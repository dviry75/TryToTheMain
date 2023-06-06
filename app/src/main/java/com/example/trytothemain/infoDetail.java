package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class infoDetail extends AppCompatActivity {
    All_Data expenses = new All_Data(this);
    TextView category , sum , describtion , date;
    String description1;
    ImageView bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);
        category = (TextView) findViewById(R.id.infoCategory);
        sum = (TextView) findViewById(R.id.infoSum);
        bill = (ImageView) findViewById(R.id.image_bill);
        describtion = (TextView) findViewById(R.id.infoDesc);
        date= (TextView)findViewById(R.id.infoDate);
        Intent intent=getIntent();
        description1 = intent.getExtras().getString("desc");


        String date1 = expenses.getDateOnDesc(description1);
        int sumCost = expenses.getCostOnDesc(description1);
        String cate = expenses.getCateOnDesc(description1);

        sum.setText(  "סכום ההוצאה: " + String.valueOf(sumCost));
        describtion.setText("תיאור ההוצאה: " + description1);
        category.setText("קטיגוריה: " + cate);
        date.setText( "תאריך ההוצאה: " + date1);


        if(expenses.getBillOnDesc(description1) == null){
            Message.message(getApplicationContext() , "לא נשמרה קבלה");
        }
        else{
            byte[] imageBytes = expenses.getBillOnDesc(description1) .getImageBytes();
            if(imageBytes ==null){

            }
            else{
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                bill.setImageBitmap(bitmap);
            }

        }




    }





    public void openLocation(View view){
        if( expenses.getLocationOnDesc(description1) == null){
            Message.message(getApplicationContext() , "לא נשמר המיקום");
        }
        else {
            String location = expenses.getLocationOnDesc(description1);
            String [] arrayLocation = location.split(",");
            double latitude = Double.valueOf(arrayLocation[0]);
            double longitude = Double.valueOf(arrayLocation[1]);
            openLocationInMaps(latitude , longitude);

        }


    }

    public void removeExpense(View view){
        int a = expenses.deleteByDesc(description1);

        if (a <= 0) {
            Message.message(getApplicationContext(), "המחיקה נכשלה");
        } else {
            Message.message(this, "נמחק");
            finish();
        }
    }


    private void openLocationInMaps(double latitude, double longitude) {
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}