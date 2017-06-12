package com.spoonart.kamusslang.services;

import com.google.gson.Gson;
import com.spoonart.kamusslang.models.ErrorModel;
import java.io.IOException;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by lafran on 4/14/17.
 */
public class ErrorUtils {
  public static String getErrorUserMessage(Throwable error) {
    if (error instanceof HttpException) {
      HttpException httpError = (HttpException) error;
      try {
        String messageBody = httpError.response().errorBody().string();
        ErrorModel errorModel = new Gson().fromJson(messageBody, ErrorModel.class);
        if(errorModel.error!=null) {
          System.out.println("Error 1 : "+errorModel.error);
          return errorModel.error;
        } else if(errorModel.userMessage!=null) {
          System.out.println("Error 2 : "+errorModel.userMessage);
          return errorModel.userMessage;
        } else {
          System.out.println("Error 3 : "+messageBody);
          return messageBody;
        }
      } catch (IOException e) {
        System.out.println(" IO Exception error "+e.getLocalizedMessage());
      } catch (Exception er) {
        System.out.println(" Exception error "+er.getLocalizedMessage());
      }
    } else if (error instanceof IOException) {
      System.out.println("  No connection ");
      return "No connection";
    }
    if(error.getLocalizedMessage() != null && error.getLocalizedMessage()
        .contains("The Loader provided did not return any data")) {
      return "Connection Error";
    }
    else
      return error.getLocalizedMessage();
  }
}
