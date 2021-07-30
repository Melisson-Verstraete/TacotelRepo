package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.List;

import model.Categorie;
import repository.CategorieRepository;

public class MagasinActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magasin);

        CategorieRepository categorieRepository = new CategorieRepository();

        categorieRepository.query().observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categories) {
                Log.i("Categories", categories.toString());
            }
        });
    }

    public void goToMenuFromMagasin(View view) {
        Intent intent = new Intent(MagasinActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
}
