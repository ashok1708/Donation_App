package com.addastic.donation_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentSuccess extends AppCompatActivity {

    DatabaseReference table_user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        String transactionId= getIntent().getStringExtra("transactionId");
        String transactionStatus= getIntent().getStringExtra("status");
        String name=getIntent().getStringExtra("name");
        final String mobileNumbe=getIntent().getStringExtra("mobileNumber");

        FirebaseApp.initializeApp(this);

        table_user = FirebaseDatabase.getInstance().getReference().child("user");

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dataSnapshot.child(mobileNumbe);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
