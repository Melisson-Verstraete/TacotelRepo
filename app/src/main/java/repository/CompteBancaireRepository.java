package repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.CompteBancaireService;
import model.CompteBancaire;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompteBancaireRepository {

    public CompteBancaireRepository() {
    }

    private CompteBancaireService getCompteBancaireService() {
        return ApiClient.getClient().create(CompteBancaireService.class);
    }

    public LiveData<List<CompteBancaire>> query() {
        final MutableLiveData<List<CompteBancaire>> mutableLiveData = new MutableLiveData<>();
            getCompteBancaireService().getCompteBancaires().enqueue(new Callback<List<CompteBancaire>>() {
                @Override
                public void onResponse(Call<List<CompteBancaire>> call, Response<List<CompteBancaire>> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<CompteBancaire>> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }

    public LiveData<CompteBancaire> create(CompteBancaire compteBancaire) {
        final MutableLiveData<CompteBancaire> mutableLiveData = new MutableLiveData<>();
            getCompteBancaireService().postCompteBancaire(compteBancaire).enqueue(new Callback<CompteBancaire>() {
                @Override
                public void onResponse(Call<CompteBancaire> call, Response<CompteBancaire> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<CompteBancaire> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }
}
