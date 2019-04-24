package com.ubtechinc.cruzr.sdk.sys;

import android.content.Context;
import com.ubtechinc.cruzr.serverlibutil.interfaces.InitListener;
import com.ubtechinc.cruzr.serverlibutil.service.SysServiceUtil;


public class SysApi
{
  private SysServiceUtil mSysServiceUtil;
  private static volatile SysApi mSysApi;
  
  public SysApi() {}
  
  public static SysApi get()
  {
    if (mSysApi == null) {
      synchronized (SysApi.class) {
        if (mSysApi == null) {
          mSysApi = new SysApi();
        }
      }
    }
    return mSysApi;
  }
  
  public SysApi initializ(Context context, InitListener listener) {
    if (mSysServiceUtil == null) {
      mSysServiceUtil = new SysServiceUtil(context, listener);
    }
    return get();
  }
  




  public String getVersion()
  {
    if (mSysServiceUtil == null) {
      return "";
    }
    return mSysServiceUtil.getVersion();
  }
}