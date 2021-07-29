package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MAGASIN = 1;
    private static final int REQUEST_CODE_PANIER = 1;
    private static final int REQUEST_CODE_DETAILS_ARTICLE = 1;
    private static final int REQUEST_CODE_FORM_PAIEMENT_ADRESSES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void goToMagasin(View view) {
        Intent intent = new Intent(MenuActivity.this,MagasinActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MAGASIN);
    }

    public void goToPanier(View view) {
        Intent intent = new Intent(MenuActivity.this,PanierActivity.class);
        startActivityForResult(intent, REQUEST_CODE_PANIER);
    }

    public void goToPaiement(View view) {
        Intent intent = new Intent(MenuActivity.this,FormPaiementAdressesActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_PAIEMENT_ADRESSES);
    }

    public void goToDetails(View view) {
        Intent intent = new Intent(MenuActivity.this,DetailsArticleActivity.class);
        startActivityForResult(intent, REQUEST_CODE_DETAILS_ARTICLE);
    }

//    public void goToDetails(View view) {
//        Intent intent = new Intent(MenuActivity.this,FormAdminArticleActivity.class);
//        startActivityForResult(intent, REQUEST_CODE_DETAILS_ARTICLE);
//    }
}
