package com.aike.xky.splash;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;
import com.aike.router.Router;
import com.aike.xky.R;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.qihoo360.replugin.utils.FileUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @Author xiekongying001
 * @Date 2019/10/27 15:31
 * @Des
 */
public class AikeSplashActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    initView();
  }

  private void initView() {
    findViewById(R.id.init_test).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Router.init(AikeSplashActivity.this);
      }
    });
    findViewById(R.id.open_extern).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Router.create("xky://user/main").navigate(AikeSplashActivity.this);
      }
    });
  }


}
