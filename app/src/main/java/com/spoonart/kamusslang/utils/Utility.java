package com.spoonart.kamusslang.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by lafran on 4/15/17.
 */

public class Utility {
  public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  public static boolean checkPermission(final Context context) {
    int currentAPIVersion = Build.VERSION.SDK_INT;
    if (currentAPIVersion >= Build.VERSION_CODES.M) {
      if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
          != PackageManager.PERMISSION_GRANTED) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
            Manifest.permission.READ_EXTERNAL_STORAGE)) {
          AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
          alertBuilder.setCancelable(true);
          alertBuilder.setTitle("Permission necessary");
          alertBuilder.setMessage("External storage permission is necessary");
          alertBuilder.setPositiveButton(android.R.string.yes,
              new DialogInterface.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                public void onClick(DialogInterface dialog, int which) {
                  ActivityCompat.requestPermissions((Activity) context,
                      new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                      MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
              });
          AlertDialog alert = alertBuilder.create();
          alert.show();
        } else {
          ActivityCompat.requestPermissions((Activity) context,
              new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
              MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
        return false;
      } else {
        return true;
      }
    } else {
      return true;
    }
  }

  public final static boolean isValidEmail(CharSequence target) {
    if (target == null) {
      return false;
    } else {
      return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
  }

  public final static int pickColor() {
    int[] colors = new int[] {
        Color.parseColor("#f44336"), Color.parseColor("#FF9800"), Color.parseColor("#9C27B0"),
        Color.parseColor("#2196F3"), Color.parseColor("#9E9E9E"), Color.parseColor("#607D8B"),
        Color.parseColor("#795548"), Color.parseColor("#FF5722"), Color.parseColor("#FF9800"),
        Color.parseColor("#FFEB3B")
    };
    int rdm = (int) (Math.random() * colors.length);
    return colors[rdm];
  }

  public static String ifNull(String source) {
    if (source == null) {
      return "null";
    } else if (source.equalsIgnoreCase("null")) return "null";
    return source;
  }

  public static boolean checkDataBase(Context context) {
    File database = context.getDatabasePath("mydb.db");

    if (!database.exists()) {
      // Database does not exist so copy it from assets here
      System.out.println("Utility.checkDataBase exists");
      return true;
    } else {
      System.out.println("Utility.checkDataBase not exists");
      return false;
    }
  }

  /*public static void exportDB(Context context) {
    File sd = Environment.getExternalStorageDirectory();
    File data = Environment.getDataDirectory();
    FileChannel source = null;
    FileChannel destination = null;
    String currentDBPath =
        "/data/" + context.getPackageName() + "/databases/" + DatabaseManager.DATABASE_NAME;
    String backupDBPath = DatabaseManager.DATABASE_NAME;
    File currentDB = new File(data, currentDBPath);
    File backupDB = new File(sd, backupDBPath);
    try {
      source = new FileInputStream(currentDB).getChannel();
      destination = new FileOutputStream(backupDB).getChannel();
      destination.transferFrom(source, 0, source.size());
      source.close();
      destination.close();
      Toast.makeText(context, "DB Exported!", Toast.LENGTH_LONG).show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }*/
}
