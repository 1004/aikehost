package com.aike.xky.core.flutter.cache

import android.content.Context
import android.os.Looper
import com.aike.xky.core.flutter.bridge.AikeFlutterBridge
import com.aike.xky.core.flutter.model.FlutterModelConstants
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.view.FlutterMain

/**
 * 创建时间: 2020/10/29 18:30 <br>
 * 作者: xiekongying <br>
 * 描述:
 */
class AikeFlutterCacheManager private constructor() {

  companion object {
    @JvmStatic
    @get:Synchronized
    var instance: AikeFlutterCacheManager? = null
      get() {
        if (field == null) {
          field = AikeFlutterCacheManager()
        }
        return field
      }
      private set
  }

  //预加载引擎
  fun proLoad(context: Context) {
    //空闲队列
    Looper.myQueue().addIdleHandler {
      initFlutterEngine(context,FlutterModelConstants.MY_MODE)
      false
    }
  }

  private fun initFlutterEngine(context: Context, modelName: String): FlutterEngine {
    val flutterEngine = FlutterEngine(context)
    flutterEngine.dartExecutor.executeDartEntrypoint(
      DartExecutor.DartEntrypoint(FlutterMain.findAppBundlePath(),modelName)
    )
    FlutterEngineCache.getInstance().put(modelName,flutterEngine)
    AikeFlutterBridge.init(flutterEngine)
    return flutterEngine
  }

  fun getFlutterEngine(modelName:String,context: Context?):FlutterEngine{
    var flutterEngine = FlutterEngineCache.getInstance()[modelName]
    if (flutterEngine == null && context != null){
      flutterEngine = initFlutterEngine(context,modelName)
    }
    return flutterEngine!!
  }

}