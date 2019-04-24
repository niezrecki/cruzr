package com.ubtechinc.cruzr.sdk.object;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
import com.google.gson.Gson;
import com.ubtechinc.cruzr.Object.aidl.CruzrObjectImpl; //Create new class




// Still need to create CruzrObjectImpl class in the serverlib




public class GetObjectListTask
  implements Runnable
{
  private Context mContext;
  private CruzrObjectCallBackImpl mCruzrObjectCallBackImpl;
  private static boolean isLauncherConn = false;
  
  private static CruzrObjectImpl asLauncherInterObject;
  private static Intent launcherService;
  private static final String LAUNCHERSERVICE_PACKNAME = "com.ubtechinc.cruzr.launcher";
  private static final String LAUNCHERSERVICE_ACTION = "com.ubtechinc.cruzr.launcher.services.LauncherObjectService";
  private ServiceConnection LauncherConn = new GetObjectListTask.1(this);









  public GetObjectListTask(Context context, CruzrObjectCallBackImpl cruzrObjectCallBackImpl)
  {
    mContext = context;
    mCruzrObjectCallBackImpl = cruzrObjectCallBackImpl;
  }
  
  public void run()
  {
    if ((!isLauncherConn) || (asLauncherInterObject == null))
    {
      bindLauncherService(mContext);
    } else {
      decodeObjectList();
    }
  }
  


  private void bindLauncherService(Context context)
  {
    if (context != null) {
      launcherService = new Intent("com.ubtechinc.cruzr.launcher.services.LauncherObjectService");
      launcherService.setPackage("com.ubtechinc.cruzr.launcher");
      context.bindService(launcherService, LauncherConn, 1);
    }
  }
  
  private void decodeObjectList()
  {
    try
    {
      String cruzrObjectListJson = asLauncherInterObject.getCruzrObjectList();
      
      Log.e("decodeObjectList", cruzrObjectListJson + "");
      
      if (!StringUtils.isEmpty(cruzrObjectListJson)) {
        ObjectListJson ObjectListJson = (ObjectListJson)new Gson().fromJson(cruzrObjectListJson, ObjectListJson.class);
        
        if ((ObjectListJson != null) && (mCruzrObjectCallBackImpl != null))
        {

          mCruzrObjectCallBackImpl.onCruzrObjectListCallBack(ObjectList);
        }
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}