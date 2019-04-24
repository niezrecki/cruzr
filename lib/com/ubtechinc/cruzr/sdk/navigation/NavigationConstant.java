package com.ubtechinc.cruzr.sdk.navigation;

public class NavigationConstant
{
  public static final String BIND_NAVIGATION_ACTION = "com.ubtechinc.cruzr.navigation.NAVIGATION_ACTION";
  public static final String START_NAVIGATION_ACTION = "com.ubtechinc.cruzr.navigation.START_NAVIGATION_ACTION";
  public static final String STOP_NAVIGATION_ACTION = "com.ubtechinc.cruzr.navigation.STOP_NAVIGATION_ACTION";
  public static final String NAVIGATION_PACKAGENAME = "com.ubtechinc.cruzr.navigation";
  public static final String NAVIGATION_POINT_NAME = "pointName";
  public static final String NAVIGATION_POINT_X = "pointX";
  public static final String NAVIGATION_POINT_Y = "pointY";
  public static final String NAVIGATION_POINT_THETA = "pointTheta";
  public static final String START_MAP_NAVIGATION_ACTION = "com.ubtechinc.cruzr.map.action.START_NAVIGATION_ACTION";
  public static final String STOP_MAP_NAVIGATION_ACTION = "com.ubtechinc.cruzr.map.action.STOP_NAVIGATION_ACTION";
  protected static final String MAP_URI = "content://com.ubtechinc.cruzr.map.mapProvider/map";
  protected static final String POINT_URI = "content://com.ubtechinc.cruzr.map.positionProvider/position";
  public static final float POSITION_INVALID_VALUE = 2.13909504E9F;
  
  public NavigationConstant() {}
}