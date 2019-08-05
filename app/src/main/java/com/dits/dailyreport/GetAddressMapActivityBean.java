package com.dits.dailyreport;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class GetAddressMapActivityBean extends AppCompatActivity {

    DatabaseReference databaseReference;

    ProgressDialog progressDialog;


    List<GetAddressMapDetails> list = new ArrayList<>();

    RecyclerView recyclerView;

    RecyclerView.Adapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address_map_bean);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(GetAddressMapActivityBean.this));

        progressDialog = new ProgressDialog(GetAddressMapActivityBean.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("MapAddress");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                DateFormat dateFormat = new SimpleDateFormat("EEE,MMM D,yy");
                final String date = dateFormat.format(Calendar.getInstance().getTime());
                DateFormat timeFormat = new SimpleDateFormat("h:mm a");
                final String time = timeFormat.format(Calendar.getInstance().getTime());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    GetAddressMapDetails GAMD = dataSnapshot.getValue(GetAddressMapDetails.class);

                    list.add(GAMD);
                }

                adapter = new GetAddressMapActivityAdapter(GetAddressMapActivityBean.this,list);;

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