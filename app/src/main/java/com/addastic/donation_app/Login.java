package com.addastic.donation_app;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;


import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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


    EditText edtName,edtPhone,edtMail;
    Button btnSubmit;
    DatabaseReference table_user;

    TextView textView ;

    String name,phone,mail="None";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtName=(EditText)findViewById(R.id.edtName);
        edtPhone=(EditText)findViewById(R.id.edtMobile);
        edtMail=(EditText)findViewById(R.id.edtMail);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        textView= findViewById(R.id.btnSkip);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        FirebaseApp.initializeApp(this);

        table_user = FirebaseDatabase.getInstance().getReference().child("user");

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog= new ProgressDialog(Login.this);
                mDialog.setMessage("Hold On...");
                mDialog.show();



                name=edtName.getText().toString();
                phone=edtPhone.getText().toString();

                if(!(edtMail.getText().toString().isEmpty())) {
                    mail = edtMail.getText().toString();
                }

                if(name.isEmpty()&& phone.isEmpty()){
                    Toast.makeText(Login.this, "enter the required information...", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
                else{
                    if(name.isEmpty()){
                        Toast.makeText(Login.this, "enter the Name...", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                    if (phone.isEmpty()){
                        Toast.makeText(Login.this, "enter the Mobile Number...", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                }

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()){
                            mDialog.dismiss();
                            String msg="Welcome Back "+name.toUpperCase()+" ...";
                            Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();

                        }
                        else{
                            mDialog.dismiss();
                            String msg="Welcome "+name.toUpperCase()+" ...";
                            User user=new User(edtName.getText().toString(),edtMail.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }
}
