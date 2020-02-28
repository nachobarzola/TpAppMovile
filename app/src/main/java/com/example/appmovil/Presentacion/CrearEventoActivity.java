package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.appmovil.Dominio.Evento;
import com.example.appmovil.Dominio.dao.EventosRepository;
import com.example.appmovil.Dominio.dao.UsuariosRepository;
import com.example.appmovil.R;
import com.example.appmovil.Services.LocalizarDireccion;
import com.example.appmovil.Services.MyBroadcastReceiver;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class CrearEventoActivity extends  FragmentActivity implements View.OnClickListener, OnMapReadyCallback {

    private TextView tvFecha,tvHora,tvUbicacion,tvLimiteNombre,tvLimiteDescripcion;
    private ImageButton btnFecha,btnHora;
    private FloatingActionButton btnGuardar;
    private  EditText et_nombre,et_descripcion;
    private GoogleMap mMap;
    private MarkerOptions marcador;

    private Evento evento;

    private String usuario_id;
    private double latitud,longitud;
    private String direccion;
    private int eventoId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        Intent i = getIntent();
        evento=(Evento)i.getSerializableExtra("evento");
        inicializarElementosGraficos();

        //ESTOY POR EDITAR
        if(evento!=null){
            et_nombre.setText(evento.getNombre());
            et_descripcion.setText(evento.getDescripcion());
            tvFecha.setText(evento.getFecha());
            tvHora.setText(evento.getHora());
            direccion=evento.getUbicacion();
            latitud=evento.getLatitud();
            longitud=evento.getLongitud();
            eventoId=evento.getId();

        }


        //NOTIFICACIONES
        BroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filtro = new IntentFilter();
        filtro.addAction(MyBroadcastReceiver.EVENTO_01);
        getApplication().getApplicationContext()
                .registerReceiver(br,filtro);

        //NOTIFICACIONES

        usuario_id= UsuariosRepository.getInstance().getUser().getId();

        //Le asigno al boton una accion definida en esta misma actividad:
        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        //MAPA
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //CODIGO PARA CONTROLAR TEXTO DEL LIMITE DE CAMPO
        et_nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvLimiteNombre.setText(String.valueOf(s.length()) + "/40");
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        et_descripcion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvLimiteDescripcion.setText(String.valueOf(s.length()) + "/500");
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });



    }



    Handler miHandler = new Handler(Looper.myLooper()){

        @Override
        public void handleMessage(Message msg) {
            Context context = getApplicationContext();
            CharSequence text;
            int duration = Toast.LENGTH_SHORT;
            Toast toast;
            switch (msg.arg1 ){
                case 0:
                    Intent i = new Intent();
                    i.putExtra("data1","Evento guardado");
                    i.putExtra("data2","Evento se guardó correctamente");
                    i.setAction(MyBroadcastReceiver.EVENTO_01);
                    sendBroadcast(i);

                    finish();

                    break;
                case 1:
                    text = "Error al guardar evento";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;

            }
        }
    };

    //Aca se agregan las acciones de todos los botones que hay en la actividad.
    @Override
    public void onClick(View v) {
        //-----------------------
        if(v==btnFecha){
            //Inicializo el calendario en la fecha actual
            final Calendar fActual = Calendar.getInstance();
            int dia,mes,ano;
            dia=fActual.get(Calendar.DAY_OF_MONTH);
            mes=fActual.get(Calendar.MONTH);
            ano=fActual.get(Calendar.YEAR);
            //Creo dialogo de calendario
            DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //Establezco formato de visualizacion de la fecha
                    tvFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
                    ,ano,mes,dia);

            datePickerDialog.show();
        }
        //-----------------------
        if(v==btnHora){
            final Calendar hActual= Calendar.getInstance();
            int hora,minutos;
            hora=hActual.get(Calendar.HOUR);
            minutos=hActual.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog;
            timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String h=String.valueOf(hourOfDay);
                    String m=String.valueOf(minute);

                    if(minute<10) m="0"+m;

                    if(hourOfDay<10) h="0"+h;

                    tvHora.setText(h+":"+m);

                }
            },hora,minutos,true);
            timePickerDialog.show();

        }
        //-----------------------
        if(v==btnGuardar){
            String nombre=et_nombre.getText().toString();
            String descripcion=et_descripcion.getText().toString();
            String fecha=tvFecha.getText().toString();
            String hora =tvHora.getText().toString();
            if(nombre.equals("")){
                Toast.makeText(v.getContext(),"El nombre es obligatorio",Toast.LENGTH_LONG).show();
            }else {
                //El id del evento lo genera el servidor
                Evento eventox = new Evento(eventoId, nombre, descripcion, fecha, hora, direccion, usuario_id, latitud, longitud);

                if(eventoId==0)
                    EventosRepository.getInstance().Guardar(eventox, miHandler);
                else
                    EventosRepository.getInstance().Actualizar(eventox, miHandler);

            }
        }
        //-----------------------

    }
    private void inicializarElementosGraficos(){
        tvFecha=findViewById(R.id.tvFecha);
        btnFecha=findViewById(R.id.btnFecha);
        btnHora=findViewById(R.id.btnHora);
        tvHora=findViewById(R.id.tvHora);
        tvUbicacion=findViewById(R.id.tvUbicacion);
        btnGuardar=findViewById(R.id.btn_guardarEvento);
        tvLimiteNombre=findViewById(R.id.textLimiteNombre);
        tvLimiteDescripcion=findViewById(R.id.textLimiteDescripcion);



        et_nombre=findViewById(R.id.et_nombre_evento);
        et_descripcion=findViewById(R.id.et_descripcion_evento);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    9999);
            return;
        }
        //se posiciona la camara del mapa en el medio de argentina
        LatLng ubicacionInicial= new LatLng(-32.87375 ,-63.466432);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionInicial,4));

        mMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                // TODO Auto-generated method stub
                latitud = point.latitude;
                longitud = point.longitude;

                direccion = LocalizarDireccion.getInstance().getAddress(latitud,longitud,getApplicationContext());

                tvUbicacion.setText(direccion);

                marcador=new MarkerOptions()
                        .position(new LatLng(latitud, longitud))
                        .title(direccion);

                mMap.clear();

                mMap.addMarker(marcador);
            }
        });
    }
}
