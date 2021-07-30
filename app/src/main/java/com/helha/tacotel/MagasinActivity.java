package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import model.Article;
import model.Categorie;
import repository.ArticleRepository;
import repository.CategorieRepository;

public class MagasinActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;

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
}
