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

import static com.helha.tacotel.FormPaiementAdressesActivity.SHARED_PREFS;


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

    SharedPreferences Sprefs;
    private String prefName = "report" ;


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
        tv_mode_validation = (TextView) findViewById(R.id.tv_mode_validation) ;
        tv_titulaire_compte_validation = (TextView) findViewById(R.id.tv_titulaire_compte_validation) ;
        tv_num_compte_validation = (TextView) findViewById(R.id.tv_num_compte_validation) ;
        //tv_cvv_validation = (TextView) findViewById(R.id.tv_cvv_validation) ;
/*
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text_nom1 = sharedPreferences.getString(STR_NOM1, "");

        tv_nom1_validation.setText(text_nom1);*/

        Intent intent = getIntent();
        if (intent != null){
            Toast.makeText(FormPaiementValidationActivity.this, "le intent fonctionne", Toast.LENGTH_SHORT).show() ;

            //tv_nom1_validation.setText(intent.getStringExtra("tv_nom1_validation"));
 /*           tv_adresse1_validation.setText(intent.getStringExtra("tv_adresse1_validation"));
            tv_pays1_validation.setText(intent.getStringExtra("tv_pays1_validation"));
            tv_nom2_validation.setText(intent.getStringExtra("tv_nom2_validation"));
            tv_adresse2_validation.setText(intent.getStringExtra("tv_adresse2_validation"));
            tv_pays2_validation.setText(intent.getStringExtra("tv_pays2_validation"));
*/
            tv_mode_validation.setText(intent.getStringExtra("tv_mode_validation"));
            tv_titulaire_compte_validation.setText(intent.getStringExtra("tv_titulaire_compte_validation"));
            tv_num_compte_validation.setText(intent.getStringExtra("tv_num_compte_validation"));

        }

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
