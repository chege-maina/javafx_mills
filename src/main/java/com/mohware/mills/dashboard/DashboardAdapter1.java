package com.mohware.mills.dashboard;

import com.mohware.mills.model.CustHelp;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.kairos.components.RippleViewRow;
import org.kairos.core.Context;
import org.kairos.layouts.RecyclerView;

public class DashboardAdapter1 extends RecyclerView.Adapter<DashboardAdapter1.Holder> {
    
    private List<CustHelp> items;

    public DashboardAdapter1(List<CustHelp> items) {
        
        this.items = items;
    }
    
    

    @Override
    public Holder onCreateViewHolder(FXMLLoader fxmlLoader) {

        fxmlLoader.setLocation(dash1.class.getResource("/rec_items/item_row.fxml"));
        //return new Holder(fxmlLoader);
        return new Holder(fxmlLoader);
    }

    @Override
    public RecyclerView.ViewRow call(ListView param) {
        return new RippleViewRow(this);
    }

    @Override
    public void onBindViewHolder(Holder holder, Object object) {

        CustHelp item = (CustHelp) object;
        holder.title.setText(item.getWaiter());
        
    }

    public static class Holder extends RecyclerView.ViewHolder {

        @FXML
        Label title;

        public Holder(FXMLLoader loader) {
            super(loader);
        }
    }
}