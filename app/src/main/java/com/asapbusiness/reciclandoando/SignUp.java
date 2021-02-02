package com.asapbusiness.reciclandoando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextPassword, textInputEditTextEmail;
    Button buttonSignup;
    TextView textViewLogin;
    ProgressBar progressBar;
    RadioGroup rg1;
    RadioButton reciclador, donador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextEmail = findViewById(R.id.email);

        reciclador = findViewById(R.id.reciclador);
        donador = findViewById(R.id.donador);

        rg1 = findViewById(R.id.typeuser);


        buttonSignup = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname;
                String username;
                String password;
                String email;

                fullname = textInputEditTextFullname.getText().toString();
                username = textInputEditTextUsername.getText().toString();
                password = textInputEditTextPassword.getText().toString();
                email = textInputEditTextEmail.getText().toString();

                if(rg1.getCheckedRadioButtonId()!=-1){
                    int id= rg1.getCheckedRadioButtonId();
                    View radioButton = rg1.findViewById(id);
                    int radioId = rg1.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) rg1.getChildAt(radioId);
                    String tipousuario = (String) btn.getText();
                    if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("") && !tipousuario.equals("")) {

                        progressBar.setVisibility(View.VISIBLE);

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(() -> {

                            String[] field = new String[5];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            field[4] = "typeuser";

                            String[] data = new String[5];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            data[4] = tipousuario;


                            PutData putData = new PutData("https://luisbustamante.tk/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Registro Exitoso!")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

               /* int id = mtypeuser.getCheckedRadioButtonId();
                RadioButton tipo = (RadioButton) mtypeuser.findViewById(id);
                String tipousuario = tipo.getText().toString();

                */


                else {
                    Toast.makeText(getApplicationContext(), "All files are required", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}