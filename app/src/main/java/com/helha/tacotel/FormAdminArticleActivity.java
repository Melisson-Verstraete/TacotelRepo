package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import model.Article;
import model.Categorie;
import repository.ArticleRepository;
import repository.CategorieRepository;

public class FormAdminArticleActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FORM_ADD_ARTICLE = 1;
    public static final String EXTRA_BUNDLE_ARTICLE = "bundle_article";
    private Article article = new Article();
    private String nameCategorie = new String();

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

        //Remplir le spinner de données
        CategorieRepository categorieRepository = new CategorieRepository();
        List<Categorie>categories = new ArrayList<>();
        List<String> nameCategories = new ArrayList<>();
        Button btnAdd = findViewById(R.id.btn_ajouter_new_article);

        Spinner spCategorie = (Spinner) findViewById(R.id.spinner_categories_new_article);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                nameCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategorie.setAdapter(adapter);

        categorieRepository.query().observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categoriesApi) {
                categories.clear();
                categories.addAll(categoriesApi);
                for(int i=0;i<categories.size();i++){
                    nameCategories.add(categories.get(i).getNomCategorie());
                }
                adapter.notifyDataSetChanged();
            }
        });

        Bundle bundleArticle = getIntent().getExtras();
        if(bundleArticle != null){
            article = (Article)getIntent().getSerializableExtra(EXTRA_BUNDLE_ARTICLE);
            tvLibelle.setText(article.getLibelle());
            tvDescription.setText(article.getDescription());
            tvPrix.setText(""+article.getPrix());
            tvStock.setText(article.getQteEnStock()+"");
            tvEcran.setText(article.getTailleEcran() + "");
            tvMarque.setText(article.getMarque());
            tvCouleur.setText(article.getCouleur());
            tvMemoire.setText(article.getTailleMemoire() + "");
            tvImageURL.setText(article.getImageURL());
            TextView tvTitle = findViewById(R.id.tv_article_admin);
            tvTitle.setText("Modification Article");
            btnAdd.setText("Modifier");


            spCategorie.setSelection(adapter.getPosition(article.getCategorie().getNomCategorie()));
            nameCategorie = article.getCategorie().getNomCategorie();
            adapter.notifyDataSetChanged();
        }



        spCategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spCategorie.setSelection(adapter.getPosition(adapter.getItem(position)));
                nameCategorie = adapter.getItem(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleRepository articleRepository = new ArticleRepository();

                article.setCouleur(tvCouleur.getText().toString());
                article.setDescription(tvDescription.getText().toString());
                article.setImageURL(tvImageURL.getText().toString());
                article.setLibelle(tvLibelle.getText().toString());
                article.setMarque(tvMarque.getText().toString());
                article.setPrix(Double.valueOf(tvPrix.getText().toString()));
                article.setQteEnStock(Integer.valueOf(tvStock.getText().toString()));
                article.setTailleEcran(Double.valueOf(tvEcran.getText().toString()));
                article.setTailleMemoire(Double.valueOf(tvMemoire.getText().toString()));
                for(int i=0;i<categories.size();i++){
                    if(categories.get(i).getNomCategorie().equals(nameCategorie)){

                        article.setCategorie(categories.get(i));
                    }
                }

                if(btnAdd.getText() == "Modifier"){

                    articleRepository.update(article.getIdArticle(),article);
                }
                else{

                    articleRepository.create(article);
                    articleRepository.setCategorie(article.getIdArticle(),article.getCategorie().getIdCategorie());
                }

                Intent intent = new Intent(FormAdminArticleActivity.this,AdminListArticlesActivity.class);
                startActivityForResult(intent,REQUEST_CODE_FORM_ADD_ARTICLE);
            }
        });
    }
}
