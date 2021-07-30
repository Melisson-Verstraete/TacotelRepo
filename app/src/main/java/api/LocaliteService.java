package api;

import java.util.List;

import model.Localite;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LocaliteService {

    @GET("dbTaCoTel/localite")
    Call<List<Localite>> getLocalites();

    @POST("dbTaCoTel/localite")
    Call<Localite> postLocalite(@Body Localite localite);
}
