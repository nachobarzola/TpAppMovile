package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.appmovil.R;

import java.util.Calendar;

public class CrearEventoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvFecha,tvHora;
    private Button btnFecha,btnHora;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        inicializarElementosGraficos();

        //Le asigno al boton una accion definida en esta misma actividad:
        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);





    }





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
                    tvHora.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,true);
            timePickerDialog.show();

        }
        //-----------------------

    }
    private void inicializarElementosGraficos(){
        tvFecha=findViewById(R.id.tvFecha);
        btnFecha=findViewById(R.id.btnFecha);
        btnHora=findViewById(R.id.btnHora);
        tvHora=findViewById(R.id.tvHora);

    }
}
