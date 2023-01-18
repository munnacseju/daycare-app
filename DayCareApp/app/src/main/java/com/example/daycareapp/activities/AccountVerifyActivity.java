package com.example.daycareapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daycareapp.R;
import com.example.daycareapp.network.RetrofitAPIClient;
import com.example.daycareapp.models.User;
import com.example.daycareapp.network.response.AccountVerifyResponse;
import com.example.daycareapp.util.SharedRefs;

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
                if(verificationTextEt.getText()==null || verificationTextEt.getText().toString().equals("")){
                    verificationTextEt.requestFocus();
                }else{
                    String pin = verificationTextEt.getText().toString();
                    verifyAccount(pin);
                }
            }
        });
    }

    private void verifyAccount(String pin) {

        Call<AccountVerifyResponse> call = RetrofitAPIClient
                .getInstance()
                .getAPI()
                .veryfyPin(pin);

        call.enqueue(new Callback<AccountVerifyResponse>() {
            @Override
            public void onResponse(Call<AccountVerifyResponse> call, Response<AccountVerifyResponse> response) {
                Log.d("hello", call.request().toString());
                if (response.isSuccessful() && response.code() == 200) {
                    System.out.println(response.message());
                    AccountVerifyResponse accountVerifyResponse = response.body();
                    User user = accountVerifyResponse.getData();
                    if(accountVerifyResponse.getStatus().equals("verified")){
                        sharedRefs.putString(SharedRefs.IS_VERIFIED, user.isVerified()+"");
                        Intent intent = new Intent(getApplicationContext(), ProtectedActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(AccountVerifyActivity.this, "Wrong pin", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Getting issue!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AccountVerifyResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("error socket", t.getMessage());
            }
        });

    }
}