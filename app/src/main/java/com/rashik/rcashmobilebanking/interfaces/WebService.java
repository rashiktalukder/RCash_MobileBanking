package com.rashik.rcashmobilebanking.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebService {

    @GET("logincash.php")
    Call<ResponseBody> loginNow(@Query("number") String number, @Query("pin") String pin);



}
