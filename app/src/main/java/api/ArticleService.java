package api;

import java.util.List;

import model.Article;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ArticleService {
    @GET("dbTaCoTel/Article")
    Call<List<Article>> getArticles();

    @POST("dbTaCoTel/Article")
    Call<Article> postArticle(@Body Article article);

    @DELETE("dbTaCoTel/Article/{id}")
    Call<Boolean> deleteArticle(@Path("id") int id);
}
