
package com.mohware.mills.login;

import com.mohware.mills.api.ApiClient;
import com.mohware.mills.api.ApiInterface;
import com.mohware.mills.model.CustHelp;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginPresenter {
    
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

public void Login(final String email, final String password) {


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CustHelp> call = apiInterface.login(email, password);

        call.enqueue(new Callback<CustHelp>() {
            @Override
            public void onResponse(Call<CustHelp> call, Response<CustHelp> response) {
                
                if (response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onAddSuccess(response.body().getMessage(), response.body().getFname(), response.body().getLname(), response.body().getDesignation(), response.body().getStatus());


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
