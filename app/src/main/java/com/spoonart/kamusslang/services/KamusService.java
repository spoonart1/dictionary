package com.spoonart.kamusslang.services;

import com.google.gson.Gson;
import com.spoonart.kamusslang.models.Data;
import java.util.ArrayList;
import javax.inject.Inject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lafran on 4/2/17.
 */

public interface KamusService {

  public static final String SERVER_URL = "http://192.168.8.102:8989/kamus/";
  @GET("api/{word}") Observable<Data> getResultData(@Path("word") String word);
  public class Creator{
    @Inject public KamusService init(OkHttpClient client, Gson gson){
      Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_URL)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .client(client)
          .build();

      return retrofit.create(KamusService.class);
    }
  }
}
