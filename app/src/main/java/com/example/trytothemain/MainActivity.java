package com.example.trytothemain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText jj;
//5555


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    public void onClick(View view) {

    }









    public void allDetails(View v){
        Intent intent = new Intent(this,DetailsMoney.class);
        startActivity(intent);
    } // פתיחת מסך פירוט
    public void yourExpense(View v){
        Intent intent=new Intent(this,YourExpense.class);
        startActivity(intent);
    } // פתיחת מסך הזנת הוצאה

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return  true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.category) {
            Intent intent=new Intent(this,categoryActivity.class);
            startActivity(intent);

            return true;
        }
        else if (id == R.id.loans) {
            Intent intent=new Intent(this,loansActivity.class);
            startActivity(intent);

            return true;
        }

        else if (id == R.id.disconnectd) { //בוצע פונקציית סגירת האפליקציה
            Message.message(getApplicationContext() ,"disconnectd" );

            finish();
            //Intent intent=new Intent(this,categoryActivity.class);
            //startActivity(intent);
            return true;
        }

        else if (id == R.id.view_card) { // לבדוק בפעם הראשונה הגדרת כרטיס.
            Intent intent=new Intent(this,photoOfCradit.class);
            startActivity(intent);

            return true;
        }

        return true;
    } // בחירת האופציות התפריט למעלה

}


