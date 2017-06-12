package com.spoonart.kamusslang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.spoonart.kamusslang.injections.component.ActivityComponent;
import com.spoonart.kamusslang.injections.component.DaggerActivityComponent;
import com.spoonart.kamusslang.injections.modules.ActivityModule;
import javax.inject.Inject;

/**
 * Created by lafran on 4/14/17.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

  @Inject protected T presenter;

  private ActivityComponent activityComponent;
  public ActivityComponent getActivityComponent(){
    if(activityComponent == null){
      activityComponent = DaggerActivityComponent.builder()
          .activityModule(new ActivityModule(this))
          .applicationComponent(BaseApplication.get(this).getApplicationComponent())
          .build();
    }

    return activityComponent;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initDependencies(getActivityComponent());
  }

  protected abstract void initDependencies(ActivityComponent appComponent);

}
