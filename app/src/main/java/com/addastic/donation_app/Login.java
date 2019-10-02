package com.addastic.donation_app;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.addastic.donation_app.Common.Common;
import com.addastic.donation_app.Model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
public class Login extends AppCompatActivity {


    EditText edtPhone,edtPassword;
    Button btnSinIn;
    DatabaseReference table_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPhone=(EditText)findViewById(R.id.edtMobile);
        edtPassword=(EditText)findViewById(R.id.edtPass);
        btnSinIn=(Button)findViewById(R.id.btnSubmit);



        FirebaseApp.initializeApp(this);

        table_user = FirebaseDatabase.getInstance().getReference().child("user");

        btnSinIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog= new ProgressDialog(Login.this);
                mDialog.setMessage("Hold On...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString()))
                            {
                                Intent homeIntent = new Intent(Login.this,MainActivity.class);
                                Common.currentUsder = user;
                                startActivity(homeIntent);
                                finish();

                            } else {
                                Toast.makeText(Login.this, "Ohh Sorry Sign in Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(Login.this,"Wrong Information!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
