package com.example.trytothemain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText jj;
    TextView allExpenses;
    All_Data expenses = new All_Data(this);
    Dialog loan;
    TextView helloMonth;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloMonth = (TextView) findViewById(R.id.month);
        helloMonth.setText( CurrentDate.currentDate() + "\n" + "הוצאת החודש - ");


    }


    public void onClick(View view) {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        allExpenses = (TextView) findViewById(R.id.sumOfExpenses);
        allExpenses.setText(expenses.getSumExpenses());
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
            //Intent intent=new Intent(this, editLoansActivity.class);
            //startActivity(intent);
            createLoanDialog();

            return true;
        }

        else if (id == R.id.disconnectd) { //בוצע פונקציית סגירת האפליקציה
            Message.message(getApplicationContext() ,"disconnectd" );

            finish();
            //Intent intent=new Intent(this,categoryActivity.class);
            //startActivity(intent);
            return true;
        }
        return true;
    } // בחירת האופציות התפריט למעלה





    public void createLoanDialog()
    {
        loan = new Dialog(this);
        loan.setContentView(R.layout.press_loans);
        loan.setTitle("מה תרצה לעשות");
        loan.setCancelable(true);
        loan.show();
    }

    public void newLoans(View view){
        loan.dismiss();
        Intent intent=new Intent(this, editLoansActivity.class);
        startActivity(intent);
    }
    public void infoLoans(View view){
        loan.dismiss();
        Intent intent=new Intent(this, DetailsLoans.class);
        startActivity(intent);
    }

}


