package com.aike.xky.core.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.idlefish.flutterboost.containers.BoostFlutterActivity;
import java.util.HashMap;
import java.util.Map;

public class PageRouter {
    // 界面格式可自定义
    public final static String OnePageUrl = "url://onePage";
    public final static String NativePage = "url://nativePage";

    public final static Map<String, String> pageName = new HashMap<String, String>() {{
        // onePage名称与flutter界面名称对应
        put(OnePageUrl, "onePage");
    }};


    public static boolean openPageByUrl(Context context, String url, Map params) {
        return openPageByUrl(context, url, params, 0);
    }

    public static boolean openPageByUrl(Context context, String url, Map params, int requestCode) {
        String flutterPage = null;
        if (url.startsWith(OnePageUrl)) {
            // 跳转flutter界面
            flutterPage = pageName.get(OnePageUrl);
            Intent intent = BoostFlutterActivity.withNewEngine().url(flutterPage).params(params)
                    .backgroundMode(BoostFlutterActivity.BackgroundMode.opaque).build(context);
            context.startActivity(intent);
            return true;
        } else if (url.startsWith(NativePage)) {
            // 跳转原生界面
            String aNative = (String) params.get("native");
            Bundle bundle = new Bundle();
            bundle.putString("native", aNative);
            context.startActivity(new Intent(context, NativePageActivity.class).putExtras(bundle));
            return true;
        }
        return false;
    }
}
