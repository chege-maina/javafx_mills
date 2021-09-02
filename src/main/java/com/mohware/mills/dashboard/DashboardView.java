package com.mohware.mills.dashboard;

import com.mohware.mills.model.CustHelp;
import com.mohware.mills.model.RecModel;
import java.util.List;

public interface DashboardView {

    void onGetResult(List<CustHelp> category);

    void onGetUnitResult(List<CustHelp> unit);
    
    void loadProductsList(List<CustHelp> lstProd);
    
    void editprod(List<CustHelp> Prod);
    
    void recItems(List<CustHelp> recitems);

    void onErrorLoading(String message);

    void onAddSuccess(String message);
    
    void onUpdateSuccess(String message);
    
    void onDispatch(String message);

    void onAddError(String message);

    void hideProgress();

    void showProgress();

    void onUnitAddSuccess(String message);

    void loadDispatchList(List<RecModel> body);

}
