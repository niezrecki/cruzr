package com.ubtechinc.cruzr.navigation.service;

import android.os.IInterface;
import android.os.RemoteException;

public abstract interface INavigationAidlInterface
  extends IInterface
{
  public abstract void registerNavigationListener(INavigationCallBack paramINavigationCallBack)
    throws RemoteException;
  
  public abstract void unregisterNavigationListener(INavigationCallBack paramINavigationCallBack)
    throws RemoteException;
  
  public abstract void startNavigation(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void stopNavigation(String paramString)
    throws RemoteException;
}