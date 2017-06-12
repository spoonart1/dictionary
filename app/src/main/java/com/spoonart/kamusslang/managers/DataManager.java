package com.spoonart.kamusslang.managers;

import com.spoonart.kamusslang.models.Data;
import com.spoonart.kamusslang.services.KamusService;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * Created by lafran on 5/28/17.
 */

@Singleton public class DataManager {
  private KamusService service;

  @Inject public DataManager(KamusService service){
    this.service = service;
  }

  public Observable<Data> getResultData(String word){
    return service.getResultData(word);
  }
}
