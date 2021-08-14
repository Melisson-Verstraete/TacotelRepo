package com.helha.tacotel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;
import java.util.Locale;

public class FormPaiementAdressesActivity extends AppCompatActivity implements LocationListener {

    private static final int REQUEST_CODE_MENU = 1;
    private static final int REQUEST_CODE_PAIEMENT_BANQUE = 1;

    private Button btn_suivant_adresses ;
    private Button btn_adresses_similaires ;

    private EditText et_nom1_adresses ;
    private EditText et_prenom1_adresses ;
    private EditText et_rue1_adresses ;
    private EditText et_numero1_adresses ;
    private EditText et_codePostal1_adresses ;
    private EditText et_ville1_adresses ;
    private EditText et_pays1_adresses ;
    private EditText et_nom2_adresses ;
    private EditText et_prenom2_adresses ;
    private EditText et_rue2_adresses ;
    private EditText et_numero2_adresses ;
    private EditText et_codePostal2_adresses ;
    private EditText et_ville2_adresses ;
    private EditText et_pays2_adresses ;

    SharedPreferences sharedpreferences;
    public static final String SHARED_PREFS = "sharedPrefs";

    public static final String NOM1_ADRESSES = "nom1Key" ;
    public static final String PRENOM1_ADRESSES = "prenom1key" ;
    public static final String RUE1_ADRESSES = "rue1Key" ;
    public static final String NUMERO1_ADRESSES ="numero1Key" ;
    public static final String CODEPOSTAL1_ADRESSES = "codePostal1Key" ;
    public static final String VILLE1_ADRESSES = "ville1Key" ;
    public static final String PAYS1_ADRESSES = "pays1Key" ;
    public static final String NOM2_ADRESSES = "nom2Key" ;
    public static final String PRENOM2_ADRESSES = "prenom2key" ;
    public static final String RUE2_ADRESSES = "rue2Key" ;
    public static final String NUMERO2_ADRESSES ="numero2Key" ;
    public static final String CODEPOSTAL2_ADRESSES = "codePostal2Key" ;
    public static final String VILLE2_ADRESSES = "ville2Key" ;
    public static final String PAYS2_ADRESSES = "pays2Key" ;

    private Button btnLocation;
    private LocationManager locationManager;

/*
    SharedPreferences Sprefs;
    public static final String prefName = "report" ;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_paiement_adresses);

        btn_suivant_adresses = (Button) findViewById(R.id.btn_suivant_adresses);
        btn_adresses_similaires =(Button) findViewById(R.id.btn_adresses_similaires) ;

        et_nom1_adresses = (EditText) findViewById(R.id.et_nom1_adresses);
        et_prenom1_adresses = (EditText) findViewById(R.id.et_prenom1_adresses);
        et_rue1_adresses = (EditText) findViewById(R.id.et_rue1_adresses);
        et_numero1_adresses = (EditText) findViewById(R.id.et_numero1_adresses);
        et_codePostal1_adresses = (EditText) findViewById(R.id.et_codePostal1_adresses);
        et_ville1_adresses = (EditText) findViewById(R.id.et_ville1_adresses);
        et_pays1_adresses = (EditText) findViewById(R.id.et_pays1_adresses);
        et_nom2_adresses = (EditText) findViewById(R.id.et_nom2_adresses);
        et_prenom2_adresses = (EditText) findViewById(R.id.et_prenom2_adresses);
        et_rue2_adresses = (EditText) findViewById(R.id.et_rue2_adresses);
        et_numero2_adresses = (EditText) findViewById(R.id.et_numero2_adresses);
        et_codePostal2_adresses = (EditText) findViewById(R.id.et_codePostal2_adresses);
        et_ville2_adresses = (EditText) findViewById(R.id.et_ville2_adresses);
        et_pays2_adresses = (EditText) findViewById(R.id.et_pays2_adresses);

        //findViewById(R.id.btn_suivant_adresses).setOnClickListener(this);
        btn_adresses_similaires = (Button) findViewById(R.id.btn_adresses_similaires) ;
        btn_suivant_adresses = (Button) findViewById(R.id.btn_suivant_adresses) ;
        btnLocation = (Button) findViewById(R.id.btn_set_location);

        if(ContextCompat.checkSelfPermission(FormPaiementAdressesActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(FormPaiementAdressesActivity.this,new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
/*
        Intent intent_validation = new Intent(FormPaiementAdressesActivity.this,FormPaiementValidationActivity.class);
        intent_validation.putExtra("tv_nom1_validation", str_prenom1+" "+str_nom1) ;

        startActivity(intent_validation);*/

        sharedpreferences = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        if (sharedpreferences.contains(NOM1_ADRESSES)) {
            et_nom1_adresses.setText(sharedpreferences.getString(NOM1_ADRESSES, ""));
        }
        if (sharedpreferences.contains(PRENOM1_ADRESSES)) {
            et_prenom1_adresses.setText(sharedpreferences.getString(PRENOM1_ADRESSES, ""));

        }
        if (sharedpreferences.contains(RUE1_ADRESSES)) {
            et_rue1_adresses.setText(sharedpreferences.getString(RUE1_ADRESSES, ""));

        }
        if (sharedpreferences.contains(NUMERO1_ADRESSES)) {
            et_numero1_adresses.setText(sharedpreferences.getString(NUMERO1_ADRESSES, ""));

        }
        if (sharedpreferences.contains(CODEPOSTAL1_ADRESSES)) {
            et_codePostal1_adresses.setText(sharedpreferences.getString(CODEPOSTAL1_ADRESSES, ""));

        }
        if (sharedpreferences.contains(VILLE1_ADRESSES)) {
            et_ville1_adresses.setText(sharedpreferences.getString(VILLE1_ADRESSES, ""));

        }
        if (sharedpreferences.contains(PAYS1_ADRESSES)) {
            et_pays1_adresses.setText(sharedpreferences.getString(PAYS1_ADRESSES, ""));

        }
        if (sharedpreferences.contains(NOM2_ADRESSES)) {
            et_nom2_adresses.setText(sharedpreferences.getString(NOM2_ADRESSES, ""));
        }
        if (sharedpreferences.contains(PRENOM2_ADRESSES)) {
            et_prenom2_adresses.setText(sharedpreferences.getString(PRENOM2_ADRESSES, ""));

        }
        if (sharedpreferences.contains(RUE2_ADRESSES)) {
            et_rue2_adresses.setText(sharedpreferences.getString(RUE2_ADRESSES, ""));

        }
        if (sharedpreferences.contains(NUMERO2_ADRESSES)) {
            et_numero2_adresses.setText(sharedpreferences.getString(NUMERO2_ADRESSES, ""));

        }
        if (sharedpreferences.contains(CODEPOSTAL2_ADRESSES)) {
            et_codePostal2_adresses.setText(sharedpreferences.getString(CODEPOSTAL2_ADRESSES, ""));

        }
        if (sharedpreferences.contains(VILLE2_ADRESSES)) {
            et_ville2_adresses.setText(sharedpreferences.getString(VILLE2_ADRESSES, ""));

        }
        if (sharedpreferences.contains(PAYS2_ADRESSES)) {
            et_pays2_adresses.setText(sharedpreferences.getString(PAYS2_ADRESSES, ""));

        }


    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        try{
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,FormPaiementAdressesActivity.this);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void goToMenuFromPaiementAdresses(View view) {
        Intent intent = new Intent(FormPaiementAdressesActivity.this,MenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    public void goToPaiementBanque(View view) {
        String str_nom1 = et_nom1_adresses.getText().toString();
        String str_prenom1 = et_prenom1_adresses.getText().toString();
        String str_rue1 = et_rue1_adresses.getText().toString();
        String str_numero1 = et_numero1_adresses.getText().toString();
        String str_codePostal1 = et_codePostal1_adresses.getText().toString();
        String str_ville1 = et_ville1_adresses.getText().toString();
        String str_pays1 = et_pays1_adresses.getText().toString();
        String str_nom2 = et_nom2_adresses.getText().toString();
        String str_prenom2 = et_prenom2_adresses.getText().toString();
        String str_rue2 = et_rue2_adresses.getText().toString();
        String str_numero2 = et_numero2_adresses.getText().toString();
        String str_codePostal2 = et_codePostal2_adresses.getText().toString();
        String str_ville2 = et_ville2_adresses.getText().toString();
        String str_pays2 = et_pays2_adresses.getText().toString();

        //Sprefs = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        if (str_nom1.isEmpty()||str_nom2.isEmpty()||str_prenom1.isEmpty()||str_prenom2.isEmpty()||str_rue1.isEmpty()||
                str_rue2.isEmpty()||str_numero1.isEmpty()||str_numero2.isEmpty()||str_codePostal1.isEmpty()||str_codePostal2.isEmpty()||
                str_ville1.isEmpty()||str_ville2.isEmpty()||str_pays1.isEmpty()||str_pays2.isEmpty())
        {
            Toast.makeText(FormPaiementAdressesActivity.this, "Tout les champs doivent être remplis", Toast.LENGTH_SHORT).show() ;
        }
        else {
            Intent intent = new Intent(FormPaiementAdressesActivity.this, FormPaiementBanqueActivity.class);
            startActivityForResult(intent, REQUEST_CODE_PAIEMENT_BANQUE);

            //SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            //SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(NOM1_ADRESSES, str_nom1 + " " + str_prenom1) ;
            //editor.putString(PRENOM1_ADRESSES, str_prenom1) ;
            editor.putString(RUE1_ADRESSES, str_rue1 +" "+str_numero1+" "+str_codePostal1+ " "+str_ville1) ;
            //editor.putString(NUMERO1_ADRESSES, str_numero1) ;
            // editor.putString(CODEPOSTAL1_ADRESSES, str_codePostal1) ;
            //editor.putString(VILLE1_ADRESSES, str_ville1) ;
            editor.putString(PAYS1_ADRESSES, str_pays1) ;
            editor.putString(NOM2_ADRESSES, str_nom2+ " " + str_prenom2) ;
            //editor.putString(PRENOM2_ADRESSES, str_prenom2) ;
            editor.putString(RUE2_ADRESSES, str_rue2+" "+str_numero2+" "+str_codePostal2+ " "+str_ville2) ;
            //editor.putString(NUMERO2_ADRESSES, str_numero2) ;
            //editor.putString(CODEPOSTAL2_ADRESSES, str_codePostal2) ;
            //editor.putString(VILLE2_ADRESSES, str_ville2) ;
            editor.putString(PAYS2_ADRESSES, str_pays2) ;

            editor.commit();

            //Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();*/
/*
            editor.putString("str_nom1"+et_nom1_adresses.getText().toString(), et_nom1_adresses.getText().toString()) ;

            editor.commit() ;*/

            //Intent intent_validation = new Intent(FormPaiementAdressesActivity.this, FormPaiementValidationActivity.class);

            //intent.putExtra("tv_nom1_validation", str_prenom1+" "+str_nom1) ;
            //intent.putExtra("et_prenom1_adresses", str_prenom1) ;
            //intent.putExtra("et_rue1_adresses", str_rue1) ;


            //startActivity(intent_validation);
        }
    }

    public void copyAdress(View view) {

        Toast.makeText(FormPaiementAdressesActivity.this, "Adresse copié", Toast.LENGTH_SHORT).show() ;

        et_nom2_adresses.setText(et_nom1_adresses.getText());
        et_prenom2_adresses.setText(et_prenom1_adresses.getText());
        et_rue2_adresses.setText(et_rue1_adresses.getText());
        et_numero2_adresses.setText(et_numero1_adresses.getText());
        et_codePostal2_adresses.setText(et_codePostal1_adresses.getText());
        et_ville2_adresses.setText(et_ville1_adresses.getText());
        et_pays2_adresses.setText(et_pays1_adresses.getText());
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try{
            Geocoder geocoder = new Geocoder(FormPaiementAdressesActivity.this, Locale.getDefault());
            List<Address> adresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            Address address = adresses.get(0);

            et_codePostal1_adresses.setText(address.getPostalCode());

            et_pays1_adresses.setText(address.getCountryName());

            et_rue1_adresses.setText(address.getThoroughfare());

            et_ville1_adresses.setText(address.getLocality());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
/*
    public void formulaireLivraison () {
        String str_nom1 ="" ;
        String str_prenom1 ="";
        String str_rue1 ="" ;
        String str_numero1 ="" ;
        String str_codePostal1 ="" ;
        String str_ville1 ="" ;
        String str_pays1 ="" ;
        String str_nom2 ="" ;
        String str_prenom2 ="";
        String str_rue2 ="" ;
        String str_numero2 ="" ;
        String str_codePostal2 ="" ;
        String str_ville2 ="" ;
        String str_pays2 ="" ;

        //Validation
        boolean info_valable=true;

        //s'assurer que ce n'est pas vide
        if(et_nom1_adresses.getText().toString().trim().equals("")||et_nom2_adresses.getText().toString().trim().equals("")==false){
            str_nom1=et_nom1_adresses.getText().toString().trim();
            str_nom2=et_nom2_adresses.getText().toString().trim();
        }else{
            info_valable=false;
        }

        if(et_prenom1_adresses.getText().toString().trim().equals("")||et_prenom2_adresses.getText().toString().trim().equals("")==false){
            str_prenom1=et_prenom1_adresses.getText().toString().trim();
            str_prenom2=et_prenom2_adresses.getText().toString().trim();
        }else{
            info_valable=false;
        }

        if(et_rue1_adresses.getText().toString().trim().equals("")||et_rue2_adresses.getText().toString().trim().equals("")==false){
            str_rue1=et_rue1_adresses.getText().toString().trim();
            str_rue2=et_rue2_adresses.getText().toString().trim();
        }else{
            info_valable=false;
        }

        if(et_numero1_adresses.getText().toString().trim().equals("")||et_numero2_adresses.getText().toString().trim().equals("")==false){
            str_numero1=et_numero1_adresses.getText().toString().trim();
            str_numero2=et_numero2_adresses.getText().toString().trim();
        }else{
            info_valable=false;
        }

        if(et_codePostal1_adresses.getText().toString().trim().equals("")||et_codePostal2_adresses.getText().toString().trim().equals("")==false){
            str_codePostal1=et_codePostal1_adresses.getText().toString().trim();
            str_codePostal2=et_codePostal2_adresses.getText().toString().trim();
        }else{
            info_valable=false;
        }

        if(et_ville1_adresses.getText().toString().trim().equals("")||et_ville2_adresses.getText().toString().trim().equals("")==false){
            str_ville1=et_ville1_adresses.getText().toString().trim();
            str_ville2=et_ville2_adresses.getText().toString().trim();
        }else{
            info_valable=false;
        }

        if(et_pays1_adresses.getText().toString().trim().equals("")||et_pays2_adresses.getText().toString().trim().equals("")==false){
            str_pays1=et_pays1_adresses.getText().toString().trim();
            str_pays2=et_pays2_adresses.getText().toString().trim();
        }else{
            info_valable=false;
        }

        if (info_valable==false){
            Toast.makeText(getApplicationContext(),"Il manque des informations", Toast.LENGTH_SHORT).show();
        }/*else{
                    Intent intent = new Intent(getApplicationContext(), ViewInfoActivity.class);
                    intent.putExtra("nom",str_nom);
                    intent.putExtra("prenom",str_prenom);
                    finish();
                    startActivity(intent);
                }
    }


    //@Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.btn_suivant_adresses:
                formulaireLivraison() ;
                break;

        }
    }*/
}
