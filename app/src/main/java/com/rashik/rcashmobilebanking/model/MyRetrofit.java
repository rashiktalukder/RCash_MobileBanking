package com.rashik.rcashmobilebanking.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rashik.rcashmobilebanking.interfaces.WebService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    public static Retrofit api;
    public static WebService service;

    public static WebService getInstance()
    {
        if(service==null)
        {
            if(api==null)
            {
                CreateRetrofit();

            }
            service=api.create(WebService.class);
            return service;
        }
        else
        {
            return service;
        }

    }

    public static void CreateRetrofit()
    {
            Gson gson=new GsonBuilder().setLenient().create();
            api=new Retrofit.Builder()
                    .baseUrl("http://localhost/api/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
    }




}
