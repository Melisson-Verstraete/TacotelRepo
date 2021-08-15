package api;

import java.util.List;

import model.Article;
import model.Panier;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PanierService {

    @GET("dbTaCoTel/panier")
    Call<List<Panier>> getPaniers();

    @POST("dbTaCoTel/panier")
    Call<Panier> postPanier(@Body Panier panier);

    @GET("dbTaCoTel/panier/{idPanier}")
    Call<List<Article>> queryArticles(@Path("idPanier") int idPanier);

    @POST("dbTaCoTel/panier/{idPanier}/Article/{idArticle}/quantite/{qteArticleChoisi}")
    Call<Boolean> postArticle(@Body Article idArticle, @Path("idPanier") int idPanier, @Path("qteArticleChoisi") int qteArticleChoisi);

    @DELETE("dbTaCoTel/panier/{idPanier}")
    Call<Boolean> deletePanier(@Path("idPanier") int idPanier);

    @DELETE("dbTaCoTel/panier/Article/Delete/{idArticle}")
    Call<Boolean> deleteArticleFromAllPanier(@Path("idArticle") int idArticle);
}
