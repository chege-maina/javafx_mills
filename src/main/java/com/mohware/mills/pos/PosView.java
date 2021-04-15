
package com.mohware.mills.pos;

import java.util.List;
import com.mohware.mills.model.CustHelp;

public interface PosView {
    void showLoading();
    void showProgress();
    void hideLoading();
    void hideProgress();
    void onGetResult(List<CustHelp> items);
    void onErrorLoading(String message);
}
