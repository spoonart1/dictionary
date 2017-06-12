package com.spoonart.kamusslang.utils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lafran on 4/14/17.
 */
public class RxUtils {
  public static void unsubscribeIfNotNull(Subscription subscription) {
    if (subscription != null) {
      subscription.unsubscribe();
    }
  }

  public static CompositeSubscription getNewCompositeSubIfUnsubscribed(
      CompositeSubscription subscription) {
    if (subscription == null || subscription.isUnsubscribed()) {
      return new CompositeSubscription();
    }

    return subscription;
  }
}
