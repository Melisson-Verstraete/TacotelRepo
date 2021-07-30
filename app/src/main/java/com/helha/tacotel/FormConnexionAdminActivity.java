package com.helha.tacotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.List;

import model.Admin;
import repository.AdminRepository;

public class FormConnexionAdminActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADMIN_LIST_ARTICLES = 1;
    private static final int REQUEST_CODE_FORM_CONNEXION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_connexion_admin);

        AdminRepository adminRepository = new AdminRepository();

        adminRepository.query().observe(this, new Observer<List<Admin>>() {
            @Override
            public void onChanged(List<Admin> admins) {
                Log.i("Admins", admins.toString());
            }
        });
    }

    public void goToAdminArticles(View view) {
        Intent intent = new Intent(FormConnexionAdminActivity.this,AdminListArticlesActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADMIN_LIST_ARTICLES);
    }

    public void goToConnexionFromConnexionAdmin(View view) {
        Intent intent = new Intent(FormConnexionAdminActivity.this,FormConnexionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FORM_CONNEXION);
    }
}
