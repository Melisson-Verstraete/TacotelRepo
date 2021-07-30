package api;

import java.util.List;

import model.Photo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PhotoService {

    @GET("dbTaCoTel/photo")
    Call<List<Photo>> getPhotos();

    @POST("dbTaCoTel/photo")
    Call<Photo> postPhoto(@Body Photo photo);
}
