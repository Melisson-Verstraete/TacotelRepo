package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class MagasinActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_DETAILS_ARTICLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magasin);

        afficherCategories();
    }

    // CATEGORIES
    private void afficherCategories() {
        List<String> categories = new ArrayList<>();

        CategorieRepository categorieRepository = new CategorieRepository();

        Spinner spinner = findViewById(R.id.spinner_categories_magasin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        categorieRepository.query().observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categoriesApi) {
                categories.clear();
                categories.add("TOUT");
                for (int j=0;j<categoriesApi.size();j++) {
                    categories.add(categoriesApi.get(j).getNomCategorie());
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
                } else if (categorieChoisie.equals("Tablette")){
                    for (int j = 0; j < articlesApi.size(); j++) {
                        if (articlesApi.get(j).getCategorie().toString() == "1") {
                            articles.add(articlesApi.get(j));
                        }
                    }
                } else if (categorieChoisie.equals("Ordinateur")){
                    for (int j = 0; j < articlesApi.size(); j++) {
                        if (articlesApi.get(j).getCategorie().toString() == "2") {
                            articles.add(articlesApi.get(j));
                        }
                    }
                } else if (categorieChoisie.equals("Téléphone")){
                    for (int j = 0; j < articlesApi.size(); j++) {
                        if (articlesApi.get(j).getCategorie().toString() == "3") {
                            articles.add(articlesApi.get(j));
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

    // REDIRECTION VERS MENU
    public void goToMenuFromMagasin(View view) {
        Intent intent = new Intent(MagasinActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Log.i("essai", String.valueOf(parent.getItemAtPosition(pos)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
