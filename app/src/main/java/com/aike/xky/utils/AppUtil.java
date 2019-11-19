package com.aike.xky.utils;

import android.support.annotation.Nullable;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 创建时间: 2019/11/19 15:22 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AppUtil {
  /**
   * 获得进程名
   */
  public static String getProcessName() {
    BufferedReader mBufferedReader = null;
    try {
      File file = new File("/proc/self/cmdline");
      mBufferedReader = new BufferedReader(new FileReader(file));
      return mBufferedReader.readLine().trim();
    } catch (Throwable e) {
    } finally {
      closeSilently(mBufferedReader);
    }
    return null;
  }

  public static void closeSilently(@Nullable Closeable closeable) {
    close(closeable);
  }

  public static void close(@Nullable Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (IOException e) {
      }
    }
  }
}
