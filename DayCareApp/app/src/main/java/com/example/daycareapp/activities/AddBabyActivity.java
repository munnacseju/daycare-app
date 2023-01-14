package com.example.daycareapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daycareapp.R;
import com.example.daycareapp.RetrofitClient;
import com.example.daycareapp.models.Baby;
import com.example.daycareapp.network.response.AddBabyResponse;
import com.example.daycareapp.network.response.AllBabyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBabyActivity extends AppCompatActivity {
    EditText babyNameEditText, babyAgeEditText, motherNameEditText, fatherNameEditText, contactNumberEditText, addressEditText, fevoriteFoodEditText;
    Button addBaby;
    Long caregiverId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby);

        caregiverId = this.getIntent().getLongExtra("caregiver_id", 1);


        babyNameEditText = findViewById(R.id.editTextBabyName);
        babyAgeEditText = findViewById(R.id.editTextAge);
        motherNameEditText = findViewById(R.id.editTextMotherName);
        fatherNameEditText = findViewById(R.id.editTextFatherName);
        contactNumberEditText = findViewById(R.id.editTextMobile);
        addressEditText = findViewById(R.id.editTextAddress);
        fevoriteFoodEditText = findViewById(R.id.editTextfevoriteFood);

        addBaby = findViewById(R.id.circularRegisterButton);
        addBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Baby baby = new Baby();
                if(babyNameEditText.getText()!=null ){
                    baby.setBabyName(babyNameEditText.getText().toString());
                }else{
                    babyNameEditText.setError("Enter Name first!");
                    babyNameEditText.requestFocus();
                }
                if(babyAgeEditText.getText()!=null){
                    int babyAge = Integer.parseInt(babyAgeEditText.getText().toString());
                    if(babyAge>=1 && babyAge<=5){
                        baby.setBabyAge(babyAge);
                    }else {
                        babyAgeEditText.setError("Baby should be 1-5 years");
                        babyAgeEditText.requestFocus();
                    }
                }else{
                    babyAgeEditText.setError("Baby should not empty");
                    babyAgeEditText.requestFocus();
                }
                baby.setMotherName(motherNameEditText.getText().toString());
                baby.setFatherName(fatherNameEditText.getText().toString());
                baby.setContactNumber(contactNumberEditText.getText().toString());
                baby.setAddress(addressEditText.getText().toString());
                baby.setFevoriteFood(fevoriteFoodEditText.getText().toString());
                addBaby(baby);
            }
        });
    }

    private void addBaby(Baby baby) {
//        progressBar.setVisibility(View.VISIBLE);
        Call<AddBabyResponse> call = RetrofitClient
                .getInstance()
                .getAPI()
                .addBaby(baby);

        call.enqueue(new Callback<AddBabyResponse>() {
            @Override
            public void onResponse(Call<AddBabyResponse> call, Response<AddBabyResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    String message = response.body().getStatus();
                    Toast.makeText(getApplicationContext(), "Successfully added baby! status: "+message, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), DateActivity.class);
                    Long babyId = response.body().getBaby().getId();
                    intent.putExtra("caregiver_id", caregiverId);
                    intent.putExtra("baby_id", babyId);
                    startActivity(intent);

                } else {
//                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Some unknown problem occurred!! "+response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddBabyResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}