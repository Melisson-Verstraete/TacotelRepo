package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import model.Article;
import model.Contient;
import model.Panier;
import repository.ContientRepository;
import repository.PanierRepository;

public class PanierActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_PAIEMENT_ADRESSES = 1;

    int idUser = FormConnexionActivity.getIdUserConnected();
    static List<Contient> contients = new ArrayList<>();
    static double sousTotalStatic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        List<Article> articles = new ArrayList<>();
        ListView listView = findViewById(R.id.lv_articles_panier);
        ArticlesPanierArrayAdapter articlesPanierArrayAdapter = new ArticlesPanierArrayAdapter(this, R.id.lv_articles_panier, articles);

        listView.setAdapter(articlesPanierArrayAdapter);

        PanierRepository panierRepository = new PanierRepository();

        // RECUPERATION DE LA LISTE DES ARTICLES DANS LE PANIER
        panierRepository.getArticles(idUser).observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articlesApi) {
                articles.clear();
                articles.addAll(articlesApi);
                articlesPanierArrayAdapter.notifyDataSetChanged();
            }
        });

        ContientRepository contientRepository = new ContientRepository();

        contientRepository.query(idUser).observe(this, new Observer<List<Contient>>() {
            @Override
            public void onChanged(List<Contient> contientsApi) {
                contients.addAll(contientsApi);
            }
        });

        TextView tvSousTotal = findViewById(R.id.tv_sous_tot_panier_result);
        tvSousTotal.setText("€ " + sousTotalStatic);
    }

    public void goToMenuFromPanier(View view) {
        Intent intent = new Intent(PanierActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
    
    public void goToPaiementFromPanier(View view) {
        Intent intent = new Intent(PanierActivity.this,FormPaiementAdressesActivity.class);
        startActivityForResult(intent, REQUEST_CODE_PAIEMENT_ADRESSES);
    }
}
