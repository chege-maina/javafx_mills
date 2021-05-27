package com.mohware.mills.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustHelp {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("itemdetails")
    private String itemdetails;
    @SerializedName("checksqty")
    private String checksqty;
    @Expose
    @SerializedName("waiter")
    private String waiter;
    @Expose
    @SerializedName("category")
    private String category;
    @Expose
    @SerializedName("product_code")
    private String product_code;
    @Expose
    @SerializedName("product_name")
    private String product_name;
    @Expose
    @SerializedName("buying_price")
    private String buying_price;
    @Expose
    @SerializedName("selling_price")
    private String selling_price;

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(String buying_price) {
        this.buying_price = buying_price;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getItemdetails() {
        return itemdetails;
    }

    public void setItemdetails(String itemdetails) {
        this.itemdetails = itemdetails;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Expose
    @SerializedName("arraylist")
    private ArrayList<ArrayList<String>> arrayLists;

    @Expose
    @SerializedName("orderno")
    private String orderno;
    @Expose
    @SerializedName("date")
    private String date;
    @Expose
    @SerializedName("customer")
    private String customer;
    @Expose
    @SerializedName("time")
    private String time;
    @Expose
    @SerializedName("cashier")
    private String cashier;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("amt")
    private String amt;
    @Expose
    @SerializedName("initiator")
    private String initiator;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("estate")
    private String estate;
    @Expose
    @SerializedName("house")
    private String house;
    @Expose
    @SerializedName("item")
    private String item;
    @Expose
    @SerializedName("price")
    private String price;
    @Expose
    @SerializedName("qty")
    private String qty;
    @Expose
    @SerializedName("total")
    private String total;
    @Expose
    @SerializedName("cost")
    private String cost;
    @Expose
    @SerializedName("path")
    private String path;
    @Expose
    @SerializedName("manager")
    private String manager;
    @Expose
    @SerializedName("encoded_string")
    private String encoded_string;
    @SerializedName("countorder")
    private String countorder;
    @Expose
    @SerializedName("success")
    private Boolean success;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("checkqty")
    private String checkqty;
    @Expose
    @SerializedName("pay_order")
    private String pay_order;
    @Expose
    @SerializedName("pay_name")
    private String pay_name;
    @Expose
    @SerializedName("pay_house")
    private String pay_house;
    @Expose
    @SerializedName("pay_estate")
    private String pay_estate;
    @Expose
    @SerializedName("pay_total")
    private String pay_total;
    @Expose
    @SerializedName("pay_paid")
    private String pay_paid;
    @Expose
    @SerializedName("pay_balance")
    private String pay_balance;
    @Expose
    @SerializedName("pay_phone")
    private String pay_phone;
    @Expose
    @SerializedName("paymode")
    private String paymode;
    @Expose
    @SerializedName("code")
    private String code;
    @Expose
    @SerializedName("forgot")
    private String forgot;
    @Expose
    @SerializedName("fname")
    private String fname;
    @Expose
    @SerializedName("lname")
    private String lname;
    @Expose
    @SerializedName("pin")
    private String pin;
    @Expose
    @SerializedName("designation")
    private String designation;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("password")
    private String password;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public String getFname() {
        return fname;
    }

    public String getForgot() {
        return forgot;
    }

    public void setForgot(String forgot) {
        this.forgot = forgot;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPath() {
        return path;
    }

    public String getCountorder() {
        return countorder;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public void setCountorder(String countorder) {
        this.countorder = countorder;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getEncoded_string() {
        return encoded_string;
    }

    public void setEncoded_string(String encoded_string) {
        this.encoded_string = encoded_string;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCheckqty() {
        return checkqty;
    }

    public void setCheckqty(String checkqty) {
        this.checkqty = checkqty;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getPay_order() {
        return pay_order;
    }

    public void setPay_order(String pay_order) {
        this.pay_order = pay_order;
    }

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public String getPay_house() {
        return pay_house;
    }

    public void setPay_house(String pay_house) {
        this.pay_house = pay_house;
    }

    public String getPay_estate() {
        return pay_estate;
    }

    public void setPay_estate(String pay_estate) {
        this.pay_estate = pay_estate;
    }

    public String getPay_total() {
        return pay_total;
    }

    public void setPay_total(String pay_total) {
        this.pay_total = pay_total;
    }

    public String getPay_paid() {
        return pay_paid;
    }

    public void setPay_paid(String pay_paid) {
        this.pay_paid = pay_paid;
    }

    public String getPay_balance() {
        return pay_balance;
    }

    public void setPay_balance(String pay_balance) {
        this.pay_balance = pay_balance;
    }

    public String getPay_phone() {
        return pay_phone;
    }

    public ArrayList<ArrayList<String>> getArrayLists() {
        return arrayLists;
    }

    public void setArrayLists(ArrayList<ArrayList<String>> arrayLists) {
        this.arrayLists = arrayLists;
    }

    public void setPay_phone(String pay_phone) {
        this.pay_phone = pay_phone;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChecksqty() {
        return checksqty;
    }

    public void setChecksqty(String checksqty) {
        this.checksqty = checksqty;
    }
}
