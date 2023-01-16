package com.example.daycareapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daycareapp.AuthToken;
import com.example.daycareapp.R;
import com.example.daycareapp.RetrofitClient;
import com.example.daycareapp.User;
import com.example.daycareapp.util.SharedRefs;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountVerifyActivity extends AppCompatActivity {

    Button verifyButton;
    EditText verificationTextEt;
    SharedRefs sharedRefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verify);

        verifyButton = findViewById(R.id.verifyBt);
        verificationTextEt = findViewById(R.id.verificationCodeEtId);

        sharedRefs = new SharedRefs(getApplicationContext());

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verificationTextEt.getText()!=null){
                    String pin = verificationTextEt.getText().toString();
                    verifyAccount(pin);
                }else{
                    verificationTextEt.setError("Enter verification pin");
                    verificationTextEt.requestFocus();
                }
            }
        });
    }

    private void verifyAccount(String pin) {

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .veryfyPin(pin);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("hello", call.request().toString());
                if (response.isSuccessful() && response.code() == 200) {
                    System.out.println(response.message());
//                    sharedRefs.putString(SharedRefs.IS_VERIFIED, "true");

                    Intent intent = new Intent();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials! Try again!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("error socket", t.getMessage());
            }
        });

    }
}