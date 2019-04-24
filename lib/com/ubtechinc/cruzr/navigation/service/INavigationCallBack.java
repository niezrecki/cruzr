package com.ubtechinc.cruzr.navigation.service;

import android.os.IInterface;
import android.os.RemoteException;

public abstract interface INavigationCallBack
  extends IInterface
{
  public abstract void onNavigationResult(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
    throws RemoteException;
  
  public abstract void onRemoteCommonResult(String paramString1, int paramInt, String paramString2)
    throws RemoteException;
}