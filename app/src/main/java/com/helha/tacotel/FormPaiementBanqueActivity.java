package com.helha.tacotel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormPaiementBanqueActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_PAIEMENT_ADRESSES = 1;
    private static final int REQUEST_CODE_PAIEMENT_VALIDATION = 1;

    private RadioGroup radioGroup_carte ;
    private RadioButton radioButton_debit;
    private RadioButton radioButton_credit ;

    private EditText et_titulaire_compte ;
    private EditText et_num_compte ;
    private EditText et_cvv ;

    SharedPreferences sharedpreferences2;
    public static final String SHARED_PREFS2 = "sharedPrefs2";

    public static final String TITULAIRE_COMPTE = "titulaireKey" ;
    public static final String NUM_COMPTE = "numKey" ;
    public static final String CVV = "cvvKey" ;
    public static String TYPE_CARTE = "débit" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_paiement_banque);

        et_titulaire_compte = (EditText) findViewById(R.id.et_titulaire_compte);
        et_num_compte = (EditText) findViewById(R.id.et_num_compte);
        et_cvv = (EditText) findViewById(R.id.et_cvv);

        radioGroup_carte = (RadioGroup) findViewById(R.id.radioGroup_carte);
        radioButton_debit = (RadioButton)findViewById(R.id.radioButton_debit);
        radioButton_credit = (RadioButton)findViewById(R.id.radioButton_credit);

        radioButton_debit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    et_cvv.setVisibility(View.INVISIBLE);
                    TYPE_CARTE = "débit";
                }
            }
        });

        radioButton_credit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    et_cvv.setVisibility(View.VISIBLE);
                    TYPE_CARTE = "crédit";
                }
            }
        });

        sharedpreferences2 = getSharedPreferences(SHARED_PREFS2, Context.MODE_PRIVATE);
        if (sharedpreferences2.contains(TITULAIRE_COMPTE)) {
            et_titulaire_compte.setText(sharedpreferences2.getString(TITULAIRE_COMPTE, ""));
        }
        if (sharedpreferences2.contains(NUM_COMPTE)) {
            et_cvv.setText(sharedpreferences2.getString(NUM_COMPTE, ""));
        }
        if (sharedpreferences2.contains(CVV)) {
            et_cvv.setText(sharedpreferences2.getString(CVV, ""));
        }
        if(sharedpreferences2.contains(TYPE_CARTE)){
            radioButton_credit.setChecked(sharedpreferences2.getBoolean("TYPE_CARTE", false));
            radioButton_debit.setChecked(sharedpreferences2.getBoolean("TYPE_CARTE", false));
        }
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
        String str_titulaire_compte = et_titulaire_compte.getText().toString() ;
        String str_num_compte = et_num_compte.getText().toString() ;
        String str_cvv = et_cvv.getText().toString() ;
        String str_type = "type de carte: "+TYPE_CARTE;

        SharedPreferences.Editor editor = sharedpreferences2.edit();

        if(((str_titulaire_compte.isEmpty()||str_num_compte.isEmpty()||str_cvv.isEmpty())&&radioButton_credit.isChecked())||((str_titulaire_compte.isEmpty()||str_num_compte.isEmpty())&&radioButton_debit.isChecked()))
        {
            Toast.makeText(FormPaiementBanqueActivity.this, "Tout les champs doivent être remplis", Toast.LENGTH_SHORT).show() ;
        }
        else {
            Intent intent = new Intent(FormPaiementBanqueActivity.this,FormPaiementValidationActivity.class);
            startActivityForResult(intent, REQUEST_CODE_PAIEMENT_VALIDATION);

            editor.putString(TYPE_CARTE, str_type);
            editor.putString(TITULAIRE_COMPTE, str_titulaire_compte) ;
            editor.putString(NUM_COMPTE, str_num_compte) ;
            editor.putString(CVV, str_cvv) ;

            int checkedRadioButtonId = radioGroup_carte.getCheckedRadioButtonId() ;
            editor.putInt("checkRadioButtonId", checkedRadioButtonId) ;

            editor.commit() ;
        }
    }

    public void onRadioButtonClicked(View view) {

        if(radioButton_credit.isChecked())
        {
            et_cvv.setVisibility(View.VISIBLE);
        }
        if(radioButton_debit.isChecked()){
            et_cvv.setVisibility(View.INVISIBLE);
        }
    }
}
