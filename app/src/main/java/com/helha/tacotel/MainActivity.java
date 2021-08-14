package com.helha.tacotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FORM_CONNEXION = 1;
    public static final int REQUEST_CODE_FORM_INSCRIPTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToFormConnexion(View view) {
        Intent intent = new Intent(MainActivity.this,FormConnexionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION);
    }

    public void goToFormInscription(View view) {
        Intent intent = new Intent(MainActivity.this, FormInscriptionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_INSCRIPTION);
    }
}