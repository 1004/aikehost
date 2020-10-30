package com.aike.xky.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.aike.router.Router;
import com.aike.xky.R;
import com.aike.xky.main.MainActivity;

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
        //DebugLoadSdPlugin.checkDebugPush(AikeSplashActivity.this);
        Router.init(AikeSplashActivity.this.getApplicationContext());
      }
    });
    findViewById(R.id.open_extern_user).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Router.create("xky://user/main").navigate(AikeSplashActivity.this);
      }
    });
    findViewById(R.id.open_extern_cource).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Router.create("xky://cource/main").navigate(AikeSplashActivity.this);
      }
    });
    findViewById(R.id.open_main).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(AikeSplashActivity.this, MainActivity.class);
        AikeSplashActivity.this.startActivity(intent);
      }
    });
  }


}
