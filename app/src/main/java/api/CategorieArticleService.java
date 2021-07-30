package api;

import java.util.List;

import model.CategorieArticle;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategorieArticleService {

    @GET("dbTaCoTel/Categorie_article")
    Call<List<CategorieArticle>> getCategorieArticles();

    @POST("dbTaCoTel/Categorie_article")
    Call<CategorieArticle> postCategorieArticle(@Body CategorieArticle categorieArticle);
}
