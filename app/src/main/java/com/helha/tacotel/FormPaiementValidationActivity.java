package com.helha.tacotel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import model.Article;
import model.Contient;
import repository.ArticleRepository;
import repository.ContientRepository;
import repository.PanierRepository;

import static com.helha.tacotel.FormPaiementAdressesActivity.NOM1_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.NOM2_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.PAYS1_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.PAYS2_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.RUE1_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.RUE2_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.SHARED_PREFS;
import static com.helha.tacotel.FormPaiementBanqueActivity.NUM_COMPTE;
import static com.helha.tacotel.FormPaiementBanqueActivity.SHARED_PREFS2;
import static com.helha.tacotel.FormPaiementBanqueActivity.TITULAIRE_COMPTE;
import static com.helha.tacotel.FormPaiementBanqueActivity.TYPE_CARTE;


public class FormPaiementValidationActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_FORM_BANQUE = 1;

    int idUser = FormConnexionActivity.getIdUserConnected();
    static List<Contient> contients = new ArrayList<>();

    ContientRepository contientRepository = new ContientRepository();

    private TextView tv_nom1_validation ;
    private TextView tv_adresse1_validation ;
    private TextView tv_pays1_validation ;
    private TextView tv_nom2_validation ;
    private TextView tv_adresse2_validation ;
    private TextView tv_pays2_validation ;
    private TextView tv_mode_validation ;
    private TextView tv_titulaire_compte_validation ;
    private TextView tv_num_compte_validation ;

    private Button btn_payer_validation ;
    private Button btn_retour_validation ;
    static double sousTotalStatic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_paiement_validation);

        afficherArticlesPanier();

        btn_payer_validation = (Button) findViewById(R.id.btn_payer_validation);
        btn_retour_validation = (Button) findViewById(R.id.btn_retour_validation);

        tv_nom1_validation = (TextView) findViewById(R.id.tv_nom1_validation) ;
        tv_adresse1_validation = (TextView) findViewById(R.id.tv_adresse1_validation) ;
        tv_pays1_validation = (TextView) findViewById(R.id.tv_pays1_validation) ;
        tv_nom2_validation = (TextView) findViewById(R.id.tv_nom2_validation) ;
        tv_adresse2_validation = (TextView) findViewById(R.id.tv_adresse2_validation) ;
        tv_pays2_validation = (TextView) findViewById(R.id.tv_pays2_validation) ;
        tv_mode_validation = (TextView) findViewById(R.id.tv_mode_validation) ;
        tv_titulaire_compte_validation = (TextView) findViewById(R.id.tv_titulaire_compte_validation) ;
        tv_num_compte_validation = (TextView) findViewById(R.id.tv_num_compte_validation) ;

        SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences sharedpreferences2 = getSharedPreferences(SHARED_PREFS2, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(NOM1_ADRESSES)) {
            tv_nom1_validation.setText(sharedpreferences.getString(NOM1_ADRESSES, ""));
        }
        if (sharedpreferences.contains(RUE1_ADRESSES)) {
            tv_adresse1_validation.setText(sharedpreferences.getString(RUE1_ADRESSES, ""));
        }
        if (sharedpreferences.contains(PAYS1_ADRESSES)) {
            tv_pays1_validation.setText(sharedpreferences.getString(PAYS1_ADRESSES, ""));
        }
        if (sharedpreferences.contains(NOM2_ADRESSES)) {
            tv_nom2_validation.setText(sharedpreferences.getString(NOM2_ADRESSES, ""));
        }
        if (sharedpreferences.contains(RUE2_ADRESSES)) {
            tv_adresse2_validation.setText(sharedpreferences.getString(RUE2_ADRESSES, ""));
        }
        if (sharedpreferences.contains(PAYS2_ADRESSES)) {
            tv_pays2_validation.setText(sharedpreferences.getString(PAYS2_ADRESSES, ""));
        }
        if (sharedpreferences2.contains(TYPE_CARTE)) {
            tv_mode_validation.setText(sharedpreferences2.getString(TYPE_CARTE, ""));
        }
        if (sharedpreferences2.contains(TITULAIRE_COMPTE)) {
            tv_titulaire_compte_validation.setText(sharedpreferences2.getString(TITULAIRE_COMPTE, ""));
        }
        if (sharedpreferences2.contains(NUM_COMPTE)) {
            tv_num_compte_validation.setText(sharedpreferences2.getString(NUM_COMPTE, ""));
        }
    }

    public void afficherArticlesPanier() {
        TextView tvSousTotal = findViewById(R.id.tv_sous_total_result_validation);
        TextView tvTVA = findViewById(R.id.tv_tva_result_validation);
        TextView tvTotal = findViewById(R.id.tv_total_result_validation);


        List<Article> articles = new ArrayList<>();
        ListView listView = findViewById(R.id.lv_articles_validation);
        ArticlesPaiementArrayAdapter articlesPaiementArrayAdapter = new ArticlesPaiementArrayAdapter(this, R.id.lv_articles_validation, articles);

        listView.setAdapter(articlesPaiementArrayAdapter);

        PanierRepository panierRepository = new PanierRepository();

        // RECUPERATION DE LA LISTE DES ARTICLES DANS LE PANIER
        panierRepository.getArticles(idUser).observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articlesApi) {
                articles.clear();
                articles.addAll(articlesApi);
                articlesPaiementArrayAdapter.notifyDataSetChanged();
            }
        });

        ContientRepository contientRepository = new ContientRepository();

        contientRepository.query(idUser).observe(this, new Observer<List<Contient>>() {
            @Override
            public void onChanged(List<Contient> contientsApi) {
                if(contientsApi != null){
                    contients.clear();
                    contients.addAll(contientsApi);
                    for (Contient contient:contients) {
                        sousTotalStatic +=contient.getQteArticleChoisi()*contient.getArticle().getPrix();
                    }
                    DecimalFormat df = new DecimalFormat("#.00");
                    tvSousTotal.setText("??? " + df.format(sousTotalStatic));
                    double tva = sousTotalStatic * 0.79;
                    tvTVA.setText("??? " + df.format(tva));
                    double total = sousTotalStatic + tva;
                    tvTotal.setText("??? " + df.format(total));
                }
            }
        });
    }

    public void goToMenuFromPaiementValidation(View view) {
        modifierQteEnStockArticle();

        Intent intent = new Intent(FormPaiementValidationActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    public void goToPaiementBanqueFromPaiementValidation(View view) {
        Intent intent = new Intent(FormPaiementValidationActivity.this,FormPaiementBanqueActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_BANQUE);
    }

    public void modifierQteEnStockArticle() {
        ArticleRepository articleRepository = new ArticleRepository();

        articleRepository.query().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articlesApi) {
                contientRepository.query(idUser).observe(FormPaiementValidationActivity.this, new Observer<List<Contient>>() {
                    @Override
                    public void onChanged(List<Contient> contientsApi) {
                        for (int i = 0; i < articlesApi.size(); i++) {
                            for (int j = 0; j < contientsApi.size(); j++) {
                                if (articlesApi.get(i).getIdArticle() == contientsApi.get(j).getArticle().getIdArticle()) {
                                    int nouvelleQuantite = articlesApi.get(i).getQteEnStock() - contientsApi.get(j).getQteArticleChoisi();
                                    articlesApi.get(i).setQteEnStock(nouvelleQuantite);
                                    Log.i("QUANTITE", ""+articlesApi.get(i).getQteEnStock());
                                    Log.i("QUANTITEnouvelle", ""+nouvelleQuantite);
                                    articleRepository.update(articlesApi.get(i).getIdArticle(), articlesApi.get(i));
                                }
                            }
                        }
                        // DELETE DU PANIER
                        PanierRepository panierRepository = new PanierRepository();
                        contientRepository.delete(idUser);
                        panierRepository.delete(idUser);
                    }
                });
            }
        });
    }
}
