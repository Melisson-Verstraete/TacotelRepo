package api;

import java.util.List;

import model.Client;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClientService {

    @GET("dbTaCoTel/client")
    Call<List<Client>> getClients();

    @POST("dbTaCoTel/client")
    Call<Client> postClient(@Body Client client);
}
