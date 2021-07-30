package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.List;

import model.Client;
import repository.ClientRepository;

public class FormInscriptionActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FORM_CONNEXION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_inscription);

        ClientRepository clientRepository = new ClientRepository();

        clientRepository.query().observe(this, new Observer<List<Client>>() {
            @Override
            public void onChanged(List<Client> clients) {
                Log.i("Clients", clients.toString());
            }
        });

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
