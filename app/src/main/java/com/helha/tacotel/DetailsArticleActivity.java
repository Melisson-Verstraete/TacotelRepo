package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsArticleActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_MAGASIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_article);
    }

    public void goToMenuFromDetails(View view) {
        Intent intent = new Intent(DetailsArticleActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
    
    public void goToMagasinFromDetails(View view) {
        Intent intent = new Intent(DetailsArticleActivity.this,MagasinActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MAGASIN);
    }
}
