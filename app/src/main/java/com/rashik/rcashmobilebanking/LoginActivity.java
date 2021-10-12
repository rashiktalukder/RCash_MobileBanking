package com.rashik.rcashmobilebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rashik.rcashmobilebanking.interfaces.WebService;
import com.rashik.rcashmobilebanking.model.MyRetrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText phoneNumberText, pinNumberText;
    Button loginButton;
    ProgressBar progressBar;
    WebService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Initialization();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phoneNumberText.getText().toString().equals("")||pinNumberText.getText().toString().equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Please Insert all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();

                    SendLoginRequest(phoneNumberText.getText().toString(),pinNumberText.getText().toString());
                }
            }
        });

    }

    private void SendLoginRequest(String phoneNumberText, String pinNumberText) {

        service= MyRetrofit.getInstance();

        Call<ResponseBody> login=service.loginNow(phoneNumberText,pinNumberText);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressBar.setVisibility(View.GONE);
                if(!response.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Response Unsuccessful", Toast.LENGTH_SHORT).show();
                    return;
                }
                String serverMsg="";

                try {
                   serverMsg =response.body().string();


                } catch (IOException e) {
                    e.printStackTrace();
                }


                Toast.makeText(LoginActivity.this, serverMsg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void Initialization() {
        phoneNumberText=findViewById(R.id.phoneNumberEditText);
        pinNumberText=findViewById(R.id.pinNumberEditText);
        loginButton=findViewById(R.id.loginButton);
        progressBar=findViewById(R.id.progressBar);
    }
}