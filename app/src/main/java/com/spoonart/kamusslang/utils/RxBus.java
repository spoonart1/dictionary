package com.spoonart.kamusslang.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by lafran on 4/14/17.
 */
public class RxBus {
  // If multiple threads are going to emit events to this
  // then it must be made thread-safe like this instead
  private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

  public void send(Object o) {
    _bus.onNext(o);
  }

  public Observable<Object> toObserverable() {
    return _bus;
  }

  public boolean hasObservers() {
    return _bus.hasObservers();
  }
}
