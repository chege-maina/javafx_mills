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
    @POST("addnewitem.php")
    Call<CustHelp> addItem(@Field("itemdetails") String itemdetails);

    @FormUrlEncoded
    @POST("addusers.php")
    Call<CustHelp> addUsers(@Field("fname") String fname, @Field("lname") String lname, @Field("email") String email, @Field("password") String password, @Field("designation") String designation, @Field("status") String status, @Field("forgot") String forgot);

    @FormUrlEncoded
    @POST("updatecustomers.php")
    Call<CustHelp> upcust(@Field("id") int id, @Field("name") String name, @Field("phone") String phone, @Field("estate") String estate, @Field("house") String house);

    @FormUrlEncoded
    @POST("updateitems.php")
    Call<CustHelp> upitm(@Field("id") int id, @Field("name") String name, @Field("phone") String phone, @Field("estate") String estate);

    @GET("searchcustomers.php")
    Call<List<CustHelp>> getCust(@Query("key") String keyword);

    @GET("searchstaff.php")
    Call<List<CustHelp>> getpin(@Query("key") String keyword);
    
    @GET("loademail.php")
    Call<List<CustHelp>> loadEmails();
    
    @GET("loadcategory.php")
    Call<List<CustHelp>> loadCategory();
    
    @GET("loadunit.php")
    Call<List<CustHelp>> loadUnit();
    
    @GET("salesitems.php")
    Call<List<CustHelp>> loadItems();
}
