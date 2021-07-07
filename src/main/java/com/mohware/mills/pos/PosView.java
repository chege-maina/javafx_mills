
package com.mohware.mills.pos;

import java.util.List;
import com.mohware.mills.model.CustHelp;
import com.mohware.mills.model.RecModel;

public interface PosView {
    void showLoading();
    void showProgress();
    void hideLoading();
    void hideProgress();
    void onGetResult(List<CustHelp> items);
    void onGetRec(List<RecModel> rec);
    void onErrorLoading(String message);
    void onAddError(String message);
    void onAddSuccess(String message);
}
