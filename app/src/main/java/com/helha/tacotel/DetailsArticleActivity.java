package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import model.Article;

public class DetailsArticleActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_MAGASIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_article);

        String libelle;
        String description;
        double ecran;
        String marque;
        double memoire;
        String couleur;
        double prix;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                libelle = null; description = null;
                ecran = 0; marque = null;
                memoire = 0; couleur = null; prix = 0;
            } else {
                libelle = extras.getString("libelle");
                description = extras.getString("description");
                ecran = extras.getDouble("ecran");
                marque = extras.getString("marque");
                memoire = extras.getDouble("memoire");
                couleur = extras.getString("couleur");
                prix = extras.getDouble("prix");
            }
        } else {
            libelle = (String) savedInstanceState.getSerializable("libelle");
            description = (String) savedInstanceState.getSerializable("description");
            ecran = (double) savedInstanceState.getSerializable("ecran");
            marque = (String) savedInstanceState.getSerializable("marque");
            memoire = (double) savedInstanceState.getSerializable("memoire");
            couleur = (String) savedInstanceState.getSerializable("couleur");
            prix = (double) savedInstanceState.getSerializable("prix");
        }

        TextView tvLibelle = findViewById(R.id.tv_libelle_details);
        TextView tvDescription = findViewById(R.id.tv_description_details);
        TextView tvEcran = findViewById(R.id.tv_taille_ecran_details_result);
        TextView tvMarque = findViewById(R.id.tv_marque_details_result);
        TextView tvMemoire = findViewById(R.id.tv_memoire_details_result);
        TextView tvCouleur = findViewById(R.id.tv_couleur_details_result);
        TextView tvPrix = findViewById(R.id.tv_prix_details_result);

        tvLibelle.setText(libelle);
        tvDescription.setText(description);
        tvEcran.setText(ecran + "\"");
        tvMarque.setText(marque);
        tvMemoire.setText(memoire + " GB");
        tvCouleur.setText(couleur);
        tvPrix.setText("€ " + prix);
    }

    public void goToMenuFromDetails(View view) {
        Intent intent = new Intent(DetailsArticleActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
    
    public void goToMagasinFromDetails(View view) {
        Intent intent = new Intent(DetailsArticleActivity.this,MagasinActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MAGASIN);
    }
}
