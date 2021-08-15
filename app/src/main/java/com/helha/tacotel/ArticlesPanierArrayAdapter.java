package com.helha.tacotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import model.Article;

public class ArticlesPanierArrayAdapter extends ArrayAdapter<Article> {
    public ArticlesPanierArrayAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.list_item_articles_panier, parent, false);
        }

        final Article article = getItem(position);
        populateView(convertView, article);

        return convertView;
    }

    private void populateView(View view, Article article) {
        ImageView imgItem = view.findViewById(R.id.img_item_panier);
        TextView tvNomItem = view.findViewById(R.id.tv_nom_item_panier);
        TextView tvPrixUniItem = view.findViewById(R.id.tv_prix_item_panier);
        TextView tvPrixTotItem = view.findViewById(R.id.tv_prix_total_item_panier);
        TextView tvQteItem = view.findViewById(R.id.tv_qte_item_panier);

        tvNomItem.setText(article.getLibelle());
        tvPrixUniItem.setText("€ " + article.getPrix());
        tvPrixTotItem.setText("€ " + "ok");
        tvQteItem.setText("" + "ok");
    }
}
