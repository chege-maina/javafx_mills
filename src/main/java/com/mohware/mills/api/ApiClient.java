
package com.mohware.mills.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //private static final String BASE_URL = "http://localhost:80/kelmo/";//www.bact.ac.ke/aberdarehttp://www.severinombae.net/hilljoint/
    private static final String BASE_URL = "http://www.severinombae.net/hilljoint/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
