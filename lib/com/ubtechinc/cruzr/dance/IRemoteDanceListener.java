package com.ubtechinc.cruzr.dance;

import android.os.IInterface;
import android.os.RemoteException;

public abstract interface IRemoteDanceListener
  extends IInterface
{
  public abstract void onResult(int paramInt)
    throws RemoteException;
}