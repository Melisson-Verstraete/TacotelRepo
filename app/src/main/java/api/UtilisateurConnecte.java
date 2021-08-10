package api;

import java.util.List;

import io.reactivex.Observable;
import model.Login;
import model.Utilisateur;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UtilisateurConnecte {
    @GET("dbTaCoTel/UtilisateurConnecte")
    Call<List<Utilisateur>> getUtilisateurs();

    @POST("dbTaCoTel/UtilisateurConnecte")
    //Observable<String>  registerUser(@Body Utilisateur user);
    Observable<Utilisateur> registerUser(@Body Utilisateur user);

    @POST("dbTaCoTel/UtilisateurConnecte/authenticate")
    Observable<Utilisateur>  loginUser(@Body Login login);
    //Observable<String> loginUser(@Body Utilisateur user);
}
