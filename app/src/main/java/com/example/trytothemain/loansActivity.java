package com.example.trytothemain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class loansActivity extends AppCompatActivity {
    private static final int REQUEST_CONTACTS_PERMISSION = 1;

    Button callNumber;
    TextView nameContacts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loans);
        callNumber = (Button) findViewById(R.id.callNumber);
        nameContacts = (TextView) findViewById(R.id.nameContacts);


    }




    public void getContacts(View v){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // בקשת הרשאה לאנשי קשר
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission not granted, request it
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        REQUEST_CONTACTS_PERMISSION);
            } else {
                // Permission already granted, do your contacts-related work here
                Toast.makeText(this, "Contacts permission granted!", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Permission not needed for lower SDK versions, do your contacts-related work here
            Toast.makeText(this, "Contacts permission granted!", Toast.LENGTH_SHORT).show();
        }

        showContactsPicker();
        callNumber.setVisibility(View.VISIBLE);
        nameContacts.setVisibility(View.VISIBLE);



    }







    public void openDial(View v){
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        String contactsDial = callNumber.getText().toString();
        String phoneNumber = "tel:" + contactsDial;
        dialIntent.setData(Uri.parse(phoneNumber));
        startActivity(dialIntent);
    }























    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, // הפעולה של בקשת ההרשאה
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACTS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, do your contacts-related work here
                Toast.makeText(this, "Contacts permission granted!", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied, handle it accordingly
                Toast.makeText(this, "Contacts permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }






    public void showContactsPicker(){
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,0);
    }

    protected void onActivityResult(int requestCode , int resultCode , Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if(requestCode == 0) {
                Cursor cursor;
                CursorLoader cursorLoader = new CursorLoader(this, data.getData(), null, null, null, null);
                cursor = cursorLoader.loadInBackground();
                cursor.moveToFirst();


                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                nameContacts.setText(name);

                if (hasPhone.equalsIgnoreCase("1")) { //בדיקה אם יש לאיש הקשר מספר טלפון
                    String phoneNum = " ";
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            , null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);

                    if (phones.moveToNext()) {
                        phoneNum = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    phones.close();
                    cursor.close();
                    callNumber.setText(phoneNum);
                    Toast.makeText(this, "יש מספר טלפון", Toast.LENGTH_SHORT).show();
                }


                else callNumber.setText("לא קיים מספר טלפון");



            }
        }



    }



































}