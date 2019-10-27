package com.aike.xky.application.plugin;

import android.content.Context;
import android.util.Log;
import com.aike.xky.BuildConfig;
import com.qihoo360.replugin.RePluginEventCallbacks;

/**
 * @Author xiekongying001
 * @Date 2019/10/27 15:39
 * @Des 针对“安装失败”等情况来做进一步的事件处理
 */
public class HostEventCallbacks extends RePluginEventCallbacks {
  private static final String TAG = "HostEventCallbacks";

  public HostEventCallbacks(Context context) {
    super(context);
  }

  @Override
  public void onInstallPluginFailed(String path, InstallResult code) {
    super.onInstallPluginFailed(path, code);
    // FIXME 当插件安装失败时触发此逻辑。您可以在此处做“打点统计”，也可以针对安装失败情况做“特殊处理”
    // 大部分可以通过RePlugin.install的返回值来判断是否成功
    if (BuildConfig.DEBUG) {
      Log.d(TAG, "onInstallPluginFailed: Failed! path=" + path + "; r=" + code);
    }
  }

  @Override
  public void onStartActivityCompleted(String plugin, String activity, boolean result) {
    // FIXME 当打开Activity成功时触发此逻辑，可在这里做一些APM、打点统计等相关工作
    super.onStartActivityCompleted(plugin, activity, result);
  }

}
