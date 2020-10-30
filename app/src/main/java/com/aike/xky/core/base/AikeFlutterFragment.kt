package com.aike.xky.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aike.xky.application.MainApplication
import com.aike.xky.core.flutter.cache.AikeFlutterCacheManager
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.android.FlutterView.RenderMode
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor

/**
 * 创建时间: 2020/10/29 17:10 <br>
 * 作者: xiekongying <br>
 * 描述:
 */
abstract class AikeFlutterFragment : Fragment(){
  protected var flutterEngine:FlutterEngine
  protected var flutterView: FlutterView?= null

  init {
     flutterEngine = AikeFlutterCacheManager.instance!!.getFlutterEngine(getModelName(),MainApplication.getContext())
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(getLayoutId(),null)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    (view as ViewGroup).addView(createFlutterView(activity!!))//native-flutterview-dart
  }

  protected fun createFlutterView(context: Context):FlutterView{
    flutterView = FlutterView(context, RenderMode.texture)//装载flutter widget
    return flutterView!!
  }


  override fun onStart() {
    super.onStart()
    flutterView!!.attachToFlutterEngine(flutterEngine)//挂钩
  }

  override fun onResume() {
    super.onResume()
    flutterEngine.lifecycleChannel.appIsResumed()
  }

  override fun onPause() {
    super.onPause()
    flutterEngine.lifecycleChannel.appIsInactive()
  }

  override fun onStop() {
    super.onStop()
    flutterEngine.lifecycleChannel.appIsPaused()
  }

  override fun onDetach() {
    super.onDetach()
    flutterEngine.lifecycleChannel.appIsDetached()
  }

  override fun onDestroy() {
    super.onDestroy()
  }

  abstract fun getLayoutId():Int

  abstract fun getModelName():String

}