package com.example.trialxcinema.DashboardActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("http://api.themoviedb.org/3/movie/popular")
        Call<MovieResponse> getAllMovieImages(
                @Query("api_key") String apiKey);
}
 //http://api.themoviedb.org/3/movie/popular?api_key=90787843a200cfbfd55b14b39270f6a1
