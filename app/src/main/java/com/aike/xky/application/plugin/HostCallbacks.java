package com.aike.xky.application.plugin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.aike.xky.BuildConfig;
import com.qihoo360.replugin.RePluginCallbacks;

/**
 * @Author xiekongying001
 * @Date 2019/10/27 15:40
 * @Des 宿主针对RePlugin的自定义行为
 */
public class HostCallbacks extends RePluginCallbacks {
  private static final String TAG = "HostCallbacks";

  public HostCallbacks(Context context) {
    super(context);
  }

  @Override
  public boolean onPluginNotExistsForActivity(Context context, String plugin, Intent intent,
      int process) {
    // FIXME 当插件"没有安装"时触发此逻辑，可打开您的"下载对话框"并开始下载。
    // FIXME 其中"intent"需传递到"对话框"内，这样可在下载完成后，打开这个插件的Activity
    if (BuildConfig.DEBUG) {
      Log.d(TAG, "onPluginNotExistsForActivity: Start download... p=" + plugin + "; i=" + intent);
    }
    return super.onPluginNotExistsForActivity(context, plugin, intent, process);
  }
}
