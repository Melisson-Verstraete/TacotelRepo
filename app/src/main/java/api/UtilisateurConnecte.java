package api;


import io.reactivex.Observable;
import model.Login;
import model.Utilisateur;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UtilisateurConnecte {
    @POST("dbTaCoTel/UtilisateurConnecte")
    //Observable<String>  registerUser(@Body Utilisateur user);
    Observable<Utilisateur> registerUser(@Body Utilisateur user);

    @POST("dbTaCoTel/UtilisateurConnecte/authenticate")
    Observable<Utilisateur>  loginUser(@Body Login login);
    //Observable<String> loginUser(@Body Utilisateur user);

}
