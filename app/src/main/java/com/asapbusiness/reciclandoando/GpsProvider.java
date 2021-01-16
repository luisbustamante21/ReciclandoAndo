package com.asapbusiness.reciclandoando;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.vishnusivadas.advanced_httpurlconnection.PutData;



public class GpsProvider {

    private GeoFire mGeoFire;
    private LatLng latLng;


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
    public void updateLocation (String usernameID, double latitud, double longitud){

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

                PutData putData = new PutData("https://luisbustamante.tk/LoginRegister/updateData.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {

                    }
                }
            });

        } catch (Exception e){

        }
    }

    public void removeLocation (String usernameID){

        try {

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {

                String[] field = new String[1];
                field[0] = "usernameID";

                String[] data = new String[1];
                data[0] = usernameID;

                PutData putData = new PutData("https://luisbustamante.tk/LoginRegister/deleteData.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {

                    }
                }
            });

        }catch (Exception e) {


        }
    }

    public void saveLocationDonador (String usernameID, double latitud, double longitud){

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

                PutData putData = new PutData("https://luisbustamante.tk/LoginRegister/savelocationDonador.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {

                    }
                }
            });

        } catch (Exception e){

        }
    }

    public void updateLocationDonador (String usernameID, double latitud, double longitud){

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

                PutData putData = new PutData("https://luisbustamante.tk/LoginRegister/updateDataDonador.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {

                    }
                }
            });

        } catch (Exception e){

        }
    }


    public void removeLocationDonador (String usernameID){

        try {

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {

                String[] field = new String[1];
                field[0] = "usernameID";

                String[] data = new String[1];
                data[0] = usernameID;

                PutData putData = new PutData("https://luisbustamante.tk/LoginRegister/deleteDataDonador.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {

                    }
                }
            });

        }catch (Exception e) {


        }
    }



   public GeoQuery getActiveUsers(LatLng latLng){
        GeoQuery geoQuery = mGeoFire.queryAtLocation(new GeoLocation(latLng.latitude, latLng.longitude ), 5);
        geoQuery.removeAllListeners();
        return geoQuery;
    }

   public void GetActiveDrivers (String usernameID, double latitud, double longitud){

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

               PutData putData = new PutData("https://luisbustamante.tk/LoginRegister/getActiveDrivers.php", "POST", field, data);
               if (putData.startPut()) {
                   if (putData.onComplete()) {

                   }
               }
           });

       } catch (Exception e){

       }

    }
}