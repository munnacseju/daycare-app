package com.example.daycareapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daycareapp.R;
import com.example.daycareapp.util.EncodeDecodeUtil;

public class FeeActivity extends AppCompatActivity {
    
    TextView nametv, locationtv, feetv, feedBacktv, specialityTv, isAvailableTv;
    ImageView imageView;
    Button addBabyButton, seeReviewBt;
    Long caregiverId = -1L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee);
        String PACKAGE_NAME = getPackageName();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.bg_button);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nametv = findViewById(R.id.nametvid);
        locationtv = findViewById(R.id.locationtvid);
        imageView = findViewById(R.id.profileimageid);
        feetv = findViewById(R.id.feetvid);
        feedBacktv = findViewById(R.id.feedBackId);
        specialityTv = findViewById(R.id.speciality);
        addBabyButton = findViewById(R.id.addBabyBtId);
        seeReviewBt = findViewById(R.id.reviewBtId);

        String name = this.getIntent().getStringExtra("name");
        String location = this.getIntent().getStringExtra("location");
        String imageText = this.getIntent().getStringExtra("img");
        caregiverId = this.getIntent().getLongExtra("caregiver_id", -1);
        String speciality = this.getIntent().getStringExtra("speciality");
        String feedback = this.getIntent().getStringExtra("feedback");
//        Boolean isAvailable = this.getIntent().getBooleanExtra("isAvailable", true);

//        Toast.makeText(this, "img: " +imageText, Toast.LENGTH_SHORT).show();

        nametv.setText(name);
        locationtv.setText(location);
        feetv.setText("Fee: 100tk");
        specialityTv.setText("Speciality: " + speciality);
        feedBacktv.setText("Admin Feedback: "+feedback);

        Bitmap mbitmap= EncodeDecodeUtil.decodeBase64ToImage(imageText, this);
        Bitmap imageRounded=Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas=new Canvas(imageRounded);
        Paint mpaint=new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 10, 10, mpaint); // Round Image Corner 100 100 100 100
        imageView.setImageBitmap(imageRounded);

        addBabyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BabyActivity.class);
                intent.putExtra("caregiver_id", caregiverId);
                finish();
                startActivity(intent);
                Toast.makeText(FeeActivity.this, "Clicked for book now", Toast.LENGTH_SHORT).show();
            }
        });

        seeReviewBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                intent.putExtra("caregiver_id", caregiverId);
                finish();
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            Intent intent = new Intent(getApplicationContext(), ProtectedActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), ProtectedActivity.class);
        startActivity(intent);
    }
}