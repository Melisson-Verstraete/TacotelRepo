package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;


import java.util.ArrayList;
import java.util.List;

import model.Article;
import repository.ArticleRepository;

public class AdminListArticlesActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FORM_ADMIN_ARTICLE = 1;

    public void addArticle(View view){
        Intent intent = new Intent(AdminListArticlesActivity.this, FormAdminArticleActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_ADMIN_ARTICLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_articles);
        Bundle bundle = getIntent().getExtras();

        List<Article> articles = new ArrayList<>();
        ListView listView = findViewById(R.id.lv_articles_admin);
        ArticlesAdminArrayAdapter articlesAdminArrayAdapter = new ArticlesAdminArrayAdapter(this, R.id.lv_articles_admin, articles);
        listView.setAdapter(articlesAdminArrayAdapter);

        ArticleRepository articleRepository = new ArticleRepository();

        articleRepository.query().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articlesApi) {
                articles.clear();
                articles.addAll(articlesApi);
                articlesAdminArrayAdapter.notifyDataSetChanged();

            }
        });
    }
}
