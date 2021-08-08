package com.helha.tacotel;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.List;

import api.ApiClient;
import api.UtilisateurConnecte;
import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import model.Client;
import model.Utilisateur;
import repository.ClientRepository;

public class FormInscriptionActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FORM_CONNEXION = 1;
    private static final int REQUEST_CODE_FORM_CONNEXION_MENU = 1;

    UtilisateurConnecte utilisateurConnecte;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    TextView tv_tacotel_inscription,tv_inscription,tv_nom,tv_prenom,tv_pseudo_inscription,tv_email,tv_mdp_inscription;
    EditText et_nom,et_prenom,et_pseudo_inscription,et_email,et_mdp_inscription;
    Button btn_valider_inscription,btn_annuler_inscription;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_inscription);

        //Init Api
        utilisateurConnecte = ApiClient.getClient().create(UtilisateurConnecte.class);

        //views
        et_nom = (EditText)findViewById(R.id.et_nom);
        et_prenom = (EditText)findViewById(R.id.et_prenom);
        et_pseudo_inscription = (EditText)findViewById(R.id.et_pseudo_inscription);
        et_email = (EditText)findViewById(R.id.et_email);
        et_mdp_inscription = (EditText)findViewById(R.id.et_mdp_inscription);
        btn_valider_inscription = (Button)findViewById(R.id.btn_valider_inscription);
        btn_annuler_inscription = (Button)findViewById(R.id.btn_annuler_inscription);
        tv_tacotel_inscription = (TextView)findViewById(R.id.tv_tacotel_inscription);
        tv_inscription = (TextView)findViewById(R.id.tv_inscription);
        tv_nom = (TextView)findViewById(R.id.tv_nom);
        tv_prenom = (TextView)findViewById(R.id.tv_prenom);
        tv_pseudo_inscription = (TextView)findViewById(R.id.tv_pseudo_inscription);
        tv_email = (TextView)findViewById(R.id.tv_email);
        tv_mdp_inscription = (TextView)findViewById(R.id.tv_mdp_inscription);

        //Event

        btn_valider_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new SpotsDialog.Builder()
                        .setContext(FormInscriptionActivity.this)
                        .build();
                dialog.show();

                //create user to register
                Utilisateur user = new Utilisateur(
                        null,
                        et_nom.getText().toString(),
                        et_prenom.getText().toString(),
                        et_pseudo_inscription.getText().toString(),
                        et_mdp_inscription.getText().toString(),
                        et_email.getText().toString(),
                        null
                );
                Toast.makeText(FormInscriptionActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                        // new Utilisateur(et_nom.getText().toString(), et_prenom.getText().toString(), et_pseudo_inscription.getText().toString(), et_email.getText().toString(), et_mdp_inscription.getText().toString());

                        compositeDisposable.add(utilisateurConnecte.registerUser(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Utilisateur>() {
                            @Override
                            public void accept(Utilisateur u) throws Exception {
                                if(u.equals("Successfully"))
                                {
                                    finish();
                                }
                                //Toast.makeText(FormInscriptionActivity.this, u.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FormInscriptionActivity.this, MenuActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION_MENU);
                                dialog.dismiss();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                dialog.dismiss();
                                Toast.makeText(FormInscriptionActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }));
            }
        });
        /*btn_valider_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormInscriptionActivity.this, UtilisateurConnecte.class));
            }
        });*/

//
        ClientRepository clientRepository = new ClientRepository();

        clientRepository.query().observe(this, new Observer<List<Client>>() {
            @Override
            public void onChanged(List<Client> clients) {
                Log.i("Clients", clients.toString());
            }
        });

//

//        NE FONCTIONNE PAS... Peut-être pcq liste vide??
//
//        clientRepository
//                .create(new Client("Madame", "Nom", "Prenom", "e-mail", "tel", "motdepasse"))
//                .observe(this, new Observer<Client>() {
//                    @Override
//                    public void onChanged(Client client) {
//                        Log.i("Client", client.toString());
//                    }
//                });
    }

    public void goToFormConnexion(View view) {
        Intent intent = new Intent(FormInscriptionActivity.this,FormConnexionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION);
    }
}
