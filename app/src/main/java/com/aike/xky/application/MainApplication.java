package com.aike.xky.application;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.aike.eventbus.AikeEventBusIPC;
import com.aike.router.Route;
import com.aike.router.Router;
import com.aike.xky.BuildConfig;
import com.aike.xky.application.plugin.DebugLoadSdPlugin;
import com.aike.xky.application.plugin.HostCallbacks;
import com.aike.xky.application.plugin.HostEventCallbacks;
import com.aike.xky.core.base.FlutterRunnerUtils;
import com.aike.xky.core.base.PageRouter;
import com.aike.xky.core.flutter.cache.AikeFlutterCacheManager;
import com.aike.xky.utils.AppUtil;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.Platform;
import com.idlefish.flutterboost.Utils;
import com.idlefish.flutterboost.interfaces.INativeRouter;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginCallbacks;
import com.qihoo360.replugin.RePluginConfig;
import com.qihoo360.replugin.model.PluginInfo;
import java.util.List;
import java.util.Map;

/**
 * @Author xiekongying001
 * @Date 2019/10/27 15:06
 * @Des
 */
public class MainApplication extends RePluginApplication {
  private static Context context;
  @Override
  public void onCreate() {
    super.onCreate();
    MainApplication.context = this;
    init();
  }

  public static Context getContext(){
    return MainApplication.context;
  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    // FIXME 允许接收rpRunPlugin等Gradle Task，发布时请务必关掉，以免出现问题
    RePlugin.enableDebugger(base, BuildConfig.DEBUG);
  }

  private void init(){
    //安装sdcard插件
    //DebugLoadSdPlugin.checkDebugPush();
    //初始化Router
    if(isMainProcess()){
      initPlugin();
      Router.init(this);
      //初始化Eventbus
      AikeEventBusIPC.init();
    }
    initFlutter();
  }

  private void initFlutter(){
    AikeFlutterCacheManager.getInstance().proLoad(this);
    Platform platform = new FlutterBoost.ConfigBuilder(this, new INativeRouter(){

      @Override
      public void openContainer(Context context, String url, Map<String, Object> urlParams, int requestCode, Map<String, Object> exts) {
        //String assembleUrl = Utils.assembleUrl(url, urlParams);
        String targetUrl = FlutterRunnerUtils.decodeUriQuery(url);
        Bundle bundle = FlutterRunnerUtils.map2Bundle(urlParams);
        Router.create(targetUrl).with(bundle).navigate(context);
        //PageRouter.openPageByUrl(context, assembleUrl, urlParams);
      }
    }).isDebug(true)
        .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
        .build();

    FlutterBoost.instance().init(platform);
  }

  //初始化插件
  private void initPlugin() {
    List<PluginInfo> pluginList = RePlugin.getPluginInfoList();
    for (PluginInfo plugin : pluginList) {
      Log.i("MainApplication","-----"+plugin.getName());
      RePlugin.fetchContext(plugin.getName());
    }
  }

  /**
   * 判断当前运行时是否为主进程
   *
   * @return
   */
  private boolean isMainProcess() {
    String processName = AppUtil.getProcessName();
    if (!TextUtils.isEmpty(processName) && processName.equals(this.getPackageName())) {
      return true;
    }
    return false;
  }


  private void loadPlugin(){

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
