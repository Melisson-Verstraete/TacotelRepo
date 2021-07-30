package api;

import java.util.List;

import model.Panier;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PanierService {

    @GET("dbTaCoTel/panier")
    Call<List<Panier>> getPaniers();

    @POST("dbTaCoTel/panier")
    Call<Panier> postPanier(@Body Panier panier);
}
