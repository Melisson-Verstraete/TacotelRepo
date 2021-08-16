package com.helha.tacotel;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.List;

import api.AdminService;
import api.ApiClient;
import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import model.Admin;
import model.Utilisateur;
import repository.AdminRepository;

public class FormConnexionAdminActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADMIN_LIST_ARTICLES = 1;
    private static final int REQUEST_CODE_FORM_CONNEXION = 1;

    AdminService adminService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_connexion_admin);

       //Init Api
       adminService = ApiClient.getClient().create(AdminService.class);

        //views
        EditText et_pseudo_admin = findViewById(R.id.et_pseudo_admin);
        EditText et_mdp_admin = findViewById(R.id.et_mdp_admin);
        Button btn_valider_admin = findViewById(R.id.btn_valider_admin);

        //Event
        btn_valider_admin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new SpotsDialog.Builder()
                        .setContext(FormConnexionAdminActivity.this)
                        .build();
                dialog.show();

                AdminRepository adminRepository = new AdminRepository();
                adminRepository.query().observe(FormConnexionAdminActivity.this, new Observer<List<Admin>>() {
                    @Override
                    public void onChanged(List<Admin> adminsApi) {
                        for (int j=0;j<adminsApi.size();j++) {
                            if ((et_pseudo_admin.getText().toString().equals(adminsApi.get(j).getLogin())) && (et_mdp_admin.getText().toString().equals(adminsApi.get(j).getMdpAdmin()))) {
                                Intent intent = new Intent(FormConnexionAdminActivity.this,AdminListArticlesActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_ADMIN_LIST_ARTICLES);
                            } else {
                                String message = "Le login et/ou le mot de passe est incorrecte. Veuillez svp ré-essayer.";
                                Toast.makeText(FormConnexionAdminActivity.this, message, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(FormConnexionAdminActivity.this,FormConnexionAdminActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_ADMIN_LIST_ARTICLES);
                            }
                        }
                    }
                });

                //create Admin to login
//                Admin admin = new Admin(et_pseudo_admin.getText().toString(), et_mdp_admin.getText().toString());
//
//                compositeDisposable.add(adminService.loginAdmin(admin)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<Admin>() {
//                            @Override
//                            public void accept(Admin a) throws Exception {
//                                Toast.makeText(FormConnexionAdminActivity.this, a.toString(), Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(FormConnexionAdminActivity.this, AdminListArticlesActivity.class);
//                                startActivityForResult(intent, REQUEST_CODE_ADMIN_LIST_ARTICLES);
//                                dialog.dismiss();
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                dialog.dismiss();
//                                Toast.makeText(FormConnexionAdminActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }));
            }
        });
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
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
