package abdeljalil.nadif.urmobilecodingchallenge.service;

import abdeljalil.nadif.urmobilecodingchallenge.model.Repos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This service will call the API using the GET method of the HTTP
 * and adding the needed path and queries such as 'q' ,'sort', 'order" and 'page'
 */

public interface ApiService {

    @GET("search/repositories")
    Call<Repos> getRepos(@Query("q") String s, @Query("sort") String s2,
                         @Query("oder") String s3, @Query("page") String s4);

}
