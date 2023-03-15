package com.grozziie.testingApplications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChatGptApi {
    @POST("/generate")
    Call<ChatGptResponse> generate(@Body ChatGptRequest request);
}
