package com.dits.dailyreport;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=(Button)findViewById(R.id.btnreport);
        ctx = this;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,GetAddressMapActivity.class);
                startActivity(i);
            }
        });
        Button b2 = (Button)findViewById(R.id.custmordetails);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(MainActivity.this,CustomerReportView.class);
                startActivity(ii);
            }
        });
        Button b3 = (Button)findViewById(R.id.dailyexpensive);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iii = new Intent(MainActivity.this,DailyReportsView.class);
                startActivity(iii);
            }
        });
        Button b4 = (Button)findViewById(R.id.Savedlocation);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iiii = new Intent(MainActivity.this,GetAddressMapActivityBean.class);
                startActivity(iiii);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Select");
                String[] list = {"Daily Expense", "Customer report"};
                builder.setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent i2 = new Intent(MainActivity.this,Expense.class);
                                startActivity(i2);
                                break;
                            case 1:
                                Intent i3= new Intent(MainActivity.this,CustomerReport.class);
                                startActivity(i3);
                                break;
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
