package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.Date;
import java.util.List;

import api.UtilisateurConnecte;
import model.Client;
import model.Login;
import model.Utilisateur;
import repository.ClientRepository;
import repository.UtilisateurRepository;

public class MenuActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MAGASIN = 1;
    private static final int REQUEST_CODE_PANIER = 1;
    private static final int REQUEST_CODE_DETAILS_ARTICLE = 1;
    private static final int REQUEST_CODE_FORM_PAIEMENT_ADRESSES = 1;
    private static final int REQUEST_CODE_MAIN = 1;
    int id = 0;
    Client client = null;
    ClientRepository clientRepository = new ClientRepository();
    Button btn_deconnexion;
    TextView tv_nom_menu;
    //EditText et_pseudo_connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_deconnexion = (Button)findViewById(R.id.btn_deconnexion);
        tv_nom_menu = (TextView)findViewById(R.id.tv_nom_menu);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
             id = bundle.getInt("pseudo");

        }

        btn_deconnexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(Users.this, For.class));
                Intent intent = new Intent(MenuActivity.this,MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MAIN);
                tv_nom_menu.setText(tv_nom_menu.getText());
                finish();
            }
        });
        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

        utilisateurRepository.query(id).observe(this, new Observer<Utilisateur>() {
            @Override
            public void onChanged(Utilisateur utilisateur) {
                if(utilisateur != null)
                {
                    tv_nom_menu.setText(utilisateur.getPseudo());
                    client = new Client(id,"M",utilisateur.getNom(),utilisateur.getPrenom(),new Date(),utilisateur.getMail(),"tel",utilisateur.getMdp());
                }else{
                    }

            }
        });

        clientRepository.query().observe(this, new Observer<List<Client>>() {
            @Override
            public void onChanged(List<Client> clients) {
                int existe = 0;
                if(clients.size()==0){
                    clientRepository.create(client);
                }
                else{
                    for (Client clientFound:clients
                         ) {
                        if(clientFound.getIdClient() == client.getIdClient()){
                            existe = 1;
                        }
                    }
                    if(existe == 0){
                        clientRepository.create(client);
                    }
                }
            }
        });

    }

    public void goToMagasin(View view) {
        Intent intent = new Intent(MenuActivity.this,MagasinActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MAGASIN);
    }

    public void goToPanier(View view) {
        Intent intent = new Intent(MenuActivity.this,PanierActivity.class);
        startActivityForResult(intent, REQUEST_CODE_PANIER);
    }

    public void goToPaiement(View view) {
        Intent intent = new Intent(MenuActivity.this,FormPaiementAdressesActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_PAIEMENT_ADRESSES);
    }

    public void goToDetails(View view) {
        Intent intent = new Intent(MenuActivity.this,DetailsArticleActivity.class);
        startActivityForResult(intent, REQUEST_CODE_DETAILS_ARTICLE);
    }

//    public void goToDetails(View view) {
//        Intent intent = new Intent(MenuActivity.this,FormAdminArticleActivity.class);
//        startActivityForResult(intent, REQUEST_CODE_DETAILS_ARTICLE);
//    }

}
