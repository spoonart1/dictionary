package com.spoonart.kamusslang.utils;

import com.spoonart.kamusslang.base.BaseModel;
import java.util.Comparator;

/**
 * Created by lafran on 4/23/17.
 */

public class SortComparator<T extends BaseModel> implements Comparator<T> {

  @Override public int compare(T o1, T o2) {
    int res = o1 == o2 ? 1 : 0;
    return res;
  }
}
