package com.aike.xky.core.flutter.bridge

/**
 * 创建时间: 2020/10/29 17:37 <br>
 * 作者: xiekongying <br>
 * 描述:
 */
interface IAikeBridge<P,Callback> {
  fun native(p:P)
}