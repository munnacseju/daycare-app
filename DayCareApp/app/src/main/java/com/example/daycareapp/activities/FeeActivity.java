package com.example.daycareapp.activities;

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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daycareapp.R;

public class FeeActivity extends AppCompatActivity {
    
    TextView nametv, locationtv, feetv, ratingtv;
    ImageView imageView;
    Button addBabyButton, seeReviewBt;
    Long caregiverId = -1L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee);
        String PACKAGE_NAME = getPackageName();

        nametv = findViewById(R.id.nametvid);
        locationtv = findViewById(R.id.locationtvid);
        imageView = findViewById(R.id.profileimageid);
        feetv = findViewById(R.id.feetvid);
        ratingtv = findViewById(R.id.ratingid);
        addBabyButton = findViewById(R.id.addBabyBtId);
        seeReviewBt = findViewById(R.id.reviewBtId);

        String name = this.getIntent().getStringExtra("name");
        String location = this.getIntent().getStringExtra("location");
        String imageText = this.getIntent().getStringExtra("img");
        caregiverId = this.getIntent().getLongExtra("caregiver_id", -1);

        Toast.makeText(this, "img: " +imageText, Toast.LENGTH_SHORT).show();

        nametv.setText(name);
        locationtv.setText(location);
        feetv.setText("Fee: 100tk");
        ratingtv.setText("Specialit: Singer");
        int imageId = getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + imageText, null, null);
//        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), imageId));

        Bitmap mbitmap=((BitmapDrawable) getResources().getDrawable(imageId)).getBitmap();
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
                finish();
                startActivity(intent);
            }
        });
    }


}