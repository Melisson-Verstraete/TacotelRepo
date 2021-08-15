package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import model.Article;
import model.CategorieArticle;
import model.Contient;
import model.Panier;
import repository.CategorieArticleRepository;
import repository.ContientRepository;
import repository.PanierRepository;

public class DetailsArticleActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_MAGASIN = 1;

    Article article;
    String libelle, description, marque, couleur;
    double ecran, memoire, prix;
    int idArticle, idUser = FormConnexionActivity.getIdUserConnected();
    PanierRepository panierRepository = new PanierRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_article);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                article = null;
                idArticle = 0;
                libelle = null;
                description = null;
                ecran = 0;
                marque = null;
                memoire = 0;
                couleur = null;
                prix = 0;
            } else {
                article = (Article) extras.get("Article");
                idArticle = extras.getInt("idArticle");
                libelle = extras.getString("libelle");
                description = extras.getString("description");
                ecran = extras.getDouble("ecran");
                marque = extras.getString("marque");
                memoire = extras.getDouble("memoire");
                couleur = extras.getString("couleur");
                prix = extras.getDouble("prix");
            }
        } else {
            article = (Article) savedInstanceState.getSerializable("Article");
            idArticle = (int) savedInstanceState.getSerializable("idArticle");
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
        tvPrix.setText("€ " + prix + " HTVA");
    }

    public void ajouterArticlePanier(View view) {
        EditText etQuantite = findViewById(R.id.et_quantite_details_result);
        String etQuantiteValue = etQuantite.getText().toString();
        int quantite = 0;
        if (etQuantiteValue.isEmpty()) {
            quantite = 1;
        } else {
            quantite = Integer.parseInt(etQuantiteValue);
        }

        // CREATION DU PANIER S'IL N'EXISTE PAS
        panierRepository.query().observe(this, new Observer<List<Panier>>() {
            @Override
            public void onChanged(List<Panier> paniersApi) {
                Log.i("paniers", paniersApi.toString());
                int existe = 0;
                for (int j=0;j<paniersApi.size();j++) {
                    if (paniersApi.get(j).getIdPanier() == idUser) {
                        existe = 1;
                    }
                }
                if (existe == 0) {
                    panierRepository.create(new Panier(idUser));
                } else {
                    existe = 0;
                }
            }
        });

        Log.i("Article à ajouter", article.toString()+idUser+quantite);
        // AJOUT DE L'ARTICLE DANS LE PANIER
        panierRepository
                .addArticle(article,idUser,quantite)
                .observe(this, new Observer<Article>() {
                    @Override
                    public void onChanged(Article articleApi) {
                        if (articleApi == null) {
                            Log.i("article ben null", "null");
                        } else {
                            Log.i("article", articleApi.toString());
                        }
                    }
                });

        // DELETE DU PANIER
//        panierRepository.delete(idUser);

        // RECUPERATION DE LA LISTE DES ARTICLES DANS LE PANIER
        panierRepository.getArticles(idUser).observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articlesApi) {
                if (articlesApi == null) {
                    Log.i("articles dans panier", "nul");
                } else {
                    Log.i("articles dans panier", articlesApi.toString());
                }
            }
        });
    }

    // REDIRECTION VERS MENU
    public void goToMenuFromDetails(View view) {
        Intent intent = new Intent(DetailsArticleActivity.this, MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    // REDIRECTION VERS MAGASIN
    public void goToMagasinFromDetails(View view) {
        Intent intent = new Intent(DetailsArticleActivity.this, MagasinActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MAGASIN);
    }
}
