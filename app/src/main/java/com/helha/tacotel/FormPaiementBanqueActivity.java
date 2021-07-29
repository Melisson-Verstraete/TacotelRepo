package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FormPaiementBanqueActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_PAIEMENT_ADRESSES = 1;
    private static final int REQUEST_CODE_PAIEMENT_VALIDATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_paiement_banque);
    }

    public void goToMenuFromPaiementBanque(View view) {
        Intent intent = new Intent(FormPaiementBanqueActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    public void goToPaiementAdressesFromPaiementBanque(View view) {
        Intent intent = new Intent(FormPaiementBanqueActivity.this,FormPaiementAdressesActivity.class);
        startActivityForResult(intent, REQUEST_CODE_PAIEMENT_ADRESSES);
    }

    public void goToPaiementValidationFromPaiementBanque(View view) {
        Intent intent = new Intent(FormPaiementBanqueActivity.this,FormPaiementValidationActivity.class);
        startActivityForResult(intent, REQUEST_CODE_PAIEMENT_VALIDATION);
    }
}
