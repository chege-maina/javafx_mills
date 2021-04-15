package com.mohware.mills.dashboard;

import com.mohware.mills.model.CustHelp;
import java.util.List;

public interface DashboardView {

    void onGetResult(List<CustHelp> category);

    void onGetUnitResult(List<CustHelp> unit);

    void onErrorLoading(String message);

    void onAddSuccess(String message);

    void onAddError(String message);

    void hideProgress();

    void showProgress();

    void onUnitAddSuccess(String message);

}
