package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Article;
import model.Categorie;
import repository.ArticleRepository;
import repository.CategorieRepository;

public class MagasinActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_DETAILS_ARTICLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magasin);

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
                Log.i("Object article: ", article.getLibelle());

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

        CategorieRepository categorieRepository = new CategorieRepository();

        categorieRepository.query().observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categories) {
                Log.i("Categories", categories.toString());
            }
        });
    }

    public void goToMenuFromMagasin(View view) {
        Intent intent = new Intent(MagasinActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

//    public void goToDetailsArticle(View view) {
//        Intent intent = new Intent(MagasinActivity.this,DetailsArticleActivity.class);
//        intent.putExtra("libelle", article.getLibelle());
//        startActivityForResult(intent, REQUEST_CODE_DETAILS_ARTICLE);
//    }
}
