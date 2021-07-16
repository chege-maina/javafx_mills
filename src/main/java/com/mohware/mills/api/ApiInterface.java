package com.mohware.mills.api;

import com.mohware.mills.model.CustHelp;
import com.mohware.mills.model.RecModel;
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
    
    @FormUrlEncoded
    @POST("makesale.php")
    Call<CustHelp> MakeSale(@Field("itemdetails") String itemdetails, @Field("recitems") String recitems);
    
    @FormUrlEncoded
    @POST("update_product.php")
    Call<CustHelp> editItems(@Field("itemdetails") String itemdetails);
    
    @FormUrlEncoded
    @POST("product_status.php")
    Call<CustHelp> Itemstatus(@Field("itemdetails") String itemdetails, @Field("code") String code);
            
    @GET("loademail.php")
    Call<List<CustHelp>> loadEmails();
    
    @GET("loadcategory.php")
    Call<List<CustHelp>> loadCategory();
    
    @GET("listproducts.php")
    Call<List<CustHelp>> listProd();
    
    @GET("dispatchlist.php")
    Call<List<RecModel>> dispatchList();
    
    @GET("loadunit.php")
    Call<List<CustHelp>> loadUnit();
    
    @GET("load_pos_items.php")
    Call<List<CustHelp>> loadItems();
    
    @GET("get_rec.php")
    Call<List<RecModel>> loadRec();
    
    @GET("editproducts.php")
    Call<List<CustHelp>> getProd(@Query("code")String prodCode);
    
    @GET("listproducts_src.php")
    Call<List<CustHelp>> getProdsrc(@Query("key")String prodCode);
    
    @GET("search_pos_items.php")
    Call<List<CustHelp>> posSrc(@Query("key")String prodCode);
}
