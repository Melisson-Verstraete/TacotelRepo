package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.PanierService;
import model.Article;
import model.Contient;
import model.Panier;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanierRepository {

    public PanierRepository() {
    }

    private PanierService getPanierService() {
        return ApiClient.getClient().create(PanierService.class);
    }

    public LiveData<List<Panier>> query() {
        final MutableLiveData<List<Panier>> mutableLiveData = new MutableLiveData<>();
            getPanierService().getPaniers().enqueue(new Callback<List<Panier>>() {
                @Override
                public void onResponse(Call<List<Panier>> call, Response<List<Panier>> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Panier>> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }

    public LiveData<Panier> create(Panier panier) {
        final MutableLiveData<Panier> mutableLiveData = new MutableLiveData<>();
            getPanierService().postPanier(panier).enqueue(new Callback<Panier>() {
                @Override
                public void onResponse(Call<Panier> call, Response<Panier> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<Panier> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }

    public LiveData<List<Article>> getArticles(int idPanier) {
        final MutableLiveData<List<Article>> mutableLiveData = new MutableLiveData<>();
            getPanierService().getArticles(idPanier).enqueue(new Callback<List<Article>>() {
                @Override
                public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Article>> call, Throwable t) {

                }

            });
        return mutableLiveData;
    }

    public LiveData<Article> addArticle(int idPanier, Article article, int qteArticleChoisi) {
        final MutableLiveData<Article> mutableLiveData = new MutableLiveData<>();
            getPanierService().postArticle(idPanier, article.getIdArticle(), qteArticleChoisi).enqueue(new Callback<Article>() {
                @Override
                public void onResponse(Call<Article> call, Response<Article> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<Article> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }
}
