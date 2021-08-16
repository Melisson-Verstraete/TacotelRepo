package com.helha.tacotel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.text.DecimalFormat;
import java.util.List;

import model.Article;
import model.DownloadImageTask;
import model.Panier;
import repository.ContientRepository;

public class ArticlesPanierArrayAdapter extends ArrayAdapter<Article> {
    int quantite = 0;
    double prixTotal = 0;
    int idUser = FormConnexionActivity.getIdUserConnected();

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
        for (int j=0;j<PanierActivity.contients.size();j++) {
            if (PanierActivity.contients.get(j).getArticle().getIdArticle() == article.getIdArticle()) {
                quantite = PanierActivity.contients.get(j).getQteArticleChoisi();
                prixTotal = article.getPrix() * quantite;
                PanierActivity.sousTotalStatic += prixTotal;
            }
        }

        TextView tvNomItem = view.findViewById(R.id.tv_nom_item_panier);
        TextView tvPrixUniItem = view.findViewById(R.id.tv_prix_item_panier);
        TextView tvPrixTotItem = view.findViewById(R.id.tv_prix_total_item_panier);
        TextView tvQteItem = view.findViewById(R.id.tv_qte_item_panier);
        EditText etQteItem = view.findViewById(R.id.et_qte_item_panier);

        DecimalFormat df = new DecimalFormat("#.00");

        etQteItem.setVisibility(View.GONE);
        tvNomItem.setText(article.getLibelle());
        tvPrixUniItem.setText("€ " + article.getPrix() + " HTVA");
        tvPrixTotItem.setText("€ " + df.format(prixTotal) + " HTVA");
        tvQteItem.setText("x" + quantite);


        ImageView img_supprimer = view.findViewById(R.id.img_supprimer_item_panier);
        ContientRepository contientRepository = new ContientRepository();
        img_supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Voulez-vous vraiment supprimer cet article?");
                builder.setMessage("L'article sera supprimé de votre panier.")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                contientRepository.deleteArticle(idUser, article.getIdArticle());
                                Intent intent = new Intent(getContext(),PanierActivity.class);
                                getContext().startActivity(intent);
                            }
                        })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        ImageView img_modifier = view.findViewById(R.id.img_modifier_item_panier);
        img_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Modifier",article.getLibelle());
                etQteItem.setVisibility(View.VISIBLE);
                tvQteItem.setVisibility(View.GONE);

                img_modifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int qte = Integer.parseInt(etQteItem.getText().toString());Log.i("QUANTITE", ""+qte);
                        contientRepository.update(idUser,article.getIdArticle(),qte);
                        etQteItem.setVisibility(View.GONE);
                        tvQteItem.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }
}
