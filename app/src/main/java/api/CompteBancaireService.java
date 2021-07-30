package api;

import java.util.List;

import model.CompteBancaire;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CompteBancaireService {

    @GET("dbTaCoTel/compte")
    Call<List<CompteBancaire>> getCompteBancaires();

    @POST("dbTaCoTel/compte")
    Call<CompteBancaire> postCompteBancaire(@Body CompteBancaire compteBancaire);
}
