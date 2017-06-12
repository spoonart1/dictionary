package com.spoonart.kamusslang.injections.component;

import android.app.Application;
import android.content.Context;
import com.spoonart.kamusslang.injections.ApplicationContext;
import com.spoonart.kamusslang.injections.modules.ApplicationModule;
import com.spoonart.kamusslang.injections.modules.HeaderInterceptor;
import com.spoonart.kamusslang.managers.DataManager;
import com.spoonart.kamusslang.services.KamusService;
import com.spoonart.kamusslang.utils.RxBus;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by lafran on 4/14/17.
 */
@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  @ApplicationContext Context context();

  void inject(HeaderInterceptor interceptor);

  Application application();

  KamusService service();

  DataManager dm();

  RxBus rxBus();
}
