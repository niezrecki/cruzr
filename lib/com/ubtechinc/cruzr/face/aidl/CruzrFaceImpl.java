package com.ubtechinc.cruzr.face.aidl;

import android.os.IInterface;
import android.os.RemoteException;

public abstract interface CruzrFaceImpl
  extends IInterface
{
  public abstract String getCruzrFaceList()
    throws RemoteException;
  
  public abstract String getCurrentFaceId()
    throws RemoteException;
  
  public abstract int setCruzrFace(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract int setCruzrFaceBG(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract int setControlBG(String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract String getCruzrFaceNameByFaceId(String paramString)
    throws RemoteException;
  
  public abstract String getCruzrFaceUriByFaceId(String paramString)
    throws RemoteException;
}