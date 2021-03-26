package com.ranju.taskapp.api;

import com.ranju.taskapp.model.PicsModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Holders {
    @GET("list")
    Call<List<PicsModel>> images();
}
