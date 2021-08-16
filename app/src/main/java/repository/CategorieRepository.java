package repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.CategorieService;
import model.Categorie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorieRepository {

    public CategorieRepository() {
    }

    private CategorieService getCategorieService() {
        return ApiClient.getClient().create(CategorieService.class);
    }

    public LiveData<List<Categorie>> query() {
        final MutableLiveData<List<Categorie>> mutableLiveData = new MutableLiveData<>();
            getCategorieService().getCategories().enqueue(new Callback<List<Categorie>>() {
                @Override
                public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Categorie>> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }
}
