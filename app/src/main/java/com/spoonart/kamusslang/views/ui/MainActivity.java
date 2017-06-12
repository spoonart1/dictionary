package com.spoonart.kamusslang.views.ui;

import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.spoonart.kamusslang.R;
import com.spoonart.kamusslang.base.BaseActivity;
import com.spoonart.kamusslang.injections.component.ActivityComponent;
import com.spoonart.kamusslang.models.Data;
import com.spoonart.kamusslang.views.MainInterface;

public class MainActivity extends BaseActivity<MainPresenter> implements MainInterface{

  @Bind(R.id.recyclerview) RecyclerView recyclerView;
  @Bind(R.id.et_text) AppCompatAutoCompleteTextView etText;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    presenter.attachView(this);
    init();
  }

  @Override protected void initDependencies(ActivityComponent appComponent) {
    appComponent.inject(this);
  }

  private void init() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  @OnClick(R.id.btn_search) void onSearchWord(View btn){
    String word = etText.getText().toString();
    presenter.getData(word);
  }

  @Override public void showResultData(Data data) {

  }
}
