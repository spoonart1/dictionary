package com.spoonart.kamusslang.injections.component;

import android.content.Context;
import com.spoonart.kamusslang.injections.ActivityContext;
import com.spoonart.kamusslang.injections.PerActivity;
import com.spoonart.kamusslang.injections.modules.ActivityModule;
import com.spoonart.kamusslang.managers.DataManager;
import com.spoonart.kamusslang.services.KamusService;
import com.spoonart.kamusslang.views.ui.MainActivity;
import dagger.Component;

/**
 * Created by lafran on 4/14/17.
 */

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  @ActivityContext Context context();

  void inject(MainActivity activity);
}
