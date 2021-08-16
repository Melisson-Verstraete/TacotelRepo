package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.Date;
import java.util.List;

import model.Client;
import model.Panier;
import model.Utilisateur;
import repository.ClientRepository;
import repository.PanierRepository;
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
    PanierRepository panierRepository = new PanierRepository();
    Button btn_deconnexion;
    TextView tv_nom_menu;

    int idUser = FormConnexionActivity.getIdUserConnected();

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

        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

        utilisateurRepository.query(idUser).observe(this, new Observer<Utilisateur>() {
            @Override
            public void onChanged(Utilisateur utilisateur) {
                tv_nom_menu.setText(utilisateur.getPrenom() + " " + utilisateur.getNom());
            }
        });

        utilisateurRepository.query(id).observe(this, new Observer<Utilisateur>() {
            @Override
            public void onChanged(Utilisateur utilisateur) {
                if(utilisateur != null)
                {
                    client = new Client(id,"M",utilisateur.getNom(),utilisateur.getPrenom(),new Date(),utilisateur.getMail(),"tel",utilisateur.getMdp());
                }
            }
        });

        btn_deconnexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(Users.this, For.class));
                Intent intent = new Intent(MenuActivity.this,MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MAIN);
                finish();
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

        // CREATION DU PANIER S'IL N'EXISTE PAS
        panierRepository.query().observe(this, new Observer<List<Panier>>() {
            @Override
            public void onChanged(List<Panier> paniersApi) {
                int existe = 0;
                if(paniersApi.size() == 0 ){
                    panierRepository.create(new Panier(client.getIdClient()));
                }
                else {
                    for (int j = 0; j < paniersApi.size(); j++) {
                        if (paniersApi.get(j).getIdPanier() == client.getIdClient()) {
                            existe = 1;
                        }
                    }
                    if (existe == 0) {
                        panierRepository.create(new Panier(client.getIdClient()));
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
}
