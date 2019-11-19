package com.aike.xky.application;

import android.content.Context;
import com.aike.eventbus.AikeEventBusIPC;
import com.aike.router.Router;
import com.aike.xky.BuildConfig;
import com.aike.xky.application.plugin.DebugLoadSdPlugin;
import com.aike.xky.application.plugin.HostCallbacks;
import com.aike.xky.application.plugin.HostEventCallbacks;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginCallbacks;
import com.qihoo360.replugin.RePluginConfig;

/**
 * @Author xiekongying001
 * @Date 2019/10/27 15:06
 * @Des
 */
public class MainApplication extends RePluginApplication {
  @Override
  public void onCreate() {
    super.onCreate();
    init();
  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    // FIXME 允许接收rpRunPlugin等Gradle Task，发布时请务必关掉，以免出现问题
    RePlugin.enableDebugger(base, BuildConfig.DEBUG);
  }

  private void init(){
    //安装sdcard插件
    DebugLoadSdPlugin.checkDebugPush();
    //初始化Router
    Router.init(this);
    //初始化Eventbus
    AikeEventBusIPC.init();
  }

  @Override
  protected RePluginConfig createConfig() {
    //自定义插件行为
    RePluginConfig rePluginConfig = new RePluginConfig();

    // 允许“插件使用宿主类”。默认为“关闭”
    rePluginConfig.setUseHostClassIfNotFound(true);
    // FIXME RePlugin默认会对安装的外置插件进行签名校验，这里先关掉，避免调试时出现签名错误
    rePluginConfig.setVerifySign(!BuildConfig.DEBUG);
    // 针对“安装失败”等情况来做进一步的事件处理
    rePluginConfig.setEventCallbacks(new HostEventCallbacks(this));

    return rePluginConfig;
  }


  @Override
  protected RePluginCallbacks createCallbacks() {
    return new HostCallbacks(this);
  }

}
