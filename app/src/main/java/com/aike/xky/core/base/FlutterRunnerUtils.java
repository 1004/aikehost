package com.aike.xky.core.base;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import com.aike.xky.constant.SchemeChannl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 创建时间: 2020/11/05 14:11 <br>
 * 作者: xiekongying <br>
 * 描述:
 */
public class FlutterRunnerUtils {
  @TargetApi(21)
  public static Bundle map2Bundle(Map<String, Object> paramMap) {
    Bundle bundle = new Bundle();
    if (paramMap == null || paramMap.isEmpty()) return bundle;
    for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      // [BEGIN] 解析params json
      if (SchemeChannl.AIKE_FLUTTER_PARAMS_KEY.equalsIgnoreCase(key)) {
        Log.d("FlutterRunnerUtils", "map2Bundle params json : " + value);
        bundle.putAll(map2Bundle(json2Map(String.valueOf(value))));
        continue;
      }
      // [END]
      if (value instanceof Bundle) {
        bundle.putBundle(key, (Bundle) value);
      } else if (value instanceof Byte) {
        bundle.putByte(key, (byte) value);
      } else if (value instanceof Short) {
        bundle.putShort(key, (short) value);
      } else if (value instanceof Integer) {
        bundle.putInt(key, (int) value);
      } else if (value instanceof Long) {
        bundle.putLong(key, (long) value);
      } else if (value instanceof Character) {
        bundle.putChar(key, (char) value);
      } else if (value instanceof Boolean) {
        bundle.putBoolean(key, (boolean) value);
      } else if (value instanceof Float) {
        bundle.putFloat(key, (float) value);
      } else if (value instanceof Double) {
        bundle.putDouble(key, (double) value);
      } else if (value instanceof String) {
        bundle.putString(key, (String) value);
      } else if (value instanceof CharSequence) {
        bundle.putCharSequence(key, (CharSequence) value);
      } else if (value instanceof byte[]) {
        bundle.putByteArray(key, (byte[]) value);
      } else if (value instanceof short[]) {
        bundle.putShortArray(key, (short[]) value);
      } else if (value instanceof int[]) {
        bundle.putIntArray(key, (int[]) value);
      } else if (value instanceof long[]) {
        bundle.putLongArray(key, (long[]) value);
      } else if (value instanceof char[]) {
        bundle.putCharArray(key, (char[]) value);
      } else if (value instanceof boolean[]) {
        bundle.putBooleanArray(key, (boolean[]) value);
      } else if (value instanceof float[]) {
        bundle.putFloatArray(key, (float[]) value);
      } else if (value instanceof double[]) {
        bundle.putDoubleArray(key, (double[]) value);
      } else if (value instanceof String[]) {
        bundle.putStringArray(key, (String[]) value);
      } else if (value instanceof CharSequence[]) {
        bundle.putCharSequenceArray(key, (CharSequence[]) value);
      } else if (value instanceof ArrayList) {
        if (!((ArrayList) value).isEmpty()) {
          Object obj = ((ArrayList) value).get(0);
          if (obj instanceof Integer) {
            bundle.putIntegerArrayList(key, (ArrayList<Integer>) value);
          } else if (obj instanceof String) {
            bundle.putStringArrayList(key, (ArrayList<String>) value);
          } else if (obj instanceof CharSequence) {
            bundle.putCharSequenceArrayList(key, (ArrayList<CharSequence>) value);
          } else if (obj instanceof Parcelable) {
            bundle.putParcelableArrayList(key, (ArrayList<? extends Parcelable>) value);
          } else {
            Log.w("FlutterRunnerUtils", "Unknown object mType.");
          }
        }
      } else if (value instanceof SparseArray) {
        bundle.putSparseParcelableArray(key, (SparseArray<? extends Parcelable>) value);
      } else if (value instanceof Parcelable) {
        bundle.putParcelable(key, (Parcelable) value);
      } else {
        Log.w("FlutterRunnerUtils", "Unknown object mType.");
      }
    }
    return bundle;
  }

  public static Map<String, Object> bundle2Map(Bundle bundle) {
    Map<String, Object> paramMap = new HashMap<>();
    if (bundle != null) {
      Set<String> keySet = bundle.keySet();
      for (String key : keySet) {
        paramMap.put(key, bundle.get(key));
      }
    }
    return paramMap;
  }

  public static String encodeUriQuery(String query) {
    return Uri.encode(query);
  }

  public static String decodeUriQuery(String query) {
    return Uri.decode(query);
  }

  public static Map<String, Object> json2Map(String json) {
    final Map<String, Object> params = new HashMap<>();
    try {
      JSONObject paramJson = new JSONObject(json);
      Iterator it = paramJson.keys();
      while (it.hasNext()) {
        String key = String.valueOf(it.next());
        params.put(key, paramJson.opt(key));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return params;
  }
}
