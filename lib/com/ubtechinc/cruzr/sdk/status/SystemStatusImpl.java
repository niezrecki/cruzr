package com.ubtechinc.cruzr.sdk.status;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ubtechinc.cruzr.serverlibutil.aidl.IStatusService;
import com.ubtechinc.cruzr.serverlibutil.aidl.IStatusService.Stub;
import com.ubtechinc.cruzr.serverlibutil.aidl.State;
import com.ubtechinc.cruzr.serverlibutil.aidl.StateRule;
import com.ubtechinc.cruzr.serverlibutil.aidl.StatusInfo;
import com.ubtechinc.cruzr.serverlibutil.interfaces.RemoteStatusListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.StatusChangeListener;
import com.ubtechinc.cruzr.serverlibutil.utils.SpeechLog;
import java.util.List;



public class SystemStatusImpl
  implements ServiceConnection
{
  private Context mContext;
  private IStatusService mService;
  private RemoteStatusListener mRemoteStatusListener;
  private SystemStatusImpl.RemoteStatusListenerImp mStatusListener;
  private SystemStatusImpl.RemoteLinkDeathListenerImp mLinkDeathListener;
  private StatusInitCallBack mInitListener;
  private StatusChangeListener mChangeListener;
  private Intent mCallIntent;
  private boolean mHasInit;
  
  public SystemStatusImpl(Context context)
  {
    mContext = context;
    mHasInit = false;
    mCallIntent = new Intent("com.ubtechinc.cruzr.coreservices.status.RemoteStatusService");
    mCallIntent.setPackage("com.ubtechinc.cruzr.coreservices");
    context.bindService(mCallIntent, this, 1);
    mStatusListener = new SystemStatusImpl.RemoteStatusListenerImp(this, null);
    mLinkDeathListener = new SystemStatusImpl.RemoteLinkDeathListenerImp(this, null);
  }
  
  public void setListener(StatusInitCallBack l) {
    mInitListener = l;
  }
  
  public void onServiceConnected(ComponentName name, IBinder service)
  {
    mService = IStatusService.Stub.asInterface(service);
    try
    {
      mService.registerLinkDeath(mContext.getPackageName(), mLinkDeathListener);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    registerAllListener(mService);
    if (mInitListener != null) {
      if (mHasInit) {
        mInitListener.onReConnect();
      } else {
        mInitListener.onInit();
      }
    }
    mHasInit = true;
  }
  
  public void onServiceDisconnected(ComponentName name)
  {
    mContext.unbindService(this);
    mContext.bindService(mCallIntent, this, 1);
  }
  
  private void registerAllListener(IStatusService service) {
    registerStatusCallback(service);
  }
  
  public int requestSystemUnSleep() {
    int ret = 0;
    try {
      ret = mService.requestSystemUnSleep();
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public Context getContext() {
    return mContext;
  }
  
  public int shutDown() {
    int ret = 0;
    try {
      ret = mService.shutDown(mContext.getPackageName());
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public boolean setAppStatus(int status, StatusChangeListener listener) {
    mChangeListener = listener;
    return doStatus(status);
  }
  
  public int setAppStatus(int status, boolean iskeep, String sponsor) {
    int ret = 0;
    try {
      ret = mService.setAppStatus(status, mContext.getApplicationInfo().packageName, iskeep, sponsor);
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public boolean doStatus(int status) {
    try {
      return mService.doStatus(status, mContext.getApplicationInfo().packageName);
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public int removeAppStatus(int status) {
    int ret = 0;
    try {
      ret = mService.removeAppStatus(status);
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public int registerStatusCallback(RemoteStatusListener listener) {
    mRemoteStatusListener = listener;
    return registerStatusCallback(mService);
  }
  
  private int registerStatusCallback(IStatusService service) {
    int ret = 0;
    if (null == mRemoteStatusListener) {
      return ret;
    }
    try {
      ret = service.registerStatusCallback(mContext.getApplicationInfo().packageName, mStatusListener);
      Log.e("rqh", "rqh registerStatusCallback the ret = " + ret);
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public int respondStatus(int status) {
    int ret = 0;
    try {
      ret = mService.respondStatus(status);
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public int setDefaultTime(long freetime) {
    int ret = 0;
    try {
      ret = mService.setDefaultTime(freetime);
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public int setHeartTime(long freetime) {
    int ret = 0;
    try {
      ret = mService.setHeartTime(freetime);
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public long getDefaultTime() {
    long ret = 0L;
    try {
      ret = mService.getDefaultTime();
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public long getHeartTime() {
    long ret = 0L;
    try {
      ret = mService.getHeartTime();
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public List<StatusInfo> getCurrentStatusList() {
    List<StatusInfo> list = null;
    try {
      list = mService.getCurrentStatusList();
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public StatusInfo getCurrentStatus() {
    StatusInfo info = null;
    try {
      info = mService.getCurrentStatus();
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return info;
  }
  
  public boolean requestPermissions(int permission) {
    boolean ret = false;
    try {
      ret = mService.requestPermissions(permission);
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  public boolean queryPermissionsWithStatus(int permission, int statusId) {
    boolean ret = false;
    
    SpeechLog.i("Status service: " + mService);
    if ((mService == null) || (mService.asBinder() == null)) {
      SpeechLog.i("Status IBinder is null");
      return ret;
    }
    try
    {
      ret = mService.queryPermissionsWithStatus(permission, statusId);
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
  


  public int addAppState(State state, StateRule stateRule)
  {
    int ret = 0;
    try {
      ret = mService.addAppState(state, stateRule, mContext.getApplicationInfo().packageName);
    } catch (RemoteException|NullPointerException e) {
      e.printStackTrace();
    }
    return ret;
  }
}