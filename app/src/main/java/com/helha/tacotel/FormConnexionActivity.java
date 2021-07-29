package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FormConnexionActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FORM_INSCRIPTION = 1;
    public static final int REQUEST_CODE_FORM_CONNEXION_ADMIN = 1;
    private static final int REQUEST_CODE_FORM_CONNEXION_MENU = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_connexion);
    }

    public void goToFormInscription2(View view) {
        Intent intent = new Intent(FormConnexionActivity.this, FormInscriptionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_INSCRIPTION);
    }

    public void goToFormConnexionAdmin(View view) {
        Intent intent = new Intent(FormConnexionActivity.this, FormConnexionAdminActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION_ADMIN);
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(FormConnexionActivity.this, MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION_MENU);
    }
}
