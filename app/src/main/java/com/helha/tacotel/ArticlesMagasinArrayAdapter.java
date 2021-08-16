package com.helha.tacotel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import model.Article;
import model.DownloadImageTask;

public class ArticlesMagasinArrayAdapter extends ArrayAdapter<Article> {
    public ArticlesMagasinArrayAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.list_item_articles_magasin, parent, false);
        }

        final Article article = getItem(position);
        populateView(convertView, article);

        return convertView;
    }

    private void populateView(View view, Article article) {
        ImageView imgItem = view.findViewById(R.id.img_item);
        TextView tvNomItem = view.findViewById(R.id.tv_nom_item);
        TextView tvDescriptionItem = view.findViewById(R.id.tv_description_item);
        TextView tvPrixItem = view.findViewById(R.id.tv_prix_item);

        tvNomItem.setText(article.getLibelle());
        tvDescriptionItem.setText(article.getDescription());
        tvPrixItem.setText("€ " + article.getPrix());

        if(article.getImageURL() != null)
            new DownloadImageTask(imgItem)
                    .execute(article.getImageURL());
    }
}
