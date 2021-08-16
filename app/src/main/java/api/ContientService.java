package api;

import java.util.List;

import model.Contient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContientService {

    @GET("dbTaCoTel/Contient/{idPanier}")
    Call<List<Contient>> getContients(@Path("idPanier") int idPanier);

    @POST("dbTaCoTel/Contient/Panier/{idPanier}/Article/{idArticle}")
    Call<Contient> postContient(@Body Contient contient,@Path("idPanier") int idPanier,@Path("idArticle") int idArticle);

    @DELETE("{idPanier}")
    Call<Boolean> deleteContient(@Path("idPanier") int idPanier);

    @DELETE("{idPanier}/Article/{idArticle}")
    Call<Boolean> deleteArticleContient(@Path("idPanier") int idPanier, @Path("idArticle") int idArticle);

    @PUT ("{idPanier}/Article/{idArticle}/quantite/{qteArticleChoisi}")
    Call<Boolean> updateContient(@Path("idPanier") int idPanier, @Path("idArticle") int idArticle, @Path("qteArticleChoisi") int qteArticleChoisi);
}
