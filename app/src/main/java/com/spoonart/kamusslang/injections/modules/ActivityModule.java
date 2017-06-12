package com.spoonart.kamusslang.injections.modules;

import android.app.Activity;
import android.content.Context;
import com.spoonart.kamusslang.injections.ActivityContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lafran on 4/14/17.
 */

@Module public class ActivityModule {

  private Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides Activity provideActivity() {
    return activity;
  }

  @Provides @ActivityContext Context provideContext() {
    return activity;
  }
}
