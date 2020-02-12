package com.example.appmovil.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmovil.R;

import com.example.appmovil.Dominio.dao.UsuariosRepository;
import com.example.appmovil.Dominio.Usuario;

public class LoginActivity extends AppCompatActivity {
    private Button btn_iniciarSesion;
    private EditText etEmail;
    private EditText etPassword;

    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn
                .getLastSignedInAccount(this);

        actualizarUI(account);
    }


    private void actualizarUI(GoogleSignInAccount acct ){

            if (acct != null) {
                signInButton.setVisibility(View.GONE);

                Usuario usuario = new Usuario(acct.getId(),acct.getDisplayName(),acct.getGivenName(),acct.getFamilyName(),acct.getEmail());

                UsuariosRepository.getInstance().setUser(usuario);

                Intent i= new Intent(getApplicationContext(),MenuActivity.class);

                startActivity(i);
            }
            else {

                signInButton.setVisibility(View.VISIBLE);
            }
        }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        inicializarElementosGraficos();

        GoogleSignInOptions gso =
                new GoogleSignInOptions
                        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setSize(SignInButton.SIZE_WIDE);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 999);

            }
        });

        btn_iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String email=etEmail.getText().toString();

                String pass=etPassword.getText().toString();

                //Usuario usuario=new Usuario(email,pass);

                //HANDLER
                Handler miHandler = new Handler(Looper.myLooper()){
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.arg1 ){
                            case 0:
                                Log.d("Login","Login OK");

                                Intent i= new Intent(v.getContext(),MenuActivity.class);
                                startActivity(i);

                                break;
                            case 1:
                                Log.d("Login","Error al loguear");
                                Context context = getApplicationContext();
                                CharSequence text = "Usuario y/o password incorrecto";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                break;

                        }
                    }
                };

                //UsuariosRepository.getInstance().obtenerUsuario(usuario,miHandler);

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode ==999) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();

            Usuario usuario = new Usuario(personId,personName,personGivenName,personFamilyName,personEmail);

            UsuariosRepository.getInstance().Guardar(usuario,miHandler);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

        }
    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            switch (msg.arg1 ){

                case 1:
                    Log.d("Login","Error al loguear");
                    CharSequence text = "Usuario y/o password incorrecto";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                case 2:
                    CharSequence textOk = "Usuario guardado con exito";

                    Toast toastOk = Toast.makeText(context, textOk, duration);

                    toastOk.show();

                    Intent i= new Intent(getApplicationContext(),MenuActivity.class);

                    startActivity(i);
                    break;

            }
        }
    };
    private void inicializarElementosGraficos(){
        this.btn_iniciarSesion=findViewById(R.id.btn_IniciarSesion);
        this.etEmail=findViewById(R.id.etEmail);
        this.etPassword=findViewById(R.id.etPassword);
        this.signInButton=findViewById(R.id.sign_in_button);
    }

}
