package com.example.trytothemain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class YourExpense extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    All_Data expenses = new All_Data(this);
    CategoryHelper allCategory = new CategoryHelper(this);
    Spinner choose_category;
    ArrayList<String> category;
    Button takeBill , save;
    ImageView bill;
    Bitmap bitBill;
    Switch saveLocation;
    EditText etDescription , etCost;
    String saveSlocation;
    byte[] imageBytes;

    String nameOfCategory;
    ArrayAdapter<String> dataAdapter ;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private double currentLatitude ;
    private double currentLongitude ;






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_expense);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        }


        saveLocation = (Switch)findViewById(R.id.save_location);
        saveLocation.setOnCheckedChangeListener(this);





        etDescription = (EditText)findViewById(R.id.et_description);
        etCost = (EditText)findViewById(R.id.et_cost);

        takeBill = (Button) findViewById(R.id.take_invoicing);
        bill = (ImageView) findViewById(R.id.invoicing);
        takeBill.setOnClickListener(YourExpense.this);
        save = (Button) findViewById(R.id.saveBill);

        //עשיית הקטיוגריות באובייקט ספינר
        choose_category = (Spinner) findViewById(R.id.open_category);
        category = allCategory.getArrayCategory();


        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, category);
        choose_category.setAdapter(dataAdapter);
        choose_category.setOnItemSelectedListener(this);












    }//sddsdjgfhgktejlhafeiufWAFGWYFWTwfgeliyfgtfweyfeyeuewiuyo

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //getCurrentLocation();
            } else {
                // Permission denied
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getCurrentLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permissions not granted
            // Request the necessary permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_REQUEST_CODE);
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {

                    public void onSuccess(Location location) {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            // Save location coordinates
                            currentLatitude = latitude;
                            currentLongitude = longitude;
                            saveNewLocation();

                        } else {
                            Message.message(getApplicationContext() , "שמירת המיקום נכשלה");
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Message.message(getApplicationContext() , "שמירת המיקום נכשלה");
                    }
                });
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
                // Convert the Bitmap to a byte array
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitBill.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imageBytes = stream.toByteArray();

                // Save the image and other relevant information into the database

            }
        }


    }

















    //הפונקצייה של האובייקט ספינר
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nameOfCategory = parent.getItemAtPosition(position).toString();    //בדיקה איך קוראים לפריט

    }
    public void onNothingSelected(AdapterView<?> parent) {
    }



    public void saveExpense(View view){// t1 וt2 הם התיאור והקטיגוריה שכרגע אני רוצה להכניס לדאטה בייס
        if(etCost.getText().toString().isEmpty())
        {
            Message.message(getApplicationContext(),"לא הכנסת את כל הפרטים");
        }
        else {
            int yourCost = Integer.valueOf(etCost.getText().toString());
            String totalDescribation = etDescription.getText().toString();
            if(totalDescribation.isEmpty() || nameOfCategory ==null || etCost.getText().toString().isEmpty()  )
            {
                Message.message(getApplicationContext(),"לא הכנסת את כל הפרטים");
            }
            else if(saveSlocation == null && imageBytes == null){
                long id = expenses.insertDataJustInfo(yourCost ,nameOfCategory, totalDescribation);
                if(id<=0)
                {
                    Message.message(getApplicationContext(),"השמירה נכשלה");

                }
                else
                {
                    Message.message(getApplicationContext(),"נשמר בהצלחה \n ללא מיקום וקבלה");
                    finish();
                }



            }
            else if(saveSlocation == null && imageBytes != null){
                long id = expenses.insertDataWithoutLocation(yourCost ,nameOfCategory, totalDescribation , imageBytes);
                if(id<=0)
                {
                    Message.message(getApplicationContext(),"השמירה נכשלה");

                }
                else
                {
                    Message.message(getApplicationContext(),"נשמר בהצלחה \n ללא מיקום ");
                    finish();
                }




            }
            else if(imageBytes == null && saveSlocation != null){
                long id = expenses.insertDataWithoutBill(yourCost ,nameOfCategory, totalDescribation , saveSlocation);
                if(id<=0)
                {
                    Message.message(getApplicationContext(),"השמירה נכשלה");

                }
                else
                {
                    Message.message(getApplicationContext(),"נשמר בהצלחה \n ללא קבלה");
                    finish();
                }



            }




            else {

                //String yourDate = CurrentDate.currentDate();
                long id = expenses.insert(yourCost ,nameOfCategory, totalDescribation , saveSlocation , imageBytes);
                if(id<=0)
                {
                    Message.message(getApplicationContext(),"השמירה נכשלה");

                }
                else
                {
                    Message.message(getApplicationContext(),"נשמר בהצלחה");
                    finish();
                }
            }
        }







    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean b) { // פעולת השמירת מיקום
        if(b){
            getCurrentLocation();
        }
        else saveSlocation = null;

    }

    public void saveNewLocation(){
        saveSlocation = String.valueOf(currentLatitude) + "," + String.valueOf(currentLongitude);
    }








}