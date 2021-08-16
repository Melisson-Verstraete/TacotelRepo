package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.ContientService;
import model.Article;
import model.Contient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContientRepository {

    public ContientRepository() {
    }

    private ContientService getContientService() {
        return ApiClient.getClient().create(ContientService.class);
    }

    public LiveData<List<Contient>> query(int idPanier) {
        final MutableLiveData<List<Contient>> mutableLiveData = new MutableLiveData<>();
            getContientService().getContients(idPanier).enqueue(new Callback<List<Contient>>() {
                @Override
                public void onResponse(Call<List<Contient>> call, Response<List<Contient>> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Contient>> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }

    public LiveData<Contient> create(Contient contient) {
        final MutableLiveData<Contient> mutableLiveData = new MutableLiveData<>();
            getContientService().postContient(contient).enqueue(new Callback<Contient>() {
                @Override
                public void onResponse(Call<Contient> call, Response<Contient> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<Contient> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }

    public LiveData<Boolean> delete (int idPanier){
        final MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        getContientService().deleteContient(idPanier).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }

        });
        return mutableLiveData;
    }

    public LiveData<Boolean> deleteArticle (int idPanier, int idArticle){
        final MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        getContientService().deleteArticleContient(idPanier, idArticle).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
            }

        });
        return mutableLiveData;
    }

    public LiveData<Boolean> update(int idPanier, int idArticle, int qteArticleChoisi){
        final MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        getContientService().updateContient(idPanier, idArticle, qteArticleChoisi).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }

        });
        return mutableLiveData;
    }
}
