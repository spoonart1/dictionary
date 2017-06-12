package com.spoonart.kamusslang.injections.modules;

import android.app.Application;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spoonart.kamusslang.injections.ApplicationContext;
import com.spoonart.kamusslang.services.KamusService;
import com.spoonart.kamusslang.utils.RxBus;
import dagger.Module;
import dagger.Provides;
import java.lang.reflect.Modifier;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

//import io.rx_cache.internal.RxCache;

/**
 * Created by lafran on 4/14/17.
 */

@Module public class ApplicationModule {

  protected final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides Application provideApplication() {
    return application;
  }

  @Provides @ApplicationContext Context provideContext() {
    return application;
  }

  @Provides @Singleton OkHttpClient provideHttpClient(@ApplicationContext Context context) {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor)
        .addInterceptor(new HeaderInterceptor(context))
        .build();
    return httpClient;
  }

  @Provides @Singleton Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
    return gsonBuilder.create();
  }

  @Provides KamusService provideCoreService(OkHttpClient httpClient, Gson gson) {
    return new KamusService.Creator().init(httpClient, gson);
  }

  @Provides @Singleton static RxBus provideRxBus() {
    return new RxBus();
  }

  /*@Provides CacheProviders provideCacheProvider(@ApplicationContext Context context) {
    File cacheDir = context.getFilesDir();
    return new RxCache.Builder().persistence(cacheDir).using(CacheProviders.class);
  }*/
}
