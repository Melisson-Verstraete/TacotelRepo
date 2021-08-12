package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.List;

import model.Article;
import model.Panier;
import repository.PanierRepository;

public class PanierActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_PAIEMENT_ADRESSES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);
    }

    public void goToMenuFromPanier(View view) {
        Intent intent = new Intent(PanierActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
    
    public void goToPaiementFromPanier(View view) {
        Intent intent = new Intent(PanierActivity.this,FormPaiementAdressesActivity.class);
        startActivityForResult(intent, REQUEST_CODE_PAIEMENT_ADRESSES);
    }
}
