package com.spoonart.kamusslang.base;

import android.app.ProgressDialog;

/**
 * Created by lafran on 4/14/17.
 */

public class BasePresenter<T>{

  protected ProgressDialog dialog;

  protected T mvpView;

  public void attachView(T view)
  {
    this.mvpView = view;
  }

  public void detachView()
  {
    if (mvpView != null)
    {
      mvpView = null;
    }
  }
  public boolean isViewAttached()
  {
    return mvpView != null;
  }

  public void checkViewAttached() {
    if (!isViewAttached()) throw new MvpViewNotAttachedException();
  }

  public static class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
      super(
          "Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter");
    }
  }
}
