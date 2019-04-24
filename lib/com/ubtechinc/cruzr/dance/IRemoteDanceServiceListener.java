package com.ubtechinc.cruzr.dance;

import android.os.IInterface;
import android.os.RemoteException;

public abstract interface IRemoteDanceServiceListener
  extends IInterface
{
  public abstract void onChange(String paramString, int paramInt)
    throws RemoteException;
}