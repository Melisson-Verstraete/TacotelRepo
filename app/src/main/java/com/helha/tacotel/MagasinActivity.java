package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import model.Article;
import model.Categorie;
import model.CategorieArticle;
import repository.ArticleRepository;
import repository.CategorieArticleRepository;
import repository.CategorieRepository;

public class MagasinActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_DETAILS_ARTICLE = 1;

    List<Categorie> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magasin);

        afficherCategories();
    }

    // CATEGORIES
    private void afficherCategories() {
        List<String> nomCategories = new ArrayList<>();

        CategorieRepository categorieRepository = new CategorieRepository();

        Spinner spinner = findViewById(R.id.spinner_categories_magasin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        categorieRepository.query().observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categoriesApi) {
                categories.addAll(categoriesApi);
                nomCategories.clear();
                nomCategories.add("TOUT");
                for (int j=0;j<categoriesApi.size();j++) {
                    nomCategories.add(categoriesApi.get(j).getNomCategorie());
                }
                adapter.notifyDataSetChanged();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                afficherArticles(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // ARTICLES
    private void afficherArticles(String categorieChoisie) {
        // CATEGORIEARTICLES
        List<CategorieArticle> categorieArticles = new ArrayList<>();
        CategorieArticleRepository categorieArticleRepository = new CategorieArticleRepository();
        categorieArticleRepository.query().observe(this, new Observer<List<CategorieArticle>>() {
            @Override
            public void onChanged(List<CategorieArticle> categorieArticlesApi) {
                categorieArticles.addAll(categorieArticlesApi);
            }
        });

        // ARTICLES
        List<Article> articles = new ArrayList<>();
        ListView listView = findViewById(R.id.lv_articles);
        ArticlesMagasinArrayAdapter articlesMagasinArrayAdapter = new ArticlesMagasinArrayAdapter(this, R.id.lv_articles, articles);
        listView.setAdapter(articlesMagasinArrayAdapter);

        ArticleRepository articleRepository = new ArticleRepository();

        articleRepository.query().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articlesApi) {
                articles.clear();
                if (categorieChoisie.equals("TOUT")) {
                    articles.addAll(articlesApi);
                } else {
                    for (int m=0 ; m<categories.size() ; m++) {
                        if (categorieChoisie.equals(categories.get(m).getNomCategorie())) {
                            Log.i("categorieChoisie", categories.get(m).getNomCategorie());
                            for (int n = 0; n < articlesApi.size(); n++) {
                                for (int j = 0; j < categorieArticles.size(); j++) {
                                    if (categorieArticles.get(j).getIdCategorie() == categories.get(m).getIdCategorie()
                                            && categorieArticles.get(j).getIdArticle() == articlesApi.get(n).getIdArticle()) {
                                        articles.add(articlesApi.get(n));
                                    }
                                }
                            }
                        }
                    }
                }
                articlesMagasinArrayAdapter.notifyDataSetChanged();

                // RECHERCHER
                EditText editText = findViewById(R.id.et_rechercher_magasin);
                editText.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int id, KeyEvent keyEvent) {
                        if (editText.getText().toString().isEmpty()) {
                            articles.clear();
                            articles.addAll(articlesApi);
                            articlesMagasinArrayAdapter.notifyDataSetChanged();
                        } else {
                            articles.clear();
                            for (int j = 0; j < articlesApi.size(); j++) {
                                if (articlesApi.get(j).getLibelle().contains(editText.getText().toString())) {
                                    articles.add(articlesApi.get(j));
                                }
                            }
                            articlesMagasinArrayAdapter.notifyDataSetChanged();
                        }
                        return false;
                    }
                });
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object object = adapterView.getItemAtPosition(i);
                Article article = (Article) object;

                Intent intent = new Intent(MagasinActivity.this,DetailsArticleActivity.class);
                intent.putExtra("Article",article);
                intent.putExtra("idArticle", article.getIdArticle());
                intent.putExtra("libelle", article.getLibelle());
                intent.putExtra("description", article.getDescription());
                intent.putExtra("ecran", article.getTailleEcran());
                intent.putExtra("marque", article.getMarque());
                intent.putExtra("memoire", article.getTailleMemoire());
                intent.putExtra("couleur", article.getCouleur());
                intent.putExtra("prix", article.getPrix());
                intent.putExtra("imageURL", article.getImageURL());
                startActivityForResult(intent, REQUEST_CODE_DETAILS_ARTICLE);
            }
        });
    }

    // REDIRECTION VERS MENU
    public void goToMenuFromMagasin(View view) {
        Intent intent = new Intent(MagasinActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
}
