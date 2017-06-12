package com.spoonart.kamusslang.base;

import android.app.Application;
import android.content.Context;
import com.spoonart.kamusslang.injections.component.ApplicationComponent;
import com.spoonart.kamusslang.injections.component.DaggerApplicationComponent;
import com.spoonart.kamusslang.injections.modules.ApplicationModule;
import com.spoonart.kamusslang.utils.RxBus;

/**
 * Created by lafran on 4/14/17.
 */

public class BaseApplication extends Application {

  public static String class_name = null;
  private static RxBus rxBus;

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
  }

  public static BaseApplication get(Context context) {
    return (BaseApplication) context.getApplicationContext();
  }

  public ApplicationComponent getApplicationComponent() {
    if (applicationComponent == null) {
      applicationComponent = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(this))
          .build();
    }
    return applicationComponent;
  }

  public static RxBus getRxBus() {
    if (rxBus == null) {
      rxBus = new RxBus();
    }
    return rxBus;
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
  }
}
