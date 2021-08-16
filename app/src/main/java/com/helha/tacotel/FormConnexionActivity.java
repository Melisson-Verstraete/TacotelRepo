package com.helha.tacotel;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import api.ApiClient;
import api.UtilisateurConnecte;
import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import model.Login;
import model.Utilisateur;

public class FormConnexionActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FORM_INSCRIPTION = 1;
    public static final int REQUEST_CODE_FORM_CONNEXION_ADMIN = 1;
    private static final int REQUEST_CODE_FORM_CONNEXION_MENU = 1;
    private static int idUserConnected;

    UtilisateurConnecte utilisateurConnecte;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    TextView tv_tacotel_connexion,tv_connexion,tv_pseudo_connexion,tv_mdp_connexion,
            tv_pas_inscrit,tv_connexion_admin;
    EditText et_pseudo_connexion,et_mdp_connexion;
    Button btn_valider_connexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_connexion);

        //Init Api
        utilisateurConnecte = ApiClient.getClient().create(UtilisateurConnecte.class);

        //views
        et_pseudo_connexion = (EditText)findViewById(R.id.et_pseudo_connexion);
        et_mdp_connexion = (EditText)findViewById(R.id.et_mdp_connexion);
        btn_valider_connexion = (Button)findViewById(R.id.btn_valider_connexion);
        tv_tacotel_connexion = (TextView)findViewById(R.id.tv_tacotel_connexion);
        tv_connexion = (TextView)findViewById(R.id.tv_connexion);
        tv_pseudo_connexion = (TextView)findViewById(R.id.tv_pseudo_connexion);
        tv_mdp_connexion = (TextView)findViewById(R.id.tv_mdp_connexion);
        tv_pas_inscrit = (TextView)findViewById(R.id.tv_pas_inscrit);
        tv_connexion_admin = (TextView)findViewById(R.id.tv_connexion_admin);

        //Event
        btn_valider_connexion.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new SpotsDialog.Builder()
                        .setContext(FormConnexionActivity.this)
                        .build();
                dialog.show();

                //create user to login
                Login login = new Login(et_pseudo_connexion.getText().toString(), et_mdp_connexion.getText().toString());

                compositeDisposable.add(utilisateurConnecte.loginUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Utilisateur>() {
                    @Override
                    public void accept(Utilisateur u) throws Exception {
                        Intent intent = new Intent(FormConnexionActivity.this, MenuActivity.class);
                        intent.putExtra("pseudo", u.getIdUser());
                        startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION_MENU);
                        idUserConnected = u.getIdUser();
                        dialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dialog.dismiss();
                        Toast.makeText(FormConnexionActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        });
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    public static int getIdUserConnected() { return idUserConnected; }

    public void goToFormInscription2(View view) {
        Intent intent = new Intent(FormConnexionActivity.this, FormInscriptionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_INSCRIPTION);
    }

    public void goToFormConnexionAdmin(View view) {
        Intent intent = new Intent(FormConnexionActivity.this, FormConnexionAdminActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION_ADMIN);
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(FormConnexionActivity.this, MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION_MENU);
    }
}
