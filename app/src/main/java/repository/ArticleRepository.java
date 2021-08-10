package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.ArticleService;
import model.Article;
import model.Categorie;
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

    //Pour la modification d'un article
    public LiveData<Article> update(int id, Article article){
        final MutableLiveData<Article> mutableLiveData = new MutableLiveData<>();
        getArticleService().putArticle(id, article).enqueue(new Callback<Article>() {
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

    public LiveData<Integer> getArticleByLibelle(String libelle) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
        getArticleService().getArticleByLibelle(libelle).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public LiveData<Article> setCategorie(int idArticle, int idCategorie) {
        final MutableLiveData<Article> mutableLiveData = new MutableLiveData<>();
        getArticleService().postCategorieToArticle(idArticle,idCategorie).enqueue(new Callback<Article>() {
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
    public LiveData<Integer> getCategorieByArticle (int id){
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
        getArticleService().getCategorieByArticle(id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }

        });
        return mutableLiveData;
    }


    public LiveData<Boolean> deleteCategorieFromArticle (int idArticle){
        final MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        getArticleService().deleteCategorieFromArticle(idArticle).enqueue(new Callback<Boolean>() {
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
