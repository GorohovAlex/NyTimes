package api;

import java.util.List;

import pojo.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NyTimesApi {
    String apiKey = "251a3a2ad22a4694837f4cb1ee026e87";

    @GET("mostemailed/all-sections/30.json?api-key=" + apiKey)
    Call<News> mostEmailed();

    @GET("mostshared/all-sections/30.json?api-key=" + apiKey)
    Call<News> mostShared();

    @GET("mostviewed/all-sections/30.json?api-key=" + apiKey)
    Call<News> mostViewed();
}
