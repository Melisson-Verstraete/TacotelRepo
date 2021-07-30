package repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.LocaliteService;
import model.Localite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocaliteRepository {

    public LocaliteRepository() {
    }

    private LocaliteService getLocaliteService() {
        return ApiClient.getClient().create(LocaliteService.class);
    }

    public LiveData<List<Localite>> query() {
        final MutableLiveData<List<Localite>> mutableLiveData = new MutableLiveData<>();
            getLocaliteService().getLocalites().enqueue(new Callback<List<Localite>>() {
                @Override
                public void onResponse(Call<List<Localite>> call, Response<List<Localite>> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Localite>> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }

    public LiveData<Localite> create(Localite localite) {
        final MutableLiveData<Localite> mutableLiveData = new MutableLiveData<>();
            getLocaliteService().postLocalite(localite).enqueue(new Callback<Localite>() {
                @Override
                public void onResponse(Call<Localite> call, Response<Localite> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<Localite> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }
}
