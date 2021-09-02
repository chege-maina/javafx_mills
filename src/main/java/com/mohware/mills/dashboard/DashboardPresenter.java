package com.mohware.mills.dashboard;

import com.mohware.mills.api.ApiClient;
import com.mohware.mills.api.ApiInterface;
import com.mohware.mills.model.CustHelp;
import com.mohware.mills.model.RecModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter {

    private DashboardView view;

    public DashboardPresenter(DashboardView view) {
        this.view = view;

    }

    public void getCategory() {
        //view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<CustHelp>> call = apiInterface.loadCategory();
        call.enqueue(new Callback<List<CustHelp>>() {
            @Override
            public void onResponse(Call<List<CustHelp>> call, Response<List<CustHelp>> response) {

                //view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            public void onFailure(Call<List<CustHelp>> call, Throwable t) {
                //view.hideLoading();
                String error = "Internet Connection Error...";
                view.onErrorLoading(error);

            }
        });
    }

    public void getUnit() {
        //view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<CustHelp>> call = apiInterface.loadUnit();
        call.enqueue(new Callback<List<CustHelp>>() {
            @Override
            public void onResponse(Call<List<CustHelp>> call, Response<List<CustHelp>> response) {

                //view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetUnitResult(response.body());
                }
            }

            public void onFailure(Call<List<CustHelp>> call, Throwable t) {
                //view.hideLoading();
                String error = "Internet Connection Error...";
                view.onErrorLoading(error);

            }
        });
    }

    public void addItems(final String itemdetails) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CustHelp> call = apiInterface.addItem(itemdetails);

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
    
    public void editItem(final String itemdetails) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CustHelp> call = apiInterface.editItems(itemdetails);

        call.enqueue(new Callback<CustHelp>() {
            @Override
            public void onResponse(Call<CustHelp> call, Response<CustHelp> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onUpdateSuccess(response.body().getMessage());

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
    
    public void itemDispatch(final String itemdetails) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CustHelp> call = apiInterface.dispatchItems(itemdetails);

        call.enqueue(new Callback<CustHelp>() {
            @Override
            public void onResponse(Call<CustHelp> call, Response<CustHelp> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onDispatch(response.body().getMessage());

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
    
    public void itemStatus(final String itemdetails, final String code) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CustHelp> call = apiInterface.Itemstatus(itemdetails, code);

        call.enqueue(new Callback<CustHelp>() {
            @Override
            public void onResponse(Call<CustHelp> call, Response<CustHelp> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onUpdateSuccess(response.body().getMessage());

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
    
    public void listProducts() {
        //view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<CustHelp>> call = apiInterface.listProd();
        call.enqueue(new Callback<List<CustHelp>>() {
            @Override
            public void onResponse(Call<List<CustHelp>> call, Response<List<CustHelp>> response) {

                //view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.loadProductsList(response.body());
                }
            }

            public void onFailure(Call<List<CustHelp>> call, Throwable t) {
                //view.hideLoading();
                String error = "Internet Connection Error...";
                view.onErrorLoading(error);

            }
        });
    }

    public void editProd(String code) {
        //view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<CustHelp>> call = apiInterface.getProd(code);
        call.enqueue(new Callback<List<CustHelp>>() {
            @Override
            public void onResponse(Call<List<CustHelp>> call, Response<List<CustHelp>> response) {

                //view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.editprod(response.body());
                }
            }

            public void onFailure(Call<List<CustHelp>> call, Throwable t) {
                //view.hideLoading();
                String error = "Internet Connection Error...";
                String what = t.toString();
                view.onErrorLoading(error);

            }
        });
    }
    
    public void getRecItems(String code) {
        //view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<CustHelp>> call = apiInterface.getRecItems(code);
        call.enqueue(new Callback<List<CustHelp>>() {
            @Override
            public void onResponse(Call<List<CustHelp>> call, Response<List<CustHelp>> response) {

                //view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.recItems(response.body());
                }
            }

            public void onFailure(Call<List<CustHelp>> call, Throwable t) {
                //view.hideLoading();
                String error = "Internet Connection Error...";
                String what = t.toString();
                view.onErrorLoading(error);

            }
        });
    }
    
    public void listDispatch() {
        //view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<RecModel>> call = apiInterface.dispatchList();
        call.enqueue(new Callback<List<RecModel>>() {
            @Override
            public void onResponse(Call<List<RecModel>> call, Response<List<RecModel>> response) {

                //view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.loadDispatchList(response.body());
                }
            }

            public void onFailure(Call<List<RecModel>> call, Throwable t) {
                //view.hideLoading();
                String error = "Internet Connection Error...";
                view.onErrorLoading(error);

            }
        });
    }
    
    public void listProductsSrc(final String key) {
        //view.showLoading();
        System.out.println(key);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<CustHelp>> call = apiInterface.getProdsrc(key);
        call.enqueue(new Callback<List<CustHelp>>() {
            @Override
            public void onResponse(Call<List<CustHelp>> call, Response<List<CustHelp>> response) {

                //view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    
                    view.loadProductsList(response.body());
                }
            }

            public void onFailure(Call<List<CustHelp>> call, Throwable t) {
                //view.hideLoading();
                String error = "Internet Connection Error...";
                view.onErrorLoading(error);

            }
        });
    }
}
