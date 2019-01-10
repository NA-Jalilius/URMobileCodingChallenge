package abdeljalil.nadif.urmobilecodingchallenge.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import abdeljalil.nadif.urmobilecodingchallenge.model.Repos;
import abdeljalil.nadif.urmobilecodingchallenge.service.ApiService;


public class RetrofitController implements Callback<Repos>  {

    static final String BASE_URL = "https://api.github.com/";
    static Repos allRepos;
    static Retrofit retrofit;

    public static void setAllRepos(Repos allRepos) {
        RetrofitController.allRepos = allRepos;
    }

    public static Repos getAllRepos() {
        return allRepos;
    }

    public void start(String page) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService apiService = RetrofitController.createService(ApiService.class);

        Call<Repos> call = apiService.getRepos("created:>2017-10-22","stars","desc", page);

        //call.enqueue(this);
        try {
            Repos reposLis = call.execute().body();
            System.out.println(reposLis);
            setAllRepos(reposLis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResponse(Call<Repos> call, Response<Repos> response) {
        if(response.isSuccessful()) {
            Repos reposList = response.body();
            System.out.println(reposList.getRepos());
            setAllRepos(reposList);
        } else {
            System.out.println(response.errorBody());
        }
    }

    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    @Override
    public void onFailure(Call<Repos> call, Throwable t) {
        t.printStackTrace();
    }
}

