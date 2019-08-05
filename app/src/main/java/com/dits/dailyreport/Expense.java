package com.dits.dailyreport;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Expense extends AppCompatActivity {
     EditText ex1,ex2,ex3,ex4;
    Button btnexpense;
    DatabaseReference refexp;
    String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        refexp= FirebaseDatabase.getInstance().getReference();
        btnexpense=(Button)findViewById(R.id.Expense);
        ex1=(EditText)findViewById(R.id.exp1);
        ex2=(EditText)findViewById(R.id.exp2);
        ex3=(EditText)findViewById(R.id.exp3);
        ex4=(EditText)findViewById(R.id.exp4);
        btnexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(ex1.getText().toString())){
                    ex1.setError("enter lunch expense");
                    ex1.requestFocus();

                }
                else  if(TextUtils.isEmpty(ex2.getText().toString())) {
                    ex2.setError("enter dinner expense ");
                    ex2.requestFocus();
                }
                else  if(TextUtils.isEmpty(ex3.getText().toString())){
                    ex3.setError("enter travelling expense");
                    ex3.requestFocus();
                }
                else  if (TextUtils.isEmpty(ex4.getText().toString())){
                    ex4.setError("enter extra expense");
                    ex4.requestFocus();
                }
                else {
                    DateFormat dateFormat = new SimpleDateFormat("EEE,MMM D,yy");
                    final String date = dateFormat.format(Calendar.getInstance().getTime());
                    DateFormat timeFormat = new SimpleDateFormat("h:mm a");
                    final String time = timeFormat.format(Calendar.getInstance().getTime());

                    HashMap hm2=new HashMap();
                    hm2.put("lunch",ex1.getText().toString());
                    hm2.put("dinner",ex2.getText().toString());
                    hm2.put("travelling",ex3.getText().toString());
                    hm2.put("extraexpense",ex4.getText().toString());
                    target=refexp.child("Expense").push().getKey();
                    refexp.child("Expense").child(target).updateChildren(hm2);
//                Intent i = new Intent(Expense.this,DailyReportsView.class);
//                startActivity(i);
                    ex1.setText("");
                    ex2.setText("");
                    ex3.setText("");
                    ex4.setText("");


                }

            }

        });
    }


    }


