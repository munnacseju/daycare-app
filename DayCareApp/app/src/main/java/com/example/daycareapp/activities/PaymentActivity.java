package com.example.daycareapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daycareapp.R;
import com.example.daycareapp.models.Baby;
import com.example.daycareapp.models.Order;
import com.example.daycareapp.network.RetrofitAPIClient;
import com.example.daycareapp.network.response.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    Button payconfirmbt;
    EditText paymentCardEt, paymentCardPassEt;
    Long orderId = -1L;
    Order order = new Order();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        orderId = this.getIntent().getLongExtra("orderId", -1);


        payconfirmbt = findViewById(R.id.payconfirmbtid);
        paymentCardEt = findViewById(R.id.paymentCardNumberEtBt);
        paymentCardPassEt = findViewById(R.id.paymentPassEtId);
        payconfirmbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.setId(orderId);
                Toast.makeText(PaymentActivity.this, "Order Id: " + order.getId(), Toast.LENGTH_SHORT).show();

                order.setAccountNumber(paymentCardEt.getText().toString().trim());
                order.setAccountPass(paymentCardPassEt.getText().toString().trim());
                updateOrder(order);
                Intent intent = new Intent(getApplicationContext(), ConfirmPaymentActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateOrder(Order order) {
        {
//        progressBar.setVisibility(View.VISIBLE);
            Call<DefaultResponse> call = RetrofitAPIClient
                    .getInstance()
                    .getAPI()
                    .updateOrder(order);

            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        DefaultResponse defaultResponse = response.body();
                        Toast.makeText(getApplicationContext(), defaultResponse.getMessage() + ", Status: " + defaultResponse.getStatus(), Toast.LENGTH_LONG).show();
                    } else {
//                    progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Some unknown problem occurred!! "+response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}