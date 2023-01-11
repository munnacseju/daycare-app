package com.example.daycareapp.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daycareapp.R;
import com.example.daycareapp.activities.DateActivity;
import com.example.daycareapp.activities.FeeActivity;
import com.example.daycareapp.adapters.CaregiverListAdapter;
import com.example.daycareapp.listeners.CaregiverClickListener;
import com.example.daycareapp.models.Caregiver;
import com.example.daycareapp.util.SharedRefs;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    CaregiverListAdapter caregiverListAdapter;
    List<Caregiver> caregiverList;
    private SharedRefs sharedRefs;
    private Dialog dialog;
    private Button filterButton;


    public HomeFragment(Context context) {
        sharedRefs = new SharedRefs(context);
    }

    public static HomeFragment newInstance(Context context) {
        return new HomeFragment(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialog = new Dialog(getContext());
        caregiverList = new ArrayList<>();
        caregiverList.add(new Caregiver("Rahima Khatun", "Mirpur", "img1"));
        caregiverList.add(new Caregiver("Hafsa Banu", "Savar", "img2"));
        caregiverList.add(new Caregiver("Mahmuda Akter", "Dhanmondi", "img3"));
        caregiverList.add(new Caregiver("Fariha Akter", "New Market", "img4"));
        caregiverList.add(new Caregiver("Kulsum Akter", "New Market", "img2"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        filterButton = view.findViewById(R.id.filterButtonId);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callForFilterDialog();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateProjectsRecycler();
    }

    private final CaregiverClickListener caregiverClickListener = new CaregiverClickListener() {
        @Override
        public void onClick(Caregiver caregiver) {
            Intent intent = new Intent(getContext(), FeeActivity.class);
            intent.putExtra("name", caregiver.getName());
            intent.putExtra("location", caregiver.getLocation());
            intent.putExtra("img", caregiver.getImagefileText());
            startActivity(intent);
//            getActivity().finish();
            Toast.makeText(getContext(), "selected: " + caregiver.getName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLongClick(Caregiver caregiver, TextView textView) {
            callForDialog(caregiver.getName());
            Toast.makeText(getContext(), "long selected: " + caregiver.getName(), Toast.LENGTH_SHORT).show();
        }
    };

    private void updateProjectsRecycler() {
        recyclerView = getView().findViewById(R.id.projectsRecyclerView);
        layoutManager = new LinearLayoutManager(getView().getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        caregiverListAdapter = new CaregiverListAdapter(getContext(), caregiverList, caregiverClickListener);
        recyclerView.setAdapter(caregiverListAdapter);
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

    private void callForFilterDialog(){
        dialog.setContentView(R.layout.sample_filter_layout);
        TextView cancell = dialog.findViewById(R.id.cancelId);
        Button startDatebt = dialog.findViewById(R.id.startDateBtId);
        startDatebt.setText("Start Date");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callForDialog("I am hello");
                dialog.cancel();
            }
        });

        startDatebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callForTimePickerDiolog();
            }
        });
        dialog.show();
    }

    public void callForTimePickerDiolog(){
        int mMinute = 0, mHour = 0;
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

}