
package com.mohware.mills.pos;

import com.mohware.mills.api.ApiClient;
import com.mohware.mills.api.ApiInterface;
import com.mohware.mills.model.CustHelp;
import com.mohware.mills.model.RecModel;
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
    
    public void makeSale(final String itemdetails, final String recitems) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CustHelp> call = apiInterface.MakeSale(itemdetails, recitems);

        call.enqueue(new Callback<CustHelp>() {
            @Override
            public void onResponse(Call<CustHelp> call, Response<CustHelp> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onAddSuccess(response.body().getMessage());

                    } else {
                        view.onAddError(response.body().getMessage());

                    }

                }
            }

            @Override
            public void onFailure(Call<CustHelp> call, Throwable t) {
                view.hideProgress();
                String error = "Internet Connection Error...";
                view.onAddError(error);

            }
        });

    }
    
    public void getRec() {
        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<RecModel>> call = apiInterface.loadRec();
        call.enqueue(new Callback<List<RecModel>>() {
            @Override
            public void onResponse(Call<List<RecModel>> call, Response<List<RecModel>> response) {

                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetRec(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RecModel>> call, Throwable t) {
                view.hideLoading();
                String error = "Internet Connection Error...";
                view.onErrorLoading(t.toString());

            }
        });
    }
    
}
