package com.example.daycareapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.daycareapp.R;

public class ConfirmPaymentActivity extends AppCompatActivity {

    Button returnbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);

        returnbt = findViewById(R.id.returnbtid);

        returnbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProtectedActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}