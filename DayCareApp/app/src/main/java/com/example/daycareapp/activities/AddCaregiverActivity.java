package com.example.daycareapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.daycareapp.constants.CaregiverConstant;
import com.example.daycareapp.R;
import com.example.daycareapp.models.Caregiver;
import com.example.daycareapp.network.RetrofitAPIClient;
import com.example.daycareapp.network.response.AddCaregiverResponse;
import com.example.daycareapp.util.EncodeDecodeUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCaregiverActivity extends AppCompatActivity {

    EditText caregiverMotherName, address, speciality, adminFeedBack;
    Button addCareGiver;
    static final int REQUEST_IMAGE_PICK = 1;
    private Button  selectImageBtn;
    private String imageBase64 = "";
    private ImageView caregiverImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_caregiver);

        caregiverMotherName = findViewById(R.id.caregiverMotherName);
        address = findViewById(R.id.address);
        speciality = findViewById(R.id.speciality);
        adminFeedBack = findViewById(R.id.adminFeedBack);
        caregiverImageView = findViewById(R.id.caregiverImageViewId);
        selectImageBtn = findViewById(R.id.caregiverImageChooserBtId);

        addCareGiver = findViewById(R.id.addCaregiver);

        addCareGiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isValid = checkValidity(caregiverMotherName, address, speciality, adminFeedBack);
                boolean isImageValid = true;
                if(imageBase64.equals("")){
                    isImageValid = false;
                    Toast.makeText(AddCaregiverActivity.this, "You have to select a caegiver image", Toast.LENGTH_SHORT).show();
                    selectImageBtn.setError("Select caregiver image");
                    selectImageBtn.requestFocus();
                }
                if(isValid & isImageValid){
                    Caregiver caregiver = new Caregiver();
                    caregiver.setCaregiverMotherName(caregiverMotherName.getText().toString());
                    caregiver.setAddress(address.getText().toString());
                    caregiver.setSpeciality(speciality.getText().toString());
                    caregiver.setAdminFeedBack(adminFeedBack.getText().toString());
                    caregiver.setImageBase64(imageBase64);
                    findViewById(R.id.progressBarId).setVisibility(View.VISIBLE);
                    addCaregiverCall(caregiver);
                }
            }
        });

        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageIntent();
            }
        });

    }

    private void addCaregiverCall(Caregiver caregiver) {
        Call<AddCaregiverResponse> call = RetrofitAPIClient
                .getInstance()
                .getAPI()
                .addCaregiver(caregiver);

        call.enqueue(new Callback<AddCaregiverResponse>() {
            @Override
            public void onResponse(Call<AddCaregiverResponse> call, Response<AddCaregiverResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    findViewById(R.id.progressBarId).setVisibility(View.GONE);
                    AddCaregiverResponse addCaregiverResponse = response.body();
                    Toast.makeText(getApplicationContext(), addCaregiverResponse.getMessage() + "Status: " + addCaregiverResponse.getStatus() , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), ProtectedActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    findViewById(R.id.progressBarId).setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Some unknown problem occurred!! "+response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddCaregiverResponse> call, Throwable t) {
                findViewById(R.id.progressBarId).setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean checkValidity(EditText caregiverMotherName, EditText address, EditText speciality, EditText adminFeedBack) {
        if(caregiverMotherName.getText().toString().equals("")){
            caregiverMotherName.setError("Enter First");
            caregiverMotherName.requestFocus();
            return false;
        }if(address.getText().toString().equals("")){
            address.setError("Enter address First");
            address.requestFocus();
            return false;
        }if(speciality.getText().toString().equals("")){
            speciality.setError("Enter speciality first");
            speciality.requestFocus();
            return false;
        }if(adminFeedBack.getText().toString().equals("")){
            speciality.setError("Enter admin feedback");
            speciality.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageBase64 = EncodeDecodeUtil.encodeImageToBase64(imageBitmap, this);
                Bitmap decodedImage = EncodeDecodeUtil.decodeBase64ToImage(imageBase64, this);
                caregiverImageView.setImageBitmap(decodedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      else{
            Toast.makeText(this, "Someting issue", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectImageIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        try {
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_PICK);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(AddCaregiverActivity.this, "Photo loading failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        onBackPressedAlertDialog();
    }

    public void onBackPressedAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to exit without adding caregiver?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), ProtectedActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("");
        alertDialog.show();
    }
}