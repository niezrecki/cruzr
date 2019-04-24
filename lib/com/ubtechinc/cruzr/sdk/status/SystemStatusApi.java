package com.ubtechinc.cruzr.sdk.status;

import android.content.Context;
import com.ubtechinc.cruzr.serverlibutil.aidl.State;
import com.ubtechinc.cruzr.serverlibutil.aidl.StateRule;
import com.ubtechinc.cruzr.serverlibutil.aidl.StatusInfo;
import com.ubtechinc.cruzr.serverlibutil.interfaces.RemoteStatusListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.StatusChangeListener;
import java.util.List;




public class SystemStatusApi
{
  private static volatile SystemStatusApi mApi;
  private SystemStatusImpl mSystemImpl;
  
  public SystemStatusApi() {}
  
  public static SystemStatusApi get()
  {
    if (mApi == null) {
      synchronized (SystemStatusApi.class) {
        if (mApi == null) {
          mApi = new SystemStatusApi();
        }
      }
    }
    return mApi;
  }
  




  public void init(Context context)
  {
    mSystemImpl = new SystemStatusImpl(context);
  }
  
  public void init(Context context, StatusInitCallBack listener) {
    mSystemImpl = new SystemStatusImpl(context);
    mSystemImpl.setListener(listener);
  }
  






  public int shutDown()
  {
    if (mSystemImpl == null) {
      return 0;
    }
    return mSystemImpl.shutDown();
  }
  










  public int setAppStatus(int status, boolean iskeep)
  {
    if (mSystemImpl == null) {
      return 0;
    }
    return mSystemImpl.setAppStatus(status, iskeep, null);
  }
  










  public int setAppStatus(int status, boolean iskeep, String sponsor)
  {
    if (mSystemImpl == null) {
      return 0;
    }
    return mSystemImpl.setAppStatus(status, iskeep, sponsor);
  }
  










  private boolean setAppStatus(int status, StatusChangeListener listener)
  {
    if (mSystemImpl == null) {
      return false;
    }
    return mSystemImpl.setAppStatus(status, listener);
  }
  








  public int removeAppStatus(int status)
  {
    if (mSystemImpl == null) {
      return 0;
    }
    return mSystemImpl.removeAppStatus(status);
  }
  
  private int setDefaultTime(long freetime) {
    if (mSystemImpl == null) {
      return 0;
    }
    return mSystemImpl.setDefaultTime(freetime);
  }
  
  private int setHeartTime(long heart) {
    if (mSystemImpl == null) {
      return 0;
    }
    return mSystemImpl.setHeartTime(heart);
  }
  
  private long getDefaultTime() {
    if (mSystemImpl == null) {
      return 0L;
    }
    return mSystemImpl.getDefaultTime();
  }
  
  private long getHeartTime() {
    if (mSystemImpl == null) {
      return 0L;
    }
    return mSystemImpl.getHeartTime();
  }
  
  private int respondStatus(int status) {
    if (mSystemImpl == null) {
      return 0;
    }
    return mSystemImpl.respondStatus(status);
  }


  public List<StatusInfo> getCurrentStatusList()
  {
    if (mSystemImpl == null) {
      return null;
    }
    return mSystemImpl.getCurrentStatusList();
  }
  





  public StatusInfo getCurrentStatus()
  {
    if (mSystemImpl == null) {
      return null;
    }
    return mSystemImpl.getCurrentStatus();
  }


  public int registerStatusCallback(RemoteStatusListener listener)
  {
    if (mSystemImpl == null) {
      return 0;
    }
    return mSystemImpl.registerStatusCallback(listener);
  }


  public boolean requestPermissions(int permission)
  {
    if (mSystemImpl == null) {
      return false;
    }
    return mSystemImpl.requestPermissions(permission);
  }


  public boolean queryPermissionsWithStatus(int permission, int statusId)
  {
    if (mSystemImpl == null) {
      return false;
    }
    return mSystemImpl.queryPermissionsWithStatus(permission, statusId);
  }



  public int addAppState(State state, StateRule stateRule)
  {
    if (mSystemImpl == null) {
      return 0;
    }
    return mSystemImpl.addAppState(state, stateRule);
  }
}