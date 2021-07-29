package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FormConnexionAdminActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADMIN_LIST_ARTICLES = 1;
    private static final int REQUEST_CODE_FORM_CONNEXION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_connexion_admin);
    }

    public void goToAdminArticles(View view) {
        Intent intent = new Intent(FormConnexionAdminActivity.this,AdminListArticlesActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADMIN_LIST_ARTICLES);
    }

    public void goToConnexionFromConnexionAdmin(View view) {
        Intent intent = new Intent(FormConnexionAdminActivity.this,FormConnexionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION);
    }
}
