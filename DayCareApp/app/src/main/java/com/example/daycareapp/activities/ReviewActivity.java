package com.example.daycareapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.daycareapp.R;
import com.example.daycareapp.adapters.ReviewsListAdapter;
import com.example.daycareapp.models.ReviewListData;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ReviewListData[] reviewsListData = new ReviewListData[] {
                new ReviewListData("Rahim", "Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_dialog_email),
                new ReviewListData("Karim", "Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_dialog_info),
                new ReviewListData("Khalek","Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_delete),
                new ReviewListData("Rostom", "Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_dialog_dialer),
                new ReviewListData("Manik", "Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_dialog_alert),
                new ReviewListData("Badol", "Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_dialog_map),
                new ReviewListData("Shohag", "Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_dialog_email),
                new ReviewListData("Imran", "Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_dialog_info),
                new ReviewListData("Deduar", "Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_delete),
                new ReviewListData("Rihan", "Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_dialog_dialer),
                new ReviewListData("Rokon", "Hello I am reviwing, all is okay and I am happy",  android.R.drawable.ic_dialog_alert),
                new ReviewListData("Anis", "Hello I am reviwing, all is okay and I am happy", android.R.drawable.ic_dialog_map),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reviewerRecyclerView);
        ReviewsListAdapter adapter = new ReviewsListAdapter(reviewsListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}