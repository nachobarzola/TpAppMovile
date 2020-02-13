package com.example.appmovil.Presentacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appmovil.Dominio.dao.UsuariosRepository;
import com.example.appmovil.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MenuActivity extends AppCompatActivity {
    private Button btn_crearEvento;
    private Button btn_listaEventos;
    private Button btn_mis_invitaciones;

    private TextView tvNombre;
    private Toolbar miToolbar;

    private String nombreUsuario;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //GOOGLE SIGN IN
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        inicializarElementosGraficos();
        nombreUsuario= UsuariosRepository.getInstance().getUser().getName();
        tvNombre.setText("Bienvenido "+nombreUsuario);

        btn_crearEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNuevoEvento=new Intent(v.getContext(),CrearEventoActivity.class);
                startActivity(iNuevoEvento);
            }
        });

        btn_listaEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iListaEventos=new Intent(v.getContext(),ListaEventos.class);
                startActivity(iListaEventos);
            }
        });

        btn_mis_invitaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imisInvitaciones=new Intent(v.getContext(),MisInvitacionesActivity.class);
                startActivity(imisInvitaciones);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuOpt1:

                return true;

            case R.id.mnuOpt2:
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("logout","deslogueo");

                                finish();
                            }
                        });
                return true;


        }
        return super.onOptionsItemSelected(item);

    }
    private void inicializarElementosGraficos(){
        btn_crearEvento=findViewById(R.id.btn_crearEvento);
        btn_listaEventos=findViewById(R.id.btn_listaEventos);
        btn_mis_invitaciones=findViewById(R.id.btn_mis_invitaciones);

        tvNombre=findViewById(R.id.tvNombre);

        miToolbar=findViewById(R.id.toolbarHome);
        setSupportActionBar(miToolbar);

    }



}
