package com.spoonart.kamusslang.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by lafran on 5/28/17.
 */

public class Data {

  @SerializedName("data") public ArrayList<Detail> details;

  public class Detail {
    public int id;
    @SerializedName("meta") public String author;
    public String description;
    public String example;
    public int like;
    public int dislike;
  }
}
