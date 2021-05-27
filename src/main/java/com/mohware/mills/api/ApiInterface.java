package com.mohware.mills.api;

import com.mohware.mills.model.CustHelp;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    
    @FormUrlEncoded
    @POST("login.php")
    Call<CustHelp> login(@Field("email") String email, @Field("password") String password);

   @FormUrlEncoded
    @POST("addcategory.php")
    Call<CustHelp> addCategory(@Field("category") String category);
    
    @FormUrlEncoded
    @POST("addunit.php")
    Call<CustHelp> addUnit(@Field("category") String category);
    
    @FormUrlEncoded
    @POST("add_product.php")
    Call<CustHelp> addItem(@Field("itemdetails") String itemdetails);
            
    @GET("loademail.php")
    Call<List<CustHelp>> loadEmails();
    
    @GET("loadcategory.php")
    Call<List<CustHelp>> loadCategory();
    
    @GET("listproducts.php")
    Call<List<CustHelp>> listProd();
    
    @GET("loadunit.php")
    Call<List<CustHelp>> loadUnit();
    
    @GET("salesitems.php")
    Call<List<CustHelp>> loadItems();
}
