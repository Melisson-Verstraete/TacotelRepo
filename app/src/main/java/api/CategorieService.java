package api;

import java.util.List;

import model.Categorie;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategorieService {
    @GET("dbTaCoTel/Categorie")
    Call<List<Categorie>> getCategories();

    @POST("dbTaCoTel/Categorie")
    Call<Categorie> postCategorie(@Body Categorie categorie);
}
