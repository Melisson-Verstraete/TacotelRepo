package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FormPaiementValidationActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_FORM_BANQUE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_paiement_validation);
    }

    public void goToMenuFromPaiementValidation(View view) {
        Intent intent = new Intent(FormPaiementValidationActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    public void goToPaiementBanqueFromPaiementValidation(View view) {
        Intent intent = new Intent(FormPaiementValidationActivity.this,FormPaiementBanqueActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_BANQUE);
    }

}
