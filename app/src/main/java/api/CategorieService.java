package api;

import java.util.List;

import model.Categorie;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategorieService {
    @GET("dbTaCoTel/Categorie")
    Call<List<Categorie>> getCategories();
}
