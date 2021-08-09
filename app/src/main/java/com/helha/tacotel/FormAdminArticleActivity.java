package com.helha.tacotel;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import model.Article;
import model.Login;
import model.Utilisateur;
import repository.ArticleRepository;

public class FormAdminArticleActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FORM_ADD_ARTICLE = 1;
    public static final String EXTRA_BUNDLE_ARTICLE = "bundle_article";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_admin_article);

        TextView tvLibelle = findViewById(R.id.et_libelle_new_article);
        TextView tvDescription = findViewById(R.id.et_description_new_article);
        TextView tvPrix = findViewById(R.id.et_prix_new_article);
        TextView tvStock = findViewById(R.id.et_stock_new_article);
        TextView tvEcran = findViewById(R.id.et_ecran_new_article);
        TextView tvMarque = findViewById(R.id.et_marque_new_article);
        TextView tvCouleur = findViewById(R.id.et_couleur_new_article);
        TextView tvMemoire = findViewById(R.id.et_memoire_new_article);
        TextView tvImageURL = findViewById(R.id.et_image_new_article);

        Bundle bundleArticle = getIntent().getExtras();
        if(bundleArticle != null){
            Article article = (Article)getIntent().getSerializableExtra(EXTRA_BUNDLE_ARTICLE);
            tvLibelle.setText(article.getLibelle());
            tvDescription.setText(article.getDescription());
            tvPrix.setText(""+article.getPrix());
            tvStock.setText(article.getQteEnStock()+"");
            tvEcran.setText(article.getTailleEcran() + "");
            tvMarque.setText(article.getMarque());
            tvCouleur.setText(article.getCouleur());
            tvMemoire.setText(article.getTailleMemoire() + "");
            tvImageURL.setText(article.getImageURL());
        }


        Button btnAdd = findViewById(R.id.btn_ajouter_new_article);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création d'une variable article
                Article article = new Article();
                article.setCouleur(tvCouleur.getText().toString());
                article.setDescription(tvDescription.getText().toString());
                article.setImageURL(tvImageURL.getText().toString());
                article.setLibelle(tvLibelle.getText().toString());
                article.setMarque(tvMarque.getText().toString());
                article.setPrix(Double.valueOf(tvPrix.getText().toString()));
                article.setQteEnStock(Integer.valueOf(tvStock.getText().toString()));
                article.setTailleEcran(Double.valueOf(tvEcran.getText().toString()));
                article.setTailleMemoire(Double.valueOf(tvMemoire.getText().toString()));

                ArticleRepository articleRepository = new ArticleRepository();

                articleRepository.create(article);
                Intent intent = new Intent(FormAdminArticleActivity.this,AdminListArticlesActivity.class);
                startActivityForResult(intent,REQUEST_CODE_FORM_ADD_ARTICLE);
            }
        });
    }
}
