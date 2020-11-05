package com.aike.xky.core.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.aike.router.Route;
import com.aike.xky.constant.SchemeChannl;
import com.idlefish.flutterboost.containers.BoostFlutterActivity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 创建时间: 2020/11/04 19:31 <br>
 * 作者: xiekongying <br>
 * 描述: 加一部分中转逻辑,自定义flutter 的url
 * Router.create("aike://flutter/boost/page?flutter_url=mFlutterUrl").navigate();
 */
@Route(SchemeChannl.FLUTTER_SCHEME_CHANNL)
public class AikeFluuterPageContainerActivity extends BoostFlutterActivity {
  private static final String DEFAULT_FLUTTER_URL = "flutter/default";
  private String mFlutterUrl = DEFAULT_FLUTTER_URL;
  private Map<String,Object> mFlutterParams = new HashMap<>();
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initIntents();
  }

  private void initIntents() {
    Bundle extras = getIntent().getExtras();
    if (extras != null){
      Set<String> strings = extras.keySet();
      for (String key:strings){
        if (SchemeChannl.AIKE_FLUTTER_URL_KEY.equalsIgnoreCase(key)){
          mFlutterUrl = extras.getString(key);
          continue;
        }
        if (SchemeChannl.AIKE_FLUTTER_PARAMS_KEY.equalsIgnoreCase(key)){
          mFlutterParams.putAll(json2Map(extras.getString(key)));
        }else {
          mFlutterParams.put(key,extras.get(key));
        }
      }
    }
  }

  @Override
  public String getContainerUrl() {
    return mFlutterUrl;
  }

  @Override
  public Map<String, Object> getContainerUrlParams() {
    return mFlutterParams;
  }


  private  Map<String, String> json2Map(String json) {
    final Map<String, String> params = new HashMap<>();
    try {
      JSONObject paramJson = new JSONObject(json);
      Iterator it = paramJson.keys();
      while (it.hasNext()) {
        String key = String.valueOf(it.next());
        params.put(key, paramJson.optString(key));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return params;
  }
}
