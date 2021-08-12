package api;

import java.util.List;

import model.Article;
import model.Panier;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PanierService {

    @GET("dbTaCoTel/panier")
    Call<List<Panier>> getPaniers();

    @POST("dbTaCoTel/panier")
    Call<Panier> postPanier(@Body Panier panier);

    @GET("dbTaCoTel/panier/{idPanier}")
    Call<List<Article>> getArticles(@Path("idPanier") int idPanier);

    @POST("dbTaCoTel/panier/{idPanier}/Article/{idArticle}")
    Call<Article> addArticle(@Path("idPanier") int idPanier, @Path("idArticle") int idArticle, @Body int qteArticleChoisi);
}
