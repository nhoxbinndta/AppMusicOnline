package com.example.appmusiconline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmusiconline.R;
import com.example.appmusiconline.Service.APIService;
import com.example.appmusiconline.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText edtFirstName , edtLastName , edtEmail , edtRepeatPassword , edtPassword ;
    ImageView btnFisnish ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mapping();

        btnFisnish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtEmail.getText().toString() == "" && edtPassword.getText().toString() == "" &&
                        edtPassword.getText().toString() != edtRepeatPassword.getText().toString() ) {
                    Toast.makeText(SignUpActivity.this, "Please check forms again", Toast.LENGTH_SHORT).show();
                }
                else {
                    createUser(edtEmail.getText().toString() , edtPassword.getText().toString());

                    Intent intent = new Intent(SignUpActivity.this , TrangchuActivity.class);
                    startActivity(intent);
                }
            }


        });
    }

    private void createUser(String username, String password) {
        DataService dataService = APIService.getService();
        Call<String> callback = dataService.createuser(username , password);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("USER123" , "Thanh cong" + response.body()) ;
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("USER123" , "That bai") ;
            }
        });

    }

    private void mapping() {
        edtFirstName = (EditText) findViewById(R.id.firstname) ;
        edtLastName = (EditText) findViewById(R.id.lastname) ;
        edtEmail = (EditText) findViewById(R.id.email) ;
        edtRepeatPassword = (EditText) findViewById(R.id.againpassword) ;
        edtPassword = (EditText) findViewById(R.id.password) ;
        btnFisnish = (ImageView) findViewById(R.id.btnFinish);
    }
}
