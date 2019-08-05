package com.dits.dailyreport;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CustomerReport extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    RadioButton r1,r2;
    RadioGroup rg;
    Button btnc;
  DatabaseReference ref;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_report);
        ref= FirebaseDatabase.getInstance().getReference();

        btnc=(Button)findViewById(R.id.Customerreport);
        e1=(EditText)findViewById(R.id.ed1);
        e2=(EditText)findViewById(R.id.ed2);
        e3=(EditText)findViewById(R.id.ed3);
        e4=(EditText)findViewById(R.id.ed4);
        e5=(EditText)findViewById(R.id.ed5);
       r1=(RadioButton)findViewById(R.id.radioButton1);
        r2=(RadioButton)findViewById(R.id.radioButton2);
        rg  = (RadioGroup)findViewById(R.id.rg);
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(e1.getText().toString())){
                    e1.setError("Enter shop name");
                    e1.requestFocus();

                }
              else  if(TextUtils.isEmpty(e2.getText().toString())) {
                    e2.setError("Enter shop address");
                    e2.requestFocus();
                }
                else  if(TextUtils.isEmpty(e3.getText().toString())){
                    e3.setError("Enter conversation details");
                    e3.requestFocus();
                }
                else  if(TextUtils.isEmpty(e5.getText().toString())){
                    e5.setError("Enter contact details");
                    e5.requestFocus();
                }
                else {
rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.radioButton1:
ref.push();
                break;
            case R.id.radioButton2:
ref.push();
                break;

        }

    }

});

                    DateFormat dateFormat = new SimpleDateFormat("EEE,MMM d,''yy");
                    final String date = dateFormat.format(Calendar.getInstance().getTime());
                    DateFormat timeFormat = new SimpleDateFormat("h:mm a");
                    final String time = timeFormat.format(Calendar.getInstance().getTime());
                    Log.v("---->",date+"/"+time);
                    HashMap hm1=new HashMap();
                    hm1.put("shopname",e1.getText().toString());
                    hm1.put("address",e2.getText().toString());
                    hm1.put("conversationdetails",e3.getText().toString());
                    hm1.put("personname",e4.getText().toString());
                    hm1.put("contactdetails",e5.getText().toString());
                    hm1.put("Date",date);
                    hm1.put("Time",time);
                    key=ref.child("customer").push().getKey();
                    ref.child("customer").child(key).updateChildren(hm1);
                    Intent i = new Intent(CustomerReport.this,CustomerReportView.class);


                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    e5.setText("");

                }
            }

        });
    }
}
