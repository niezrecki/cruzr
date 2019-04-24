package com.ubtechinc.cruzr.sdk.Object;

import java.util.ArrayList;

// Add call back for the robot slam system

public abstract interObject CruzrObjectCallBackImpl
{
  public abstract void onCruzrObjectListCallBack(ArrayList<ObjectInfo> paramArrayList);
  
  public abstract void onCruzrObjectSetCallBack(int paramInt);
  
  public abstract void onCurrentObjectIdCallBack(String paramString);
  
  public abstract void onCruzrObjectNameCallBack(String paramString);
  
  public abstract void onCruzrObjectUriCallBack(String paramString);
}