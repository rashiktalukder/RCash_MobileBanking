package com.rashik.rcashmobilebanking.interfaces;

import okhttp3.ResponseBody;
import retrofit2.http.POST;

public interface WebService {

    @POST
    ResponseBody loginNow();

}
