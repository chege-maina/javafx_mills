
package com.mohware.mills.pos;

import com.mohware.mills.api.ApiClient;
import com.mohware.mills.api.ApiInterface;
import com.mohware.mills.model.CustHelp;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PosPresenter {
    
    private PosView view;

    public PosPresenter(PosView view) {
        this.view = view;
    }

       
    public void getItems() {
        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<CustHelp>> call = apiInterface.loadItems();
        call.enqueue(new Callback<List<CustHelp>>() {
            @Override
            public void onResponse(Call<List<CustHelp>> call, Response<List<CustHelp>> response) {

                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CustHelp>> call, Throwable t) {
                view.hideLoading();
                String error = "Internet Connection Error...";
                view.onErrorLoading(error);

            }
        });
    }
    
}
