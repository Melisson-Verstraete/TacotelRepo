package repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.ClientService;
import model.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientRepository {

    public ClientRepository() {
    }

    private ClientService getClientService() {
        return ApiClient.getClient().create(ClientService.class);
    }

    public LiveData<List<Client>> query() {
        final MutableLiveData<List<Client>> mutableLiveData = new MutableLiveData<>();
            getClientService().getClients().enqueue(new Callback<List<Client>>() {
                @Override
                public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Client>> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }

    public LiveData<Client> create(Client client) {
        final MutableLiveData<Client> mutableLiveData = new MutableLiveData<>();
            getClientService().postClient(client).enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {

                }
            });
        return mutableLiveData;
    }
}
