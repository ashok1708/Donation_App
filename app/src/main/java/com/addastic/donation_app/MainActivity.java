package com.addastic.donation_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.PaymentApp;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PaymentStatusListener {



    Button btnPaytm;
    EditText edtNote,edtAmount;
    String TAG ="main";
    final int UPI_PAYMENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPaytm = findViewById(R.id.btnPay);
        edtNote = findViewById(R.id.editTextNote);
        edtAmount = findViewById(R.id.editTextAmount);

        btnPaytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay(edtAmount.getText().toString(),edtNote.getText().toString());
            }
        });
    }

    void pay(String amount,String note)
    {
        EasyUpiPayment mEasyUpiPayment = new EasyUpiPayment.Builder()
                .with(this)
                .setPayeeVpa("naren4933@okicic")
                .setPayeeName("Narendra Chouhan")
                .setTransactionId("214545")
                .setTransactionRefId("0212545")
                .setDescription(note)
                .setAmount(amount+".00")
                .build();

        // Register Listener for Events
        mEasyUpiPayment.setPaymentStatusListener(this);



        mEasyUpiPayment.startPayment();



    }

    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        // Transaction Completed
        Log.d("TransactionDetails", transactionDetails.toString());
        //statusView.setText(transactionDetails.toString());
    }

    @Override
    public void onTransactionSuccess() {
        // Payment Success
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
       // imageView.setImageResource(R.drawable.ic_success);
        Intent intent= new Intent(MainActivity.this,PaymentSuccess.class);
        startActivity(intent);
    }

    @Override
    public void onTransactionSubmitted() {
        // Payment Pending
        Toast.makeText(this, "Pending | Submitted", Toast.LENGTH_SHORT).show();
        //imageView.setImageResource(R.drawable.ic_success);
    }

    @Override
    public void onTransactionFailed() {
        // Payment Failed
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        //imageView.setImageResource(R.drawable.ic_failed);
    }

    @Override
    public void onTransactionCancelled() {
        // Payment Cancelled by User
        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        //imageView.setImageResource(R.drawable.ic_failed);
    }

    @Override
    public void onAppNotFound() {
        Toast.makeText(this, "App Not Found", Toast.LENGTH_SHORT).show();
    }


}
