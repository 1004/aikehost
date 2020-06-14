package com.aike.xky;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.Test;

/**
 * 创建时间: 2020/02/20 21:48 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class StrTest {
  @Test
  public void testStartWith(){
String    str="\uFEFFhttps://bayek.m.ke.com/scan/fillinfo?code=FXS104226&name=聊城宜居房地产经纪有限公司&shop_name=聊城宜居房产&shop_code=FS0099745&company_code=FJH7027&auditor_id=1000000023102448&auditor_name=党启和";
    System.out.println(str.startsWith("https"));
  }

  @Test
  public void testThread(){
    try {
      Future<String> submit = Executors.newSingleThreadExecutor().submit(new Callable<String>() {
        @Override
        public String call() throws Exception {
          System.out.println("call-before");
          Thread.sleep(5 * 1000);
          System.out.println("call-after");
          return "ok";
        }
      });
      //String s = submit.get(13,TimeUnit.SECONDS);
      //System.out.println("s="+s);
      System.out.println("call-finish");
    } catch (Throwable e){
      e.printStackTrace();
    }
    //try {
    //  Thread.sleep(10*1000);
    //} catch (InterruptedException e) {
    //  e.printStackTrace();
    //}
  }
}
