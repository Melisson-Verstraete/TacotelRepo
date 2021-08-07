package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import api.ApiClient;
import api.CategorieService;
import model.Article;
import model.Categorie;
import repository.ArticleRepository;
import repository.CategorieRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MagasinActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_DETAILS_ARTICLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magasin);

        // CATEGORIES
        List<Categorie> categories = new ArrayList<>();
        List<String> categoriesString = new ArrayList<>();

        CategorieRepository categorieRepository = new CategorieRepository();

        categorieRepository.query().observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categoriesApi) {
                Log.i("Categories", categories.toString());
                categories.addAll(categoriesApi);

                categoriesString.add(categories.toString());
                String nom = categories.get(0).getNomCategorie();
                Log.i("CategoriesBOUCLE", nom);
            }
        });
//        for (int j=0;j<=categories.size();j++) {
//            String nom2 = categories.get(j).getNomCategorie();
//            Log.i("CategoriesBOUCLE2", nom2);
//        }
        Spinner spinner = findViewById(R.id.spinner_categories_magasin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // ARTICLES
        List<Article> articles = new ArrayList<>();
        ListView listView = findViewById(R.id.lv_articles);
        ArticlesMagasinArrayAdapter articlesMagasinArrayAdapter = new ArticlesMagasinArrayAdapter(this, R.id.lv_articles, articles);
        listView.setAdapter(articlesMagasinArrayAdapter);

        ArticleRepository articleRepository = new ArticleRepository();

        articleRepository.query().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articlesApi) {
                Log.i("Articles", articles.toString());
                articles.clear();
                articles.addAll(articlesApi);
                articlesMagasinArrayAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object object = adapterView.getItemAtPosition(i);
                Article article = (Article) object;

                Intent intent = new Intent(MagasinActivity.this,DetailsArticleActivity.class);
                intent.putExtra("libelle", article.getLibelle());
                intent.putExtra("description", article.getDescription());
                intent.putExtra("ecran", article.getTailleEcran());
                intent.putExtra("marque", article.getMarque());
                intent.putExtra("memoire", article.getTailleMemoire());
                intent.putExtra("couleur", article.getCouleur());
                intent.putExtra("prix", article.getPrix());
                startActivityForResult(intent, REQUEST_CODE_DETAILS_ARTICLE);
            }
        });
    }

    public void goToMenuFromMagasin(View view) {
        Intent intent = new Intent(MagasinActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
}
