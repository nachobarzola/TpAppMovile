package com.example.appmovil.Services;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public final class LocalizarDireccion {

    private static LocalizarDireccion _INSTANCE;

    private LocalizarDireccion(){}

    public static LocalizarDireccion getInstance(){
        if(_INSTANCE==null){
            _INSTANCE = new LocalizarDireccion();
        }
        return _INSTANCE;
    }

    public String getAddress(double latitude, double longitude, Context context) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                if(address.getThoroughfare()!=null)
                    result.append(address.getThoroughfare()).append(" ");
                if(address.getSubThoroughfare()!=null)
                    result.append(address.getSubThoroughfare()).append(",");
                if(address.getSubAdminArea()!=null)
                    result.append(address.getSubAdminArea()).append(", ");
                if(address.getLocality()!=null)
                    result.append(address.getLocality()).append(", ");
                if(address.getCountryName()!=null)
                    result.append(address.getCountryName());
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }
}
