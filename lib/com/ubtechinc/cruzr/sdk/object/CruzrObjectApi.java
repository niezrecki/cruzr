package com.ubtechinc.cruzr.sdk.Object;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.ubtechinc.cruzr.Object.aidl.CruzrObjectImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class CruzrObjectApi
{
  private static final String TAG = "CruzrObjectApi";
  private static boolean isLauncherConn = false;
  private static CruzrObjectImpl asLauncherInterObject;
  private static boolean isCoreConn = false;
  private static CruzrObjectImpl asCoreInterObject;
  private static boolean isSmile = true;
  private static Intent launcherService;
  private static Intent coreService;
  private static CruzrObjectCallBackImpl mCruzrObjectCallBackImpl;
  private static String mAppId = "";
  private static String mObjectId = "";
  private static Context mContext;
  private static int requestId = 100;
  private static boolean isPlayMusic = true; //
  private static boolean isLoop = true;
  
  private static final String LAUNCHERSERVICE_PACKNAME = "com.ubtechinc.cruzr.launcher";
  private static final String LAUNCHERSERVICE_ACTION = "com.ubtechinc.cruzr.launcher.services.LauncherObjectService";  // There is currently no object in core services and must be created
  private static final String CORESERVICE_PACKNAME = "com.ubtechinc.cruzr.coreservices";
  private static final String CORESERVICE_ACTION = "com.ubtechinc.cruzr.coreservices.Object.CruzrCoreObjectServie";
  private static Map<String, CruzrObjectCallBackImpl> callBackMap = new HashMap();
  
  private static final int Object_MODE_NORMAL = 110;
  private static final int Object_MODE_BG = 111;
  private static final int Object_MODE_CONTROL = 112;
  private static final int Object_MODE_ObjectID = 113;
  private static int setObjectMode = 110;
  
  private static final int Object_ID_REQUEST = 120;
  private static final int Object_NAME_REQUEST = 121;
  private static final int Object_URI_REQUEST = 122;
  private static int mCurrentRequestMode = -1;
  

  private static String resultObjectId = "";
  




  private static boolean mIsControl = false;
  
  private static ServiceConnection LauncherConn = new CruzrObjectApi.1();







  public CruzrObjectApi() {}







// Current decode method for the JSON list when provided as a string
  // Set as JSON Object
  // Check out GSON, more standard parse


  private static void decodeObjectList()
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
  
  private static String decodeObjectId()
  {
    String currentObjectId = "";
    try {
      currentObjectId = asLauncherInterObject.getCurrentObjectId();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    
    if (mCruzrObjectCallBackImpl != null) {
      Log.i("CruzrObjectApi", "decodeObjectId currentObjectId:" + currentObjectId + ";callback:" + mCruzrObjectCallBackImpl);
      mCruzrObjectCallBackImpl.onCurrentObjectIdCallBack(currentObjectId);
    }
    return currentObjectId;
  }
  
  private static String decodeObjectName(String appId) {
    String ObjectName = "";
    try {
      ObjectName = asLauncherInterObject.getCruzrObjectNameByObjectId(appId);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    
    if (mCruzrObjectCallBackImpl != null) {
      Log.i("CruzrObjectApi", "decodeObjectName appid:" + appId + ";ObjectName:" + ObjectName + ";callback:" + mCruzrObjectCallBackImpl);
      mCruzrObjectCallBackImpl.onCruzrObjectNameCallBack(ObjectName);
    }
    return ObjectName;
  }
  
  private static String decodeObjectUri(String appId) {
    String ObjectUri = "";
    try {
      ObjectUri = asLauncherInterObject.getCruzrObjectUriByObjectId(appId);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    
    if (mCruzrObjectCallBackImpl != null) {
      Log.i("CruzrObjectApi", "decodeObjectUri appid:" + appId + ";ObjectName:" + ObjectUri + ";callback:" + mCruzrObjectCallBackImpl);
      mCruzrObjectCallBackImpl.onCruzrObjectUriCallBack(ObjectUri);
    }
    return ObjectUri;
  }
  

  private static ServiceConnection coreConn = new CruzrObjectApi.2();



  private static ExecutorService newFixedThreadPool;




  private static GetObjectListTask getObjectListTask;





  private static int decodeSetObject()
  {
    int setCruzrObjectResult = -1;
    if (asCoreInterObject != null) {
      try {
        setCruzrObjectResult = asCoreInterObject.setCruzrObject(mAppId, mObjectId, isPlayMusic, isLoop);
        

        CruzrObjectCallBackImpl cruzrObjectCallBackImpl = (CruzrObjectCallBackImpl)callBackMap.remove("" + setCruzrObjectResult);
        
        if (cruzrObjectCallBackImpl != null)
        {
          cruzrObjectCallBackImpl.onCruzrObjectSetCallBack(setCruzrObjectResult);
        }
        Log.e("ObjectApi", "onCoreServiceConnected" + setCruzrObjectResult);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return setCruzrObjectResult;
  }
  
  private static int decodeSetObjectBG()
  {
    int setCruzrObjectResult = -1;
    if (asCoreInterObject != null) {
      try {
        setCruzrObjectResult = asCoreInterObject.setCruzrObjectBG(mAppId, mObjectId, isPlayMusic, isLoop);
        

        CruzrObjectCallBackImpl cruzrObjectCallBackImpl = (CruzrObjectCallBackImpl)callBackMap.remove("" + setCruzrObjectResult);
        
        if (cruzrObjectCallBackImpl != null)
        {
          cruzrObjectCallBackImpl.onCruzrObjectSetCallBack(setCruzrObjectResult);
        }
        Log.e("ObjectApi", "onCoreServiceConnected" + setCruzrObjectResult);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return setCruzrObjectResult;
  }
  
  private static int decodeSetObjectControlBG() {
    int setCruzrObjectResult = -1;
    if (asCoreInterObject != null) {
      try
      {
        setCruzrObjectResult = asCoreInterObject.setControlBG(mAppId, mIsControl);
        

        CruzrObjectCallBackImpl cruzrObjectCallBackImpl = (CruzrObjectCallBackImpl)callBackMap.remove("" + setCruzrObjectResult);
        
        if (cruzrObjectCallBackImpl != null)
        {
          cruzrObjectCallBackImpl.onCruzrObjectSetCallBack(setCruzrObjectResult);
        }
        Log.e("ObjectApi", "onCoreServiceConnected" + setCruzrObjectResult);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return setCruzrObjectResult;
  }
  





  public static void initCruzrObject(Context context)
  {
    mContext = context.getApplicationContext();
    bindCoreService(mContext);
    bindLauncherService(mContext);
  }
  









  public static int getCruzrObjectsList(CruzrObjectCallBackImpl cruzrObjectCallBackImpl)
  {
    if (newFixedThreadPool == null) {
      newFixedThreadPool = Executors.newFixedThreadPool(5);
    }
    
    getObjectListTask = new GetObjectListTask(mContext, cruzrObjectCallBackImpl);
    newFixedThreadPool.execute(getObjectListTask);
    







    return 1;
  }
  

















  public static int setCruzrObject(CruzrObjectCallBackImpl cruzrObjectCallBackImpl, String ObjectId, boolean playMusic, boolean loop)
  {
    mCurrentRequestMode = -1;
    mObjectId = "";
    
    setObjectMode = 110;  // Object mode normal
    
    isPlayMusic = playMusic;
    isLoop = loop;
    

    Log.e("ObjectApi", "setObject");
    
    callBackMap.put("" + requestId, cruzrObjectCallBackImpl);
    

    mAppId = getPackageName(mContext);
    
    Log.e("ObjectApi", "setObject==" + mAppId);
    
    mObjectId = ObjectId + "&" + requestId;
    
    mCruzrObjectCallBackImpl = cruzrObjectCallBackImpl;
    
    if ((!isCoreConn) || (asCoreInterObject == null)) {
      bindCoreService(mContext);
    }
    else {
      decodeSetObject();
    }
    
    return requestId++;
  }
  


















  public static int setCruzrObjectBG(CruzrObjectCallBackImpl cruzrObjectCallBackImpl, String ObjectId, boolean playMusic, boolean loop)
  {
    setObjectMode = 111;
    isPlayMusic = playMusic;
    isLoop = loop;
    

    Log.e("ObjectApi", "setObject");
    
    callBackMap.put("" + requestId, cruzrObjectCallBackImpl);
    

    mAppId = getPackageName(mContext);
    
    Log.e("ObjectApi", "setObject==" + mAppId);
    
    mObjectId = ObjectId + "&" + requestId;
    
    mCruzrObjectCallBackImpl = cruzrObjectCallBackImpl;
    
    if ((!isCoreConn) || (asCoreInterObject == null)) {
      bindCoreService(mContext);
    }
    else {
      decodeSetObjectBG();
    }
    
    return requestId++;
  }
  









  /**
   * @deprecated
   */
  public static int setCruzrObjectControlBG(CruzrObjectCallBackImpl cruzrObjectCallBackImpl, String appId, boolean isControl)
  {
    mIsControl = isControl;
    
    setObjectMode = 112;
    

    Log.e("ObjectApi", "setObject");
    
    callBackMap.put("" + requestId, cruzrObjectCallBackImpl);
    

    mAppId = getPackageName(mContext);
    
    mCruzrObjectCallBackImpl = cruzrObjectCallBackImpl;
    
    if ((!isCoreConn) || (asCoreInterObject == null)) {
      bindCoreService(mContext);
    }
    else {
      decodeSetObjectControlBG();
    }
    return requestId++;
  }
  








  public static String getCurrentObjectId(CruzrObjectCallBackImpl cruzrObjectCallBackImpl)
  {
    mCruzrObjectCallBackImpl = cruzrObjectCallBackImpl;
    mCurrentRequestMode = 120;
    mObjectId = "";
    String returnObjectId = "-1";
    if ((!isLauncherConn) || (asLauncherInterObject == null)) {
      bindLauncherService(mContext);
    } else {
      returnObjectId = decodeObjectId();
    }
    return returnObjectId;
  }
  






  public static String getCruzrObjectNameByObjectId(CruzrObjectCallBackImpl cruzrObjectCallBackImpl, String ObjectId)
  {
    if (TextUtils.isEmpty(ObjectId)) {
      throw new IllegalArgumentException("ObjectId异常 Object==" + ObjectId);
    }
    Log.i("CruzrObjectApi", "getCruzrObjectNameByObjectId callback：" + cruzrObjectCallBackImpl);
    mCruzrObjectCallBackImpl = cruzrObjectCallBackImpl;
    mCurrentRequestMode = 121;
    resultObjectId = ObjectId;
    String returnObjectName = "-1";
    if ((!isLauncherConn) || (asLauncherInterObject == null)) {
      bindLauncherService(mContext);
    } else {
      Log.i("CruzrObjectApi", "decodeObjectName：" + ObjectId);
      returnObjectName = decodeObjectName(ObjectId);
    }
    return returnObjectName;
  }
  






  public static String getCruzrObjectUriByObjectId(CruzrObjectCallBackImpl cruzrObjectCallBackImpl, String ObjectId)
  {
    if ((TextUtils.isEmpty(ObjectId)) && 
      (TextUtils.isEmpty(ObjectId))) {
      throw new IllegalArgumentException("ObjectId异常 Object==" + ObjectId);
    }
    
    Log.i("CruzrObjectApi", "getCruzrObjectUriByObjectId callback：" + cruzrObjectCallBackImpl);
    mCruzrObjectCallBackImpl = cruzrObjectCallBackImpl;
    mCurrentRequestMode = 122;
    resultObjectId = ObjectId;
    String returnObjectUri = "-1";
    if ((!isLauncherConn) || (asLauncherInterObject == null)) {
      bindLauncherService(mContext);
    } else {
      Log.i("CruzrObjectApi", "decodeObjectUri：" + ObjectId);
      returnObjectUri = decodeObjectUri(ObjectId);
    }
    return returnObjectUri;
  }
  
  private static String getPackageName(Context context)
  {
    String packageName = "";
    try
    {
      PackageInfo info = context.getPackageManager().getPackageInfo(context
        .getPackageName(), 0);
      
      String versionName = versionName;
      
      int versionCode = versionCode;
      
      packageName = packageName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return packageName;
  }
  





  private static void bindLauncherService(Context context)
  {
    if (context != null) {
      launcherService = new Intent("com.ubtechinc.cruzr.launcher.services.LauncherObjectService");
      launcherService.setPackage("com.ubtechinc.cruzr.launcher");
      context.bindService(launcherService, LauncherConn, 1);
    }
  }
  







  private static void bindCoreService(Context context)
  {
    coreService = new Intent("com.ubtechinc.cruzr.coreservices.Object.CruzrCoreObjectServie");
    coreService.setPackage("com.ubtechinc.cruzr.coreservices");
    context.bindService(coreService, coreConn, 1);
  }
}
