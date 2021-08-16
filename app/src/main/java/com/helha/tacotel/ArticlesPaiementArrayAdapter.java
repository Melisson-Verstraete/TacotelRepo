package com.helha.tacotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.util.List;

import model.Article;
import model.DownloadImageTask;

public class ArticlesPaiementArrayAdapter extends ArrayAdapter<Article> {
    int quantite = 0;
    double prixTotal = 0;

    public ArticlesPaiementArrayAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
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
        for (int j=0;j<FormPaiementValidationActivity.contients.size();j++) {
            if (FormPaiementValidationActivity.contients.get(j).getArticle().getIdArticle() == article.getIdArticle()) {
                quantite = FormPaiementValidationActivity.contients.get(j).getQteArticleChoisi();
                prixTotal = article.getPrix() * quantite;
            }
        }

        ImageView imgItem = view.findViewById(R.id.img_item_panier);
        TextView tvNomItem = view.findViewById(R.id.tv_nom_item_panier);
        TextView tvPrixUniItem = view.findViewById(R.id.tv_prix_item_panier);
        TextView tvPrixTotItem = view.findViewById(R.id.tv_prix_total_item_panier);
        TextView tvQteItem = view.findViewById(R.id.tv_qte_item_panier);

        DecimalFormat df = new DecimalFormat("#.00");

        tvNomItem.setText(article.getLibelle());
        tvPrixUniItem.setText("€ " + article.getPrix() + " HTVA");
        tvPrixTotItem.setText("€ " + df.format(prixTotal) + " HTVA");
        tvQteItem.setText("x" + quantite);

        // CACHER LES BOUTONS
        EditText etQteItem = view.findViewById(R.id.et_qte_item_panier);
        etQteItem.setVisibility(View.GONE);
        ImageView img_supprimer = view.findViewById(R.id.img_supprimer_item_panier);
        img_supprimer.setVisibility(View.GONE);
        ImageView img_modifier = view.findViewById(R.id.img_modifier_item_panier);
        img_modifier.setVisibility(View.GONE);

        if(article.getImageURL() != null) {
            new DownloadImageTask(imgItem)
                    .execute(article.getImageURL());
        }
    }
}