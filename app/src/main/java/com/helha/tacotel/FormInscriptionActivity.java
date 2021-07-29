package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FormInscriptionActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FORM_CONNEXION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_inscription);
    }

    public void goToFormConnexion(View view) {
        Intent intent = new Intent(FormInscriptionActivity.this,FormConnexionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION);
    }
}
