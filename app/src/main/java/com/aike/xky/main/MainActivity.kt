package com.aike.xky.main

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.aike.xky.R
import com.aike.xky.main.tab.MyFragment

/**
 * 创建时间: 2020/10/29 16:10 <br>
 * 作者: xiekongying <br>
 * 描述:
 */
class MainActivity : FragmentActivity() {
  var titleTv: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    titleTv = findViewById(R.id.tabbar_title)
    supportFragmentManager.beginTransaction().add(R.id.fragment_container, MyFragment()).commitAllowingStateLoss()
  }

}