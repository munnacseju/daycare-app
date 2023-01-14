package com.example.daycareapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daycareapp.R;
import com.example.daycareapp.RetrofitClient;
import com.example.daycareapp.adapters.BabyListAdapter;
import com.example.daycareapp.listeners.BabyClickListener;
import com.example.daycareapp.models.Baby;
import com.example.daycareapp.network.response.AllBabyResponse;
import com.example.daycareapp.util.SharedRefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyActivity extends AppCompatActivity {
    private String demoMessage;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    BabyListAdapter babyListAdapter;
    List<Baby> babyList;
    private SharedRefs sharedRefs;
    private Dialog dialog;
    Long caregiverId;
    private Button addBaby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby);
        caregiverId = this.getIntent().getLongExtra("caregiver_id", 1);

        babyList = new ArrayList<>();

        getBabyList();

        dialog = new Dialog(getApplicationContext());
        addBaby = findViewById(R.id.addBabyBtId);

        addBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddBabyActivity.class);
                intent.putExtra("caregiver_id", caregiverId);
                startActivity(intent);
            }
        });
    }

    private final BabyClickListener babyClickListener = new BabyClickListener() {
        @Override
        public void onClick(Baby baby) {
            Intent intent = new Intent(getApplicationContext(), DateActivity.class);
            intent.putExtra("caregiver_id", caregiverId);
            intent.putExtra("baby_id", baby.getId());
            startActivity(intent);
//            getActivity().finish();
            Toast.makeText(getApplicationContext(), "selected: " + baby.getBabyName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLongClick(Baby baby, TextView textView) {
//            callForDialog(baby.getBabyName());
            Toast.makeText(getApplicationContext(), "long selected: " + baby.getBabyName(), Toast.LENGTH_SHORT).show();
        }
    };

    private void getBabyList() {
//        progressBar.setVisibility(View.VISIBLE);
        Call<AllBabyResponse> call = RetrofitClient
                .getInstance()
                .getAPI()
                .findAllBaby();

        call.enqueue(new Callback<AllBabyResponse>() {
            @Override
            public void onResponse(Call<AllBabyResponse> call, Response<AllBabyResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    String message = response.body().getStatus();
                    Toast.makeText(getApplicationContext(), "Successfully got data! status: "+message, Toast.LENGTH_LONG).show();
                    babyList = response.body().getBabies();
                    Toast.makeText(getApplicationContext(), "list size: " + babyList.size(), Toast.LENGTH_SHORT).show();

                    updateProjectsRecycler();


                } else {
//                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Some unknown problem occurred!! "+response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AllBabyResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateProjectsRecycler() {
        recyclerView = findViewById(R.id.projectsRecyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        babyListAdapter = new BabyListAdapter(getApplicationContext(), babyList, babyClickListener);
        recyclerView.setAdapter(babyListAdapter);
    }



//    private void callForDialog(String dialogStringData){
//        dialog.setContentView(R.layout.sample_info_layout);
//        TextView cancell = dialog.findViewById(R.id.cancelId);
//        TextView dialogText = dialog.findViewById(R.id.dialogTextId);
//        dialogText.setText(dialogStringData);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        cancell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//            }
//        });
//        dialog.show();
//    }
}


