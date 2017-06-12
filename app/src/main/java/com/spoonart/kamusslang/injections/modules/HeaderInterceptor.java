package com.spoonart.kamusslang.injections.modules;

import android.content.Context;
import com.spoonart.kamusslang.base.BaseApplication;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.Request;

/**
 * Created by lafran on 4/14/17.
 */

public class HeaderInterceptor implements Interceptor {
  private Context context;

  public HeaderInterceptor(Context context) {
    this.context = context;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    BaseApplication.get(context).getApplicationComponent().inject(this);
    Request request = chain.request();
    Request newRequest;
    newRequest = request.newBuilder()
        .addHeader("Authorization", "Bearer ")
        .build();
    return chain.proceed(newRequest);
  }
}
