package com.example.daycareapp.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daycareapp.R;
import com.example.daycareapp.RetrofitClient;
import com.example.daycareapp.activities.FeeActivity;
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

public class BabyFragment extends Fragment {
    private String demoMessage;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    BabyListAdapter babyListAdapter;
    List<Baby> babyList;
    private SharedRefs sharedRefs;
    private Dialog dialog;
    private Button filterButton;

    public BabyFragment(String demoMessage) {
        this.demoMessage = demoMessage;
    }

    public static BabyFragment newInstance(String demoMessage) {
        BabyFragment fragment = new BabyFragment(demoMessage);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        babyList = new ArrayList<>();

        getCareGiverList();

        dialog = new Dialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_baby, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        TextView demoTextView = view.findViewById(R.id.demo_text_view);
//        demoTextView.setText(demoMessage);
    }

    public static class newInstance extends Fragment {
        public newInstance(String demo_fragment) {
        }
    }


    private final BabyClickListener babyClickListener = new BabyClickListener() {
        @Override
        public void onClick(Baby baby) {
            Intent intent = new Intent(getContext(), FeeActivity.class);
            intent.putExtra("name", baby.getBabyName());
            intent.putExtra("location", baby.getAddress());
            intent.putExtra("img", "img1");
            startActivity(intent);
//            getActivity().finish();
            Toast.makeText(getContext(), "selected: " + baby.getBabyName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLongClick(Baby baby, TextView textView) {
            callForDialog(baby.getBabyName());
            Toast.makeText(getContext(), "long selected: " + baby.getBabyName(), Toast.LENGTH_SHORT).show();
        }
    };

    private void getCareGiverList() {
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
                    Toast.makeText(getActivity(), "Successfully got data! status: "+message, Toast.LENGTH_LONG).show();
                    babyList = response.body().getBabies();
                    Toast.makeText(getContext(), "list size: " + babyList.size(), Toast.LENGTH_SHORT).show();

                    updateProjectsRecycler();


                } else {
//                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Some unknown problem occurred!! "+response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AllBabyResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateProjectsRecycler() {
        recyclerView = getView().findViewById(R.id.projectsRecyclerView);
        layoutManager = new LinearLayoutManager(getView().getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        babyListAdapter = new BabyListAdapter(getContext(), babyList, babyClickListener);
        recyclerView.setAdapter(babyListAdapter);
    }

    private void callForDialog(String dialogStringData){
        dialog.setContentView(R.layout.sample_info_layout);
        TextView cancell = dialog.findViewById(R.id.cancelId);
        TextView dialogText = dialog.findViewById(R.id.dialogTextId);
        dialogText.setText(dialogStringData);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

}