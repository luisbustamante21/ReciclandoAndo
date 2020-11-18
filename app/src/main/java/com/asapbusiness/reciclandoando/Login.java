package com.asapbusiness.reciclandoando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    TextInputEditText  textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextUsername = findViewById(R.id.usernameLogin);
        textInputEditTextPassword = findViewById(R.id.passwordLogin);

        buttonLogin = findViewById(R.id.btnLogin);
        textViewSignUp = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                finish();
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname, username, password, email, typeuser;
                username = textInputEditTextUsername.getText().toString();
                password = textInputEditTextPassword.getText().toString();
                //typeuser = textInputEditTextTypeuser.getText().toString();

                //if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("") && !typeuser.equals("")) {

                if(!username.equals("") && !password.equals("")) {

                    progressBar.setVisibility(View.VISIBLE);

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(() -> {

                        String[] field = new String[2];
                        field[0] = "username";
                        field[1] = "password";

                        String[] data = new String[2];
                        data[0] = username;
                        data[1] = password;

                        PutData putData = new PutData("https://luisbustamante.tk/LoginRegister/login.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if (result.equals("Login Success")){
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                                else{
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "All files are required", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}