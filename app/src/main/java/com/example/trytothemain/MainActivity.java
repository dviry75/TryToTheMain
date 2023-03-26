package com.example.trytothemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void onClick(View view) {

    }



    public void allDetails(View v){
        Intent intent=new Intent(this,DetailsMoney.class);
        startActivity(intent);

    }




    public void yourExpense(View v){
        Intent intent=new Intent(this,YourExpense.class);
        startActivity(intent);
    }
























    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return  true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.category) {
            Toast.makeText(this,"you selected category", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,categoryActivity.class);
            startActivity(intent);

            return true;
        }
        else if (id == R.id.equal_month) {
            Toast.makeText(this,"you selected month", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,compareMonth.class);
            startActivity(intent);

            return true;
        }
        else if (id == R.id.calendar_equale) {
            Toast.makeText(this,"you selected calendar", Toast.LENGTH_SHORT).show(); //לבדוק איך עושים לוח שנה שממנו אפשר להגיע בלחיצת כפתור לפירוט ההוצאות באותו היום
            //Intent intent=new Intent(this,categoryActivity.class);
            //startActivity(intent);

            return true;
        }
        else if (id == R.id.loans) {
            Toast.makeText(this,"you selected loans", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,loansActivity.class);
            startActivity(intent);

            return true;
        }
        else if (id == R.id.disconnectd) { //לבדוק את פונקציית היציאה מהאפליקציה
            Toast.makeText(this,"disconnectd", Toast.LENGTH_SHORT).show();
            //Intent intent=new Intent(this,categoryActivity.class);
            //startActivity(intent);

            return true;
        }

        else if (id == R.id.view_card) { // לבדוק בפעם הראשונה הגדרת כרטיס.
            Toast.makeText(this,"you selected your card", Toast.LENGTH_SHORT).show();// לבדוק אם אפשר לשים שם עוד סיסמא
            Intent intent=new Intent(this,photoOfCradit.class);
            startActivity(intent);

            return true;
        }

        return true;
    }



}


