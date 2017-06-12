package com.spoonart.kamusslang.utils;

import android.util.Log;
import rx.Observer;
import rx.observers.Observers;

/**
 * Created by lafran on 4/14/17.
 */

public class SimpleObserver<T> implements Observer<T> {
  @Override public void onCompleted() {

  }

  @Override public void onError(Throwable e) {
    Log.e("Error Simple Observer: " , "MSG : "+e.getLocalizedMessage() + " | Error code: "+e.hashCode());
  }

  @Override public void onNext(T t) {

  }
}
