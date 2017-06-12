package com.spoonart.kamusslang.views.ui;

import com.spoonart.kamusslang.base.BasePresenter;
import com.spoonart.kamusslang.managers.DataManager;
import com.spoonart.kamusslang.models.Data;
import com.spoonart.kamusslang.services.KamusService;
import com.spoonart.kamusslang.utils.SimpleObserver;
import com.spoonart.kamusslang.views.MainInterface;
import javax.inject.Inject;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lafran on 5/28/17.
 */

public class MainPresenter extends BasePresenter<MainInterface> {

  private DataManager dm;
  private KamusService service;
  private CompositeSubscription subscription = new CompositeSubscription();

  @Inject
  public MainPresenter(DataManager dm, KamusService service){
    this.dm = dm;
    this.service = service;
  }

  public void getData(String word){
    subscription.add(dm.getResultData(word)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SimpleObserver<Data>() {
          Data data;
          @Override public void onCompleted() {
            mvpView.showResultData(data);
          }

          @Override public void onNext(Data data) {
            this.data = data;
          }
        })
    );
  }
}
