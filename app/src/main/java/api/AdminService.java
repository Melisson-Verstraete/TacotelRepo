package api;

import java.util.List;

import model.Admin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AdminService {

    @GET("dbTaCoTel/Admin")
    Call<List<Admin>> getAdmins();

    @POST("dbTaCoTel/Admin")
    Call<Admin> postAdmin(@Body Admin admin);
}
