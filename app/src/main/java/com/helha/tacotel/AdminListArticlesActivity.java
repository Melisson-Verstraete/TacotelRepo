package com.helha.tacotel;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.List;

import api.ApiClient;
import api.ArticleService;
import model.Article;
import repository.ArticleRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminListArticlesActivity extends AppCompatActivity {
//Lindaaaaaaaaaaaaaa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_articles);

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

        ArticleRepository articleRepository = new ArticleRepository();

        articleRepository.query().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                Log.i("Articles", articles.toString());
            }
        });

        articleRepository
                .create(new Article("libelle", "description", 22.22, 22, 13.5, "marque", "couleur", 20, "imageURL"))
                .observe(this, new Observer<Article>() {
                    @Override
                    public void onChanged(Article article) {
                        Log.i("Article", article.toString());
                    }
                });
    }
}
