package com.asapbusiness.reciclandoando;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;



public class GpsProvider {


    public void saveLocation (String usernameID, double latitud, double longitud){

        try {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {

                String[] field = new String[3];
                field[0] = "usernameID";
                field[1] = "latitud";
                field[2] = "longitud";

                String[] data = new String[3];
                data[0] = usernameID;
                data[1] = String.valueOf(latitud);
                data[2] = String.valueOf(longitud);

                PutData putData = new PutData("https://luisbustamante.tk/LoginRegister/gpstracking.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {

                    }
                }
            });

} catch (Exception e){

        }
    }

    public void removeLocation(){

    }
}