package com.asapbusiness.reciclandoando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextPassword, textInputEditTextEmail;
    Button buttonSignup;
    TextView textViewLogin;
    ProgressBar progressBar;

    //private TextInputLayout textInputLayoutTypeuser;
    //private AutoCompleteTextView dropDownText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextEmail = findViewById(R.id.email);
        //textInputEditTextTypeuser = findViewById(R.id.textInputLayoutTypeuser);

        buttonSignup = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname, username, password, email, typeuser;
                fullname = textInputEditTextFullname.getText().toString();
                username = textInputEditTextUsername.getText().toString();
                password = textInputEditTextPassword.getText().toString();
                email = textInputEditTextEmail.getText().toString();
                //typeuser = textInputEditTextTypeuser.getText().toString();

                //if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("") && !typeuser.equals("")) {

                if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {

                    progressBar.setVisibility(View.VISIBLE);

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(() -> {

                        String[] field = new String[4];
                        field[0] = "fullname";
                        field[1] = "username";
                        field[2] = "password";
                        field[3] = "email";

                        String[] data = new String[4];
                        data[0] = fullname;
                        data[1] = username;
                        data[2] = password;
                        data[3] = email;

                        PutData putData = new PutData("https://luisbustamante.tk/LoginRegister/signup.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if (result.equals("Sign Up Success")){
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Login.class);
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

        /*textInputLayoutTypeuser = findViewById(R.id.textInputLayoutTypeuser);

        dropDownText = findViewById(R.id.dropdown_text);

        String[] items = new String[]{
                "RECOLECTOR DE BOTELLAS",
                "DONADOR DE BOTELLAS"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                SignUp.this,
                R.layout.dropdown_item,
                items
        );

        dropDownText.setAdapter(adapter);*/
    }
}