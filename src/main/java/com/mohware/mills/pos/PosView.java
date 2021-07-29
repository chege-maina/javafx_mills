
package com.mohware.mills.pos;

import com.mohware.mills.model.CustHelp;
import java.util.List;
import com.mohware.mills.model.RecModel;

public interface PosView {
    void showLoading();
    void showProgress();
    void hideLoading();
    void hideProgress();
    void onGetResult(List<CustHelp> items);
    void onGetRec(List<RecModel> rec);
    void onErrorLoading(String message);
    void onGetRecSale(List<RecModel> rec);
    void onAddError(String message);
    void onAddSuccess(String message);

    void onGetsrcResult(List<CustHelp> body);
}
