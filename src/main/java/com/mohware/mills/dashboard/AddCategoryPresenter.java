
package com.mohware.mills.dashboard;
import com.mohware.mills.api.ApiClient;
import com.mohware.mills.api.ApiInterface;
import com.mohware.mills.model.CustHelp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCategoryPresenter {
    
    
    private DashboardView view;
    public AddCategoryPresenter(DashboardView view) {
        this.view = view;
    }
    
    public void addCategory(final String category) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CustHelp> call = apiInterface.addCategory(category);

        call.enqueue(new Callback<CustHelp>() {
            @Override
            public void onResponse(Call<CustHelp> call, Response<CustHelp> response) {

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
                String error= "Internet Connection Error...";
                view.onAddError(error);

            }
        });

    }
    
    public void addUnit(final String category) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CustHelp> call = apiInterface.addUnit(category);

        call.enqueue(new Callback<CustHelp>() {
            @Override
            public void onResponse(Call<CustHelp> call, Response<CustHelp> response) {

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
                String error= "Internet Connection Error...";
                view.onAddError(error);

            }
        });

    }
    
}
