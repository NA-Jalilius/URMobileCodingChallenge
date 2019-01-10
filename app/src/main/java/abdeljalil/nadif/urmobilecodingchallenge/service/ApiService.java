package abdeljalil.nadif.urmobilecodingchallenge.service;

import abdeljalil.nadif.urmobilecodingchallenge.model.Repos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/repositories")
    Call<Repos> getRepos(@Query("q") String s, @Query("sort") String s2,
                         @Query("oder") String s3, @Query("page") String s4);

}
