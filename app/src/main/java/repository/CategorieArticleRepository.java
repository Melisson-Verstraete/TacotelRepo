package repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.CategorieArticleService;
import model.CategorieArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorieArticleRepository {

    public CategorieArticleRepository() {
    }

    private CategorieArticleService getCategorieArticleService() {
        return ApiClient.getClient().create(CategorieArticleService.class);
    }

    public LiveData<List<CategorieArticle>> query() {
        final MutableLiveData<List<CategorieArticle>> mutableLiveData = new MutableLiveData<>();
            getCategorieArticleService().getCategorieArticles().enqueue(new Callback<List<CategorieArticle>>() {
                @Override
                public void onResponse(Call<List<CategorieArticle>> call, Response<List<CategorieArticle>> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<CategorieArticle>> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }

    public LiveData<CategorieArticle> create(CategorieArticle categorieArticle) {
        final MutableLiveData<CategorieArticle> mutableLiveData = new MutableLiveData<>();
            getCategorieArticleService().postCategorieArticle(categorieArticle).enqueue(new Callback<CategorieArticle>() {
                @Override
                public void onResponse(Call<CategorieArticle> call, Response<CategorieArticle> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<CategorieArticle> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }
}
