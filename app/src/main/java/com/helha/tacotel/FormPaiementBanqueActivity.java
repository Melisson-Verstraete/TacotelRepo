package com.helha.tacotel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormPaiementBanqueActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_PAIEMENT_ADRESSES = 1;
    private static final int REQUEST_CODE_PAIEMENT_VALIDATION = 1;

    private RadioGroup radioGroup_carte ;
    private RadioButton radioButton_debit ;
    private RadioButton radioButton_credit ;

    private EditText et_titulaire_compte ;
    private EditText et_num_compte ;
    private EditText et_cvv ;

    private Button btn_retour_banque ;
    private Button btn_suivant_banque ;

    private TextView tv_nom1_validation ;
    private TextView tv_adresse1_validation ;
    private TextView tv_pays1_validation ;
    private TextView tv_mode_validation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_paiement_banque);

        btn_retour_banque = (Button) findViewById(R.id.btn_retour_banque);
        btn_suivant_banque = (Button) findViewById(R.id.btn_suivant_banque);

        et_titulaire_compte = (EditText) findViewById(R.id.et_titulaire_compte);
        et_num_compte = (EditText) findViewById(R.id.et_num_compte);
        et_cvv = (EditText) findViewById(R.id.et_cvv);

        radioGroup_carte = (RadioGroup) findViewById(R.id.radioGroup_carte);
        radioButton_debit = (RadioButton)findViewById(R.id.radioButton_debit);
        radioButton_credit = (RadioButton)findViewById(R.id.radioButton_credit);
/*
        tv_nom1_validation = (TextView) findViewById(R.id.tv_nom1_validation) ;
        tv_adresse1_validation = (TextView) findViewById(R.id.tv_adresse1_validation) ;
        tv_pays1_validation = (TextView) findViewById(R.id.tv_pays1_validation) ;*/

        radioButton_debit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    et_cvv.setVisibility(View.INVISIBLE);
                    String str_mode_paiement = "carte débit" ;
                    //rb2.setChecked(false);
                }
            }
        });

        radioButton_credit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    et_cvv.setVisibility(View.VISIBLE);
                    String str_mode_paiement = "carte crédit" ;
                    //rb2.setChecked(false);
                }
            }
        });

/*
        Intent intent = getIntent();
        if (intent != null){
            Toast.makeText(FormPaiementBanqueActivity.this, "le intent fonctionne", Toast.LENGTH_SHORT).show() ;
            tv_nom1_validation.setText(intent.getStringExtra("tv_nom1_validation"));
            tv_adresse1_validation.setText(intent.getStringExtra("tv_adresse1_validation"));
            tv_pays1_validation.setText(intent.getStringExtra("tv_pays1_validation"));
        }*/
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


        if(((str_titulaire_compte.isEmpty()||str_num_compte.isEmpty()||str_cvv.isEmpty())&&radioButton_credit.isChecked())||((str_titulaire_compte.isEmpty()||str_num_compte.isEmpty())&&radioButton_debit.isChecked()))
        {
            Toast.makeText(FormPaiementBanqueActivity.this, "Tout les champs doivent être remplis", Toast.LENGTH_SHORT).show() ;
        }
        else {
            Intent intent = new Intent(FormPaiementBanqueActivity.this,FormPaiementValidationActivity.class);
            startActivityForResult(intent, REQUEST_CODE_PAIEMENT_VALIDATION);


            //intent.putExtra("tv_nom1_validation", str)
            tv_nom1_validation.setText(intent.getStringExtra("tv_nom1_validation"));

            //intent.putExtra("tv_mode_validation", str_mode_paiement) ;
            intent.putExtra("tv_titulaire_compte_validation", str_titulaire_compte) ;
            intent.putExtra("tv_num_compte_validation", str_num_compte) ;

            startActivity(intent);
        }
    }
/*
    public void onRadioButtonClicked(View view) {

        if(radioButton_credit.isChecked())
        {
            et_cvv.setVisibility(View.VISIBLE);
        }
        if(radioButton_debit.isChecked()){
            et_cvv.setVisibility(View.INVISIBLE);
        }
    }*/
}
