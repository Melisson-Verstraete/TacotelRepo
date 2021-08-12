package repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import model.Utilisateur;
import model.Utilisateur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import api.ApiClient;
import api.UtilisateurConnecte;

public class UtilisateurRepository {

    public LiveData<Utilisateur> query(int id) {
        final MutableLiveData<Utilisateur> mutableLiveData = new MutableLiveData<>();
        getUtilisateurConnecte().getSpeudo(id).enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    private UtilisateurConnecte getUtilisateurConnecte() {
        return ApiClient.getClient().create(UtilisateurConnecte.class);
    }
}
