package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
//        ArticleService articleService = ApiClient.getClient().create(ArticleService.class);
//
//        articleService.getArticles().enqueue(new Callback<List<Article>>() {
//            @Override
//            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
//                Log.i("Articles", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<Article>> call, Throwable t) {
//
//            }
//        });

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



//        Mis en commentaires pcq sinon ça va créer un nouvel article à chaque fois
//
//        articleRepository
//                .create(new Article("libelle", "description", 22.22, 22, 13.5, "marque", "couleur", 20, "imageURL"))
//                .observe(this, new Observer<Article>() {
//                    @Override
//                    public void onChanged(Article article) {
//                        Log.i("Article", article.toString());
//                    }
//                });
    }
}
