package com.aike.xky.application.plugin;

import android.os.Environment;
import android.util.Log;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import java.io.File;

/**
 * 创建时间: 2019/10/29 10:11 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class DebugLoadSdPlugin {
  private static final String TAG = "DebugLoadSdPlugin";

  private static void installExternalPlugin(java.io.File fileDir) {
    java.io.File[] files = fileDir.listFiles();
    if(files != null && files.length > 0) {
      for (int i = 0; i < files.length; i++) {
        java.io.File file = files[i];
        if (file.isDirectory()) {
          installExternalPlugin(file);
        } else if (file.isFile()) {
          String pluginPath = file.getAbsolutePath();
          PluginInfo install = RePlugin.install(pluginPath);
          if (install == null) {
            Log.i(TAG, "安装外置插件失败");
          } else {
            RePlugin.preload(install);
            Log.i(TAG, "安装外置插件成功");
          }
        }
      }
    } else {
      Log.i(TAG,"没有外置插件");
    }
  }

  public static void checkDebugPush(){
    File directory = Environment.getExternalStorageDirectory();
    String path = directory.getAbsolutePath() + File.separator+"aikeplugins";
    File pluginDir = new File(path);
    if (!pluginDir.exists()){
      pluginDir.mkdirs();
    }
    installExternalPlugin(new File(path));
  }
}
