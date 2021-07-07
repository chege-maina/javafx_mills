package com.mohware.mills.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class RecModel {

    @Expose
    @SerializedName("rec_no")
    private String rec_no;
    @Expose
    @SerializedName("rec_date")
    private String rec_date;
    @Expose
    @SerializedName("rec_time")
    private String rec_time;
    @Expose
    @SerializedName("rec_Atotal")
    private String rec_Atotal;
    @Expose
    @SerializedName("rec_cash")
    private String rec_cash;
    @Expose
    @SerializedName("rec_mpesa")
    private String rec_mpesa;
    @Expose
    @SerializedName("rec_change")
    private String rec_change;
    @Expose
    @SerializedName("rec_transcode")
    private String rec_transcode;
    @Expose
    @SerializedName("rec_subtotal")
    private String rec_subtotal;
    @Expose
    @SerializedName("rec_taxamt")
    private String rec_taxamt;
    @Expose
    @SerializedName("rec_user")
    private String rec_user;
    @Expose
    @SerializedName("rec_status")
    private String rec_status;
    @Expose
    @SerializedName("rec_customer")
    private String rec_customer;
    @Expose
    @SerializedName("rec_items")
    private ArrayList<Items> rec_items;

    public class Items {

        @Expose
        @SerializedName("rec_item")
        private String rec_item;
        @Expose
        @SerializedName("rec_qty")
        private String rec_qty;
        @Expose
        @SerializedName("rec_price")
        private String rec_price;
        @Expose
        @SerializedName("rec_total")
        private String rec_total;
        @Expose
        @SerializedName("rec_unit")
        private String rec_unit;

        public String getRec_item() {
            return rec_item;
        }

        public void setRec_item(String rec_item) {
            this.rec_item = rec_item;
        }

        public String getRec_qty() {
            return rec_qty;
        }

        public void setRec_qty(String rec_qty) {
            this.rec_qty = rec_qty;
        }

        public String getRec_price() {
            return rec_price;
        }

        public void setRec_price(String rec_price) {
            this.rec_price = rec_price;
        }

        public String getRec_total() {
            return rec_total;
        }

        public void setRec_total(String rec_total) {
            this.rec_total = rec_total;
        }

        public String getRec_unit() {
            return rec_unit;
        }

        public void setRec_unit(String rec_unit) {
            this.rec_unit = rec_unit;
        }
        
        
    }

    public String getRec_no() {
        return rec_no;
    }

    public void setRec_no(String rec_no) {
        this.rec_no = rec_no;
    }

    public String getRec_date() {
        return rec_date;
    }

    public void setRec_date(String rec_date) {
        this.rec_date = rec_date;
    }

    public String getRec_time() {
        return rec_time;
    }

    public void setRec_time(String rec_time) {
        this.rec_time = rec_time;
    }

    public String getRec_Atotal() {
        return rec_Atotal;
    }

    public void setRec_Atotal(String rec_Atotal) {
        this.rec_Atotal = rec_Atotal;
    }

    public String getRec_cash() {
        return rec_cash;
    }

    public void setRec_cash(String rec_cash) {
        this.rec_cash = rec_cash;
    }

    public String getRec_mpesa() {
        return rec_mpesa;
    }

    public void setRec_mpesa(String rec_mpesa) {
        this.rec_mpesa = rec_mpesa;
    }

    public String getRec_change() {
        return rec_change;
    }

    public void setRec_change(String rec_change) {
        this.rec_change = rec_change;
    }

    public String getRec_transcode() {
        return rec_transcode;
    }

    public void setRec_transcode(String rec_transcode) {
        this.rec_transcode = rec_transcode;
    }

    public String getRec_subtotal() {
        return rec_subtotal;
    }

    public void setRec_subtotal(String rec_subtotal) {
        this.rec_subtotal = rec_subtotal;
    }

    public String getRec_taxamt() {
        return rec_taxamt;
    }

    public void setRec_taxamt(String rec_taxamt) {
        this.rec_taxamt = rec_taxamt;
    }

    public String getRec_user() {
        return rec_user;
    }

    public void setRec_user(String rec_user) {
        this.rec_user = rec_user;
    }

    public String getRec_status() {
        return rec_status;
    }

    public void setRec_status(String rec_status) {
        this.rec_status = rec_status;
    }

    public String getRec_customer() {
        return rec_customer;
    }

    public void setRec_customer(String rec_customer) {
        this.rec_customer = rec_customer;
    }

    public ArrayList<RecModel.Items> getRec_items() {
        return rec_items;
    }

    public void setRec_items(ArrayList<RecModel.Items> rec_items) {
        this.rec_items = rec_items;
    }
    

}
