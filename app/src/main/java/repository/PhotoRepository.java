package repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.PhotoService;
import model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepository {

    public PhotoRepository() {
    }

    private PhotoService getPhotoService() {
        return ApiClient.getClient().create(PhotoService.class);
    }

    public LiveData<List<Photo>> query() {
        final MutableLiveData<List<Photo>> mutableLiveData = new MutableLiveData<>();
            getPhotoService().getPhotos().enqueue(new Callback<List<Photo>>() {
                @Override
                public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Photo>> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }

    public LiveData<Photo> create(Photo photo) {
        final MutableLiveData<Photo> mutableLiveData = new MutableLiveData<>();
            getPhotoService().postPhoto(photo).enqueue(new Callback<Photo>() {
                @Override
                public void onResponse(Call<Photo> call, Response<Photo> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<Photo> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }
}
