package com.aike.xky.main.tab

import com.aike.xky.R
import com.aike.xky.core.base.AikeFlutterFragment
import com.aike.xky.core.flutter.model.FlutterModelConstants

/**
 * 创建时间: 2020/10/29 17:00 <br>
 * 作者: xiekongying <br>
 * 描述:
 */
class MyFragment: AikeFlutterFragment() {

  override fun getLayoutId(): Int {
    return R.layout.fragment_my
  }

  override fun getModelName(): String {
    return FlutterModelConstants.MY_MODE
  }

}