package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmovil.Dominio.dao.InvitacionesRepository;
import com.example.appmovil.Dominio.dao.UsuariosRepository;
import com.example.appmovil.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EscanearQrActivity extends AppCompatActivity {

    private int permisoCamara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escanear_qr);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

        }
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                TextView textView = new TextView(this);
                textView.setText("Debe conceder permisos de cámara para poder escanear los códigos QR");

                builder.setView(textView);
                builder.show();

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        permisoCamara);
            }

        IntentIntegrator integrator = new IntentIntegrator(EscanearQrActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");

            } else {
                Log.e("Scan", "Scanned");

               // tv_qr_readTxt.setText(result.getContents());
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                int invitacionId = 0;

                try {
                    invitacionId=Integer.parseInt(result.getContents());
                    InvitacionesRepository.getInstance().RegistrarIngreso(
                            UsuariosRepository.getInstance().getUser().getId(),
                            invitacionId,
                            miHandler);
                } catch (Exception e) {
                    Toast.makeText(this, "Scanned: " + "No es una invitación válida", Toast.LENGTH_LONG).show();
                }


            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    Handler miHandler = new Handler(Looper.myLooper()){

        @Override
        public void handleMessage(Message msg) {
            Context context = getApplicationContext();
            CharSequence text;
            int duration = Toast.LENGTH_LONG;
            Toast toast;
            switch (msg.arg1 ){
                case 0:
                    text = "INGRESO OK";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                case 1:
                    text = "ERROR EN LA RED";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                    finish();
                    break;

            }
        }
    };
}
