package com.helha.tacotel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.helha.tacotel.FormPaiementAdressesActivity.NOM1_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.NOM2_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.PAYS1_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.PAYS2_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.RUE1_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.RUE2_ADRESSES;
import static com.helha.tacotel.FormPaiementAdressesActivity.SHARED_PREFS;
import static com.helha.tacotel.FormPaiementBanqueActivity.NUM_COMPTE;
import static com.helha.tacotel.FormPaiementBanqueActivity.SHARED_PREFS2;
import static com.helha.tacotel.FormPaiementBanqueActivity.TITULAIRE_COMPTE;

//import static final com.helha.tacotel.FormPaiementAdressesActivity.SHARED_PREFS;


public class FormPaiementValidationActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_FORM_BANQUE = 1;

    private TextView tv_nom1_validation ;
    private TextView tv_adresse1_validation ;
    private TextView tv_pays1_validation ;
    private TextView tv_nom2_validation ;
    private TextView tv_adresse2_validation ;
    private TextView tv_pays2_validation ;
    private TextView tv_mode_validation ;
    private TextView tv_titulaire_compte_validation ;
    private TextView tv_num_compte_validation ;
    private TextView tv_cvv_validation ;

    private Button btn_payer_validation ;
    private Button btn_retour_validation ;

    //SharedPreferences Sprefs;
    // private String prefName = "report" ;
/*
    SharedPreferences sharedpreferences;
    public static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedpreferences2;
    public static final String SHARED_PREFS2 = "sharedPrefs2";
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_paiement_validation);

        btn_payer_validation = (Button) findViewById(R.id.btn_payer_validation);
        btn_retour_validation = (Button) findViewById(R.id.btn_retour_validation);

        tv_nom1_validation = (TextView) findViewById(R.id.tv_nom1_validation) ;
        tv_adresse1_validation = (TextView) findViewById(R.id.tv_adresse1_validation) ;
        tv_pays1_validation = (TextView) findViewById(R.id.tv_pays1_validation) ;
        tv_nom2_validation = (TextView) findViewById(R.id.tv_nom2_validation) ;
        tv_adresse2_validation = (TextView) findViewById(R.id.tv_adresse2_validation) ;
        tv_pays2_validation = (TextView) findViewById(R.id.tv_pays2_validation) ;
        //tv_mode_validation = (TextView) findViewById(R.id.tv_mode_validation) ;
        tv_titulaire_compte_validation = (TextView) findViewById(R.id.tv_titulaire_compte_validation) ;
        tv_num_compte_validation = (TextView) findViewById(R.id.tv_num_compte_validation) ;
        //tv_cvv_validation = (TextView) findViewById(R.id.tv_cvv_validation) ;

        SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences sharedpreferences2 = getSharedPreferences(SHARED_PREFS2, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(NOM1_ADRESSES)) {
            tv_nom1_validation.setText(sharedpreferences.getString(NOM1_ADRESSES, ""));
        }
        if (sharedpreferences.contains(RUE1_ADRESSES)) {
            tv_adresse1_validation.setText(sharedpreferences.getString(RUE1_ADRESSES, ""));
        }
        if (sharedpreferences.contains(PAYS1_ADRESSES)) {
            tv_pays1_validation.setText(sharedpreferences.getString(PAYS1_ADRESSES, ""));
        }
        if (sharedpreferences.contains(NOM2_ADRESSES)) {
            tv_nom2_validation.setText(sharedpreferences.getString(NOM2_ADRESSES, ""));
        }
        if (sharedpreferences.contains(RUE2_ADRESSES)) {
            tv_adresse2_validation.setText(sharedpreferences.getString(RUE2_ADRESSES, ""));
        }
        if (sharedpreferences.contains(PAYS2_ADRESSES)) {
            tv_pays2_validation.setText(sharedpreferences.getString(PAYS2_ADRESSES, ""));
        }
        if (sharedpreferences2.contains(TITULAIRE_COMPTE)) {
            tv_titulaire_compte_validation.setText(sharedpreferences2.getString(TITULAIRE_COMPTE, ""));
        }
        if (sharedpreferences2.contains(NUM_COMPTE)) {
            tv_num_compte_validation.setText(sharedpreferences2.getString(NUM_COMPTE, ""));
        }

/*
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text_nom1 = sharedPreferences.getString(STR_NOM1, "");

        tv_nom1_validation.setText(text_nom1);*/
/*
        Intent intent = getIntent();
        if (intent != null){
            Toast.makeText(FormPaiementValidationActivity.this, "le intent fonctionne", Toast.LENGTH_SHORT).show() ;

            //tv_nom1_validation.setText(intent.getStringExtra("tv_nom1_validation"));
            tv_adresse1_validation.setText(intent.getStringExtra("tv_adresse1_validation"));
            tv_pays1_validation.setText(intent.getStringExtra("tv_pays1_validation"));
            tv_nom2_validation.setText(intent.getStringExtra("tv_nom2_validation"));
            tv_adresse2_validation.setText(intent.getStringExtra("tv_adresse2_validation"));
            tv_pays2_validation.setText(intent.getStringExtra("tv_pays2_validation"));

            tv_mode_validation.setText(intent.getStringExtra("tv_mode_validation"));
            tv_titulaire_compte_validation.setText(intent.getStringExtra("tv_titulaire_compte_validation"));
            tv_num_compte_validation.setText(intent.getStringExtra("tv_num_compte_validation"));

        }*/



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
