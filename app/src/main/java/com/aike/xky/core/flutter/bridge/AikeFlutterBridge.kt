package com.aike.xky.core.flutter.bridge

import android.widget.Toast
import com.aike.xky.application.MainApplication
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/**
 * 创建时间: 2020/10/29 17:38 <br>
 * 作者: xiekongying <br>
 * 描述:
 */
class AikeFlutterBridge : IAikeBridge<Any?, Result>, MethodCallHandler {

  val methodChannels = mutableListOf<MethodChannel>()
  //单利
  companion object {
    @JvmStatic
    var instance:AikeFlutterBridge?=null
      private set

    @JvmStatic
    fun init(flutterEngine: FlutterEngine):AikeFlutterBridge{
      val methodChannel = MethodChannel(flutterEngine.dartExecutor,"aikeflutterBridge")
      if (instance == null){
        AikeFlutterBridge().also { instance = it }
      }
      methodChannel.setMethodCallHandler(instance)
      instance!!.apply {
        methodChannels.add(methodChannel)
      }
      return instance!!
    }

  }

  fun send2Flutter(method:String,params:Any?){
    methodChannels.forEach {
      it.invokeMethod(method,params)
    }
  }

  fun send2Flutter(method: String,params: Any?,callback:MethodChannel.Result){
    methodChannels.forEach {
      it.invokeMethod(method,params,callback)
    }
  }

  override fun native(p: Any?) {
    Toast.makeText(MainApplication.getContext(),"native:来了"+p,Toast.LENGTH_SHORT).show()
  }

  //接受flutter-->natvie的执行方法，使用router做中转
  override fun onMethodCall(call: MethodCall, result: Result) {
    val methodName = call.method
    if (methodName.equals("native")){
      native(call.arguments)
    }
  }

}