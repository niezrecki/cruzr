package com.ubtechinc.cruzr.sdk.navigation;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.ubtechinc.cruzr.navigation.service.INavigationAidlInterface;
import com.ubtechinc.cruzr.navigation.service.INavigationCallBack;
import com.ubtechinc.cruzr.sdk.navigation.model.MapModel;
import com.ubtechinc.cruzr.sdk.navigation.model.MapPointModel;
import com.ubtechinc.cruzr.sdk.navigation.utils.Convert;
import com.ubtechinc.cruzr.sdk.navigation.utils.MyLogger;
import com.ubtechinc.cruzr.serverlibutil.interfaces.NavigationApiCallBackListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;






public class NavigationApi
{
  private Context mContext;
  private volatile INavigationAidlInterface mIRemoteService;
  private WeakReference<NavigationApiCallBackListener> weakReference;
  private NavigationConstant.EnumNavigationType mNavigationType = NavigationConstant.EnumNavigationType.NAVIGATION_BY_THIRD_APP_NO_TTS;
  
  private NavigationApi() {}
  
  public static final NavigationApi get()
  {
    return NavigationApi.NavigationApiHolder.access$000();
  }
  




  private ServiceConnection conn = new NavigationApi.1(this);
  


























  private INavigationCallBack navigationCallBack = new NavigationApi.2(this);
  


























  private synchronized void bindNavigationService()
  {
    Intent intent = new Intent("com.ubtechinc.cruzr.navigation.NAVIGATION_ACTION");
    
    intent.setPackage("com.ubtechinc.cruzr.navigation");
    
    mContext.bindService(intent, conn, 1);
  }
  







  public synchronized void initializ(Context context)
  {
    mContext = context;
    bindNavigationService();
  }
  




  public void destory()
  {
    mContext.unbindService(conn);
  }
  





  public void startNavigationService(String pointName)
  {
    MyLogger.mLog().d("startNavigationService pointName:" + pointName);
    try {
      if (null != mIRemoteService) {
        mIRemoteService.startNavigation(pointName, mNavigationType.name());
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
  




  public void stopNavigationService()
  {
    MyLogger.mLog().d("stopNavigationService");
    try {
      if (null != mIRemoteService) {
        mIRemoteService.stopNavigation(mNavigationType.name());
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
  



  public void setNavigationApiCallBackListener(NavigationApiCallBackListener navigationApiCallBackListener)
  {
    weakReference = new WeakReference(navigationApiCallBackListener);
  }
  





  public List<MapPointModel> queryAllMapPointByMapName(String mapName)
  {
    MyLogger.mLog().d("queryMapPointPosition mapName:" + mapName);
    
    if (TextUtils.isEmpty(mapName)) {
      MyLogger.mLog().e("queryAllMapPointByMapName error: mapName is null.");
      return null;
    }
    
    ContentResolver contentResolver = mContext.getContentResolver();
    Uri uri = Uri.parse("content://com.ubtechinc.cruzr.map.positionProvider/position");
    
    String[] projection = null;
    String selection = "mapName = ?";
    String[] selectionArgs = { mapName };
    
    Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, null);
    
    List<MapPointModel> mapPointModels = new ArrayList();
    

    if ((cursor != null) && (cursor.moveToFirst())) {
      do {
        MapPointModel mapPointModel = new MapPointModel();
        
        mapPointModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
        mapPointModel.setPointName(cursor.getString(cursor.getColumnIndex("pointName")));
        mapPointModel.setMapId(cursor.getInt(cursor.getColumnIndex("map_id")));
        mapPointModel.setMapName(cursor.getString(cursor.getColumnIndex("mapName")));
        mapPointModel.setPointType(cursor.getString(cursor.getColumnIndex("pointType")));
        mapPointModel.setMapX(cursor.getString(cursor.getColumnIndex("map_x")));
        mapPointModel.setMapY(cursor.getString(cursor.getColumnIndex("map_y")));
        mapPointModel.setTheta(cursor.getString(cursor.getColumnIndex("theta")));
        mapPointModel.setDisplayX(cursor.getString(cursor.getColumnIndex("display_x")));
        mapPointModel.setDisplayY(cursor.getString(cursor.getColumnIndex("display_y")));
        mapPointModel.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        boolean isStandby = cursor.getInt(cursor.getColumnIndex("is_standby")) == 1;
        mapPointModel.setStandby(isStandby);
        boolean isWelcom = cursor.getInt(cursor.getColumnIndex("is_welcome")) == 1;
        mapPointModel.setWelcome(isWelcom);
        boolean isCruiser = cursor.getInt(cursor.getColumnIndex("is_cruiser")) == 1;
        mapPointModel.setCruiser(isCruiser);
        mapPointModel.setCruiserIndex(cursor.getInt(cursor.getColumnIndex("cruiser_index")));
        
        mapPointModels.add(mapPointModel);
      } while (cursor.moveToNext());
      
      cursor.close();
    }
    
    MyLogger.mLog().d("mapPointModels:" + Convert.toJson(mapPointModels));
    return mapPointModels;
  }
  






  public MapModel queryMapModelByMapName(String mapName)
  {
    MyLogger.mLog().d("queryMapModelByMapName mapName:" + mapName);
    
    if (TextUtils.isEmpty(mapName)) {
      MyLogger.mLog().e("queryMapModelByMapName error: mapName is null.");
      return null;
    }
    
    ContentResolver contentResolver = mContext.getContentResolver();
    Uri uri = Uri.parse("content://com.ubtechinc.cruzr.map.mapProvider/map");
    
    String[] projection = null;
    String selection = "mapName = ?";
    String[] selectionArgs = { mapName };
    
    Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, null);
    
    MapModel mapModel = null;
    
    if ((cursor != null) && (cursor.moveToFirst()))
    {
      mapModel = new MapModel();
      
      mapModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
      mapModel.setMap_name(cursor.getString(cursor.getColumnIndex("map_name")));
      mapModel.setMap_original(cursor.getString(cursor.getColumnIndex("map_original")));
      mapModel.setMap_modify(cursor.getString(cursor.getColumnIndex("map_modify")));
      mapModel.setMap_data_url(cursor.getString(cursor.getColumnIndex("map_data_url")));
      mapModel.setResolution(cursor.getString(cursor.getColumnIndex("resolution")));
      mapModel.setBmp_x(cursor.getString(cursor.getColumnIndex("bmp_x")));
      mapModel.setBmp_y(cursor.getString(cursor.getColumnIndex("bmp_y")));
      mapModel.setBmp_w(cursor.getString(cursor.getColumnIndex("bmp_w")));
      mapModel.setBmp_h(cursor.getString(cursor.getColumnIndex("bmp_h")));
      mapModel.setDisplay_w(cursor.getString(cursor.getColumnIndex("display_w")));
      mapModel.setDisplay_h(cursor.getString(cursor.getColumnIndex("display_h")));
      mapModel.setRail_mode(cursor.getString(cursor.getColumnIndex("rail_mode")));
      mapModel.setRail_mode_in_use(cursor.getString(cursor.getColumnIndex("rail_mode_in_use")));
      boolean isCruiserRandom = cursor.getInt(cursor.getColumnIndex("cruiser_random")) == 1;
      mapModel.setCruiser_random(isCruiserRandom);
      
      cursor.close();
    }
    
    MyLogger.mLog().d("mapModel:" + (mapModel == null ? "is null!" : Convert.toJson(mapModel)));
    return mapModel;
  }
  



  public NavigationConstant.EnumNavigationType getmNavigationType()
  {
    return mNavigationType;
  }
  



  public void setmNavigationType(NavigationConstant.EnumNavigationType mNavigationType)
  {
    this.mNavigationType = mNavigationType;
  }
}