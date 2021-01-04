package com.asapbusiness.reciclandoando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    CheckBox mRemember;
    private GpsProvider objetoProvider = new GpsProvider();

    SharedPreferences sharedPreferences;

    boolean isRemembered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        textInputEditTextUsername = findViewById(R.id.usernameLogin);
        textInputEditTextPassword = findViewById(R.id.passwordLogin);
        mRemember = findViewById(R.id.checkBox);

        buttonLogin = findViewById(R.id.btnLogin);
        textViewSignUp = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);


       sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE);
       isRemembered = sharedPreferences.getBoolean("CHECKBOX", false);

        if(isRemembered){

            if(sharedPreferences.getString("tipo", "").equals("Reciclador")){
                objetoProvider.updateLocation(sharedPreferences.getString("username", ""),0.0, 0.0);
                Intent intent = new Intent(getApplicationContext(), MapReciclador.class);
                startActivity(intent);
                finish();

            }

            if(sharedPreferences.getString("tipo", "").equals("Donador")){
                objetoProvider.updateLocationDonador(sharedPreferences.getString("username", ""),0.0, 0.0);
                Intent intent = new Intent(getApplicationContext(), MapDonador.class);
                startActivity(intent);
                finish();

            }

        }

        /*
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            int defaultValue = getResources().getInteger(R.integer.saved_high_score_default_key);
            int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);
         */

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
                String fullname, username, password;
                username = textInputEditTextUsername.getText().toString();
                password = textInputEditTextPassword.getText().toString();

                boolean checked = mRemember.isChecked();

                //SharedPreferences preferencias = getSharedPreferences("datos", MODE_PRIVATE);
                SharedPreferences.Editor obj_editor = sharedPreferences.edit();
                obj_editor.putString("username", textInputEditTextUsername.getText().toString());
                obj_editor.putString("pass", textInputEditTextPassword.getText().toString());
                obj_editor.putBoolean("CHECKBOX", checked);

                //obj_editor.apply();

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
                                //objetoProvider.saveLocation(username,0.0, 0.0);
                                obj_editor.putString("tipo", result); /*---------------------*/
                                if (result.equals("Reciclador")){
                                    obj_editor.apply();
                                    objetoProvider.saveLocation(username,0.0, 0.0);
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MapReciclador.class);
                                    startActivity(intent);
                                    finish();
                                }
                              if (result.equals("Donador")){
                                    obj_editor.apply();
                                  //objetoProvider.saveLocationDonador(username,0.0, 0.0);
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), MapDonador.class);
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