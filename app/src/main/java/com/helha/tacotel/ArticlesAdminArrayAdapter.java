package com.helha.tacotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import model.Article;

public class ArticlesAdminArrayAdapter extends ArrayAdapter<Article> {
    public ArticlesAdminArrayAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.list_item_articles_admin, parent, false);
        }

        final Article article = getItem(position);
        populateView(convertView, article);

        return convertView;
    }

    private void populateView(View view, Article article) {
        TextView tvId = view.findViewById(R.id.tv_id_article_admin);
        TextView tvLibelle = view.findViewById(R.id.tv_libelle_article_admin);
        TextView tvDescription = view.findViewById(R.id.tv_description_article_admin);
        TextView tvPrix = view.findViewById(R.id.tv_prix_article_admin);
        TextView tvStock = view.findViewById(R.id.tv_stock_result_article_admin);
        TextView tvEcran = view.findViewById(R.id.tv_ecran_result_article_admin);
        TextView tvMarque = view.findViewById(R.id.tv_marque_result_article_admin);
        TextView tvCouleur = view.findViewById(R.id.tv_couleur_result_article_admin);
        TextView tvMemoire = view.findViewById(R.id.tv_memoire_result_article_admin);
        TextView tvImageURL = view.findViewById(R.id.tv_image_article_admin);

        tvId.setText(article.getIdArticle() + "");
        tvLibelle.setText(article.getLibelle());
        tvDescription.setText(article.getDescription());
        tvPrix.setText("€ " + article.getPrix());
        tvStock.setText(article.getQteEnStock() + "");
        tvEcran.setText(article.getTailleEcran() + "\"");
        tvMarque.setText(article.getMarque());
        tvCouleur.setText(article.getCouleur());
        tvMemoire.setText(article.getTailleMemoire() + " GB");
        tvImageURL.setText(article.getImageURL());
    }
}
