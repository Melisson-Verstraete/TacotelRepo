package repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.AdminService;
import api.ApiClient;
import model.Admin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminRepository {

    public AdminRepository() {
    }

    private AdminService getAdminService() {
        return ApiClient.getClient().create(AdminService.class);
    }

    public LiveData<List<Admin>> query() {
        final MutableLiveData<List<Admin>> mutableLiveData = new MutableLiveData<>();
            getAdminService().getAdmins().enqueue(new Callback<List<Admin>>() {
                @Override
                public void onResponse(Call<List<Admin>> call, Response<List<Admin>> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Admin>> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }

    public LiveData<Admin> create(Admin admin) {
        final MutableLiveData<Admin> mutableLiveData = new MutableLiveData<>();
            getAdminService().postAdmin(admin).enqueue(new Callback<Admin>() {
                @Override
                public void onResponse(Call<Admin> call, Response<Admin> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<Admin> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }
}
