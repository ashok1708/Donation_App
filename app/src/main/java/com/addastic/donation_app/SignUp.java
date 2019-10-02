package com.addastic.donation_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.addastic.donation_app.Model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText edtName,edtMobile,edtPass;
    Button btnSubmit;
    DatabaseReference table_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edtName= (EditText)findViewById(R.id.edtName);
        edtPass=(EditText)findViewById(R.id.edtPass);
        edtMobile=(EditText)findViewById(R.id.edtMobile);

        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        FirebaseApp.initializeApp(this);

       table_user = FirebaseDatabase.getInstance().getReference().child("user");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog= new ProgressDialog(SignUp.this);
                mDialog.setMessage("Hold On...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(edtName.getText().toString().isEmpty()&&edtMobile.getText().toString().isEmpty()&&edtPass.getText().toString().isEmpty())
                        {
                            Toast.makeText(SignUp.this, "Please Enter the required info...", Toast.LENGTH_SHORT).show();
                        }

                        if(edtName.getText().toString().isEmpty()){
                            Toast.makeText(SignUp.this, "Please Enter Name...", Toast.LENGTH_SHORT).show();
                        }
                        if(edtMobile.getText().toString().isEmpty())
                        {
                            Toast.makeText(SignUp.this, "Please Enter Mobile Number...", Toast.LENGTH_SHORT).show();
                        }
                        if(edtPass.getText().toString().isEmpty()){
                            Toast.makeText(SignUp.this, "Please choose a Password...", Toast.LENGTH_SHORT).show();
                        }
                        if(dataSnapshot.child(edtMobile.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this,"Phone Number already register...",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mDialog.dismiss();
                            User user = new User(edtName.getText().toString(),edtPass.getText().toString());
                            table_user.child(edtMobile.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"Sign Up Successfully!",Toast.LENGTH_SHORT).show();
                            finish();

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





