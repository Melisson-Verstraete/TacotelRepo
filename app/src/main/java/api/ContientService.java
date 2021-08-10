package api;

import java.util.List;

import model.Contient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ContientService {

    @GET("dbTaCoTel/Contient")
    Call<List<Contient>> getContients();

    @POST("dbTaCoTel/Contient")
    Call<Contient> postContient(@Body Contient contient);
}
