package com.dits.dailyreport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DailyReportsView  extends AppCompatActivity {

        DatabaseReference databaseReference;

        ProgressDialog progressDialog;

        List<DailyReportDetails> list = new ArrayList<>();

        RecyclerView recyclerView;

        RecyclerView.Adapter adapter ;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reports_view);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(DailyReportsView.this));

        progressDialog = new ProgressDialog(DailyReportsView.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Expense");

        databaseReference.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot snapshot) {
        DateFormat dateFormat = new SimpleDateFormat("EEE,MMM D,yy");
        final String date = dateFormat.format(Calendar.getInstance().getTime());
        DateFormat timeFormat = new SimpleDateFormat("h:mm a");
        final String time = timeFormat.format(Calendar.getInstance().getTime());
        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

        DailyReportDetails DRD = dataSnapshot.getValue(DailyReportDetails.class);

        list.add(DRD);
        }

        adapter = new RecyclerviewAdapter(DailyReportsView.this, list);

        recyclerView.setAdapter(adapter);

        progressDialog.dismiss();
        }

@Override
public void onCancelled(DatabaseError databaseError) {

        progressDialog.dismiss();

        }
        });

        }
        }