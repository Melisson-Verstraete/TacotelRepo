package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.ArticleService;
import model.Article;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    public ArticleRepository() {
    }

    private ArticleService getArticleService() {
        return ApiClient.getClient().create(ArticleService.class);
    }

    public LiveData<List<Article>> query() { // ressemble à Observable dans Angular
        final MutableLiveData<List<Article>> mutableLiveData = new MutableLiveData<>();
            getArticleService().getArticles().enqueue(new Callback<List<Article>>() {
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

    public LiveData<Article> create(Article article) {
        final MutableLiveData<Article> mutableLiveData = new MutableLiveData<>();
            getArticleService().postArticle(article).enqueue(new Callback<Article>() {
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

    public LiveData<Boolean> delete (int id){
        final MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        getArticleService().deleteArticle(id).enqueue(new Callback<Boolean>() {
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
