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
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.addastic.donation_app.Model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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

        edtName=(EditText)findViewById(R.id.editTextName);
        edtPhone=(EditText)findViewById(R.id.editTextPhone);
        edtMail=(EditText)findViewById(R.id.editTextMail);
        btnSubmit=(Button)findViewById(R.id.cirLoginButton);

        textView= findViewById(R.id.tvSkip);
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

                if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(phone)) {
                    edtName.setError("Fill the Name.");

                    edtPhone.setError("Fill the Mobile Number.");
                    mDialog.dismiss();
                }
                else{
                    if(TextUtils.isEmpty(name)){
                        edtName.setError("Fill the Name.");
                        mDialog.dismiss();
                    }
                    if (TextUtils.isEmpty(phone)){
                        edtPhone.setError("Fill the Mobile Number.");
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
                            Intent intent= new Intent(Login.this,MainActivity.class);
                            intent.putExtra("mobileNumber",edtPhone.getText().toString());
                            intent.putExtra("name",edtName.getText().toString());
                            startActivity(intent);

                        }
                        else{
                            mDialog.dismiss();
                            String msg="Welcome "+name.toUpperCase()+" ...";
                            User user=new User(edtName.getText().toString(),edtMail.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(Login.this,MainActivity.class);
                            intent.putExtra("mobileNumber",edtPhone.getText().toString());
                            intent.putExtra("name",edtName.getText().toString());
                            startActivity(intent);

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