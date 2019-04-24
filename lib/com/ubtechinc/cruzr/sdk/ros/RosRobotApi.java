package com.ubtechinc.cruzr.sdk.ros;

import android.content.Context;
import com.ubtechinc.cruzr.serverlibutil.aidl.BatteryInfo;
import com.ubtechinc.cruzr.serverlibutil.aidl.Odometer;
import com.ubtechinc.cruzr.serverlibutil.aidl.Position;
import com.ubtechinc.cruzr.serverlibutil.interfaces.InitListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.RemoteBatteryListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.RemoteCommonListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.RemoteDiagnosticDataListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.RemoteEskinStatusListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.RemoteNavigationListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.RemotePowerListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.RemoteWarnListener;
import com.ubtechinc.cruzr.serverlibutil.service.RosControlServiceUtil;
import java.util.List;





public class RosRobotApi
{
  private RosControlServiceUtil mRosControlServiceUtil;
  private static volatile RosRobotApi mRosRobotApi;
  
  public RosRobotApi() {}
  
  public static RosRobotApi get()
  {
    if (mRosRobotApi == null) {
      synchronized (RosRobotApi.class) {
        if (mRosRobotApi == null) {
          mRosRobotApi = new RosRobotApi();
        }
      }
    }
    return mRosRobotApi;
  }
  
  private void initializ(Context context) {
    if (mRosControlServiceUtil == null) {
      mRosControlServiceUtil = new RosControlServiceUtil(context);
    }
  }
  







  public void initializ(Context context, InitListener listener)
  {
    if (mRosControlServiceUtil == null) {
      mRosControlServiceUtil = new RosControlServiceUtil(context, listener);
    }
  }
  




  public void destory()
  {
    if (mRosControlServiceUtil == null) {
      return;
    }
    mRosControlServiceUtil.destory();
  }
  









  public int registerCommonCallback(RemoteCommonListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.registerCommonCallback(listener);
  }
  








  public int registerWarnCallback(RemoteWarnListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.registerWarnCallback(listener);
  }
  







  public int registerNavigationCallback(RemoteNavigationListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.registerNavigationCallback(listener);
  }
  






  public int registerBatteryCallback(RemoteBatteryListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.registerBatteryCallback(listener);
  }
  








  public int registerPowerCallback(RemotePowerListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.registerPowerCallback(listener);
  }
  








  public int registerDiagnosticDataCallback(RemoteDiagnosticDataListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.registerDiagnosticDataCallback(listener);
  }
  


















  public int moveToward(float x, float y, float theta)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.moveToward(x, y, theta);
  }
  


















  public int moveToward(float x, float y, float theta, RemoteCommonListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    
    return mRosControlServiceUtil.moveToward(x, y, theta, listener);
  }
  



















  public int moveTo(float x, float y, float theta, float maxSpeed)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.moveTo(x, y, theta, maxSpeed);
  }
  



















  public int moveTo(float x, float y, float theta, float maxSpeed, RemoteCommonListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.moveTo(x, y, theta, maxSpeed, listener);
  }
  












  public boolean isMoveActive()
  {
    boolean ret = false;
    if (mRosControlServiceUtil == null) {
      return ret;
    }
    return mRosControlServiceUtil.isMoveActive();
  }
  












  public int stopMove()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.stopMove();
  }
  













  public Position getPosition(boolean useSensor)
  {
    if (mRosControlServiceUtil == null) {
      return null;
    }
    return mRosControlServiceUtil.getPosition(useSensor == true ? 1 : 0);
  }
  













  public float getMaxSpeed(int dir)
  {
    if (mRosControlServiceUtil == null) {
      return 0.0F;
    }
    return mRosControlServiceUtil.getMaxSpeed(dir);
  }
  











  public BatteryInfo getBattery()
  {
    if (mRosControlServiceUtil == null) {
      return null;
    }
    return mRosControlServiceUtil.getBattery();
  }
  













  public String[] getBodyNames(String part)
  {
    String[] ret = null;
    if (mRosControlServiceUtil == null) {
      return ret;
    }
    return mRosControlServiceUtil.getBodyNames(part);
  }
  











  public String[] getSensorNames()
  {
    String[] ret = null;
    if (mRosControlServiceUtil == null) {
      return ret;
    }
    return mRosControlServiceUtil.getSensorNames();
  }
  











  public float[] getLimits()
  {
    float[] ret = null;
    if (mRosControlServiceUtil == null) {
      return ret;
    }
    return mRosControlServiceUtil.getLimits();
  }
  














  public int run(String name)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    String[] names = new String[1];
    names[0] = name;
    return mRosControlServiceUtil.run(names);
  }
  














  public int run(String name, RemoteCommonListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    String[] names = new String[1];
    names[0] = name;
    return mRosControlServiceUtil.run(names, listener);
  }
  













  public int run(String[] names)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.run(names);
  }
  














  public int run(String[] names, RemoteCommonListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.run(names, listener);
  }
  












  public int stopRun()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.stopRun();
  }
  



















  public int navigateTo(float x, float y, float theta, float maxSpeed)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.navigateTo(x, y, theta, maxSpeed);
  }
  











  public int cancelNavigate()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.cancelNavigate();
  }
  












  public int navigateRelocationCtrl(int opt)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.navigateRelocationCtrl(opt);
  }
  
















  public int gotoRecharge(float x, float y)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.gotoRecharge(x, y);
  }
  











  public int leaveStation()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.leaveStation();
  }
  

















  public int syncToRos(float x, float y, float theta)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.syncToRos(x, y, theta);
  }
  












  public int ledSetOnOff(boolean onOff)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.ledSetOnOff(onOff == true ? 1 : 0);
  }
  












  public int ledSetEffect(int lightEffect, int brightness, int color)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.ledSetEffect(lightEffect, brightness, color);
  }
  



















  public int ledSetColor(int r, int g, int b, int times)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.ledSetColor(r, g, b, times);
  }
  













  public int powerOffRos(int type)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.powerOffRos(type);
  }
  











  public String getRosWifiIp()
  {
    String ret = null;
    if (mRosControlServiceUtil == null) {
      return ret;
    }
    return mRosControlServiceUtil.getRosWifiIp();
  }
  
















  public int setRosWifi(String name, String password)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.setRosWifi(name, password);
  }
  












  public int startPS3joy()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.startPS3joy();
  }
  
















  public int buildMap(int operate, int option)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.buildMap(operate, option);
  }
  













  public int setCurrentMap(String name)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.setCurrentMap(name);
  }
  











  public String getCurrentMap()
  {
    if (mRosControlServiceUtil == null) {
      return "";
    }
    return mRosControlServiceUtil.getCurrentMap();
  }
  




















  public int angleInterpolation(String[] names, List<float[]> angles, List<float[]> times, int absolute)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.angleInterpolation(names, angles, times, absolute);
  }
  




















  public int angleInterpolationWithSpeed(String[] names, List<float[]> angles, float max_speed, int absolute)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.angleInterpolationWithSpeed(names, angles, max_speed, absolute);
  }
  


















  public int setAngles(String name, float angle, float time)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.setAngles(new String[] { name }, new float[] { angle }, new float[] { time }, 0);
  }
  


















  public int setAngles(String[] names, float[] angles, float[] times)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.setAngles(names, angles, times, 0);
  }
  











  public int stopJoint()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.stopJoint();
  }
  














  public float getJointCurrentAngle(String name)
  {
    if (mRosControlServiceUtil == null) {
      return 0.0F;
    }
    return mRosControlServiceUtil.getJointCurrentAngle(name);
  }
  











  public String getRosVersion()
  {
    if (mRosControlServiceUtil == null) {
      return "";
    }
    return mRosControlServiceUtil.getRosVersion();
  }
  











  public int dockOnStation()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.dockOnStation();
  }
  












  public int cancelDockOn()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.cancelDockOn();
  }
  















  public int ledSetWorkByTimes(int effect, int brightness, int color, int times)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.ledSetWorkByTimes(effect, brightness, color, times);
  }
  











  public int clearMap()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.clearMap();
  }
  












  public int zeroSetting()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.zeroSetting();
  }
  
















  public String[] getVersionInfo()
  {
    String[] ret = null;
    if (mRosControlServiceUtil == null) {
      return ret;
    }
    return mRosControlServiceUtil.getVersionInfo();
  }
  














  public int getDiagnosticStatus(int key)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.getDiagnosticStatus(key);
  }
  











  public int startPS3joyMove()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.startPS3joyMove();
  }
  











  public int servoEnergyRelease()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.servoEnergyRelease();
  }
  















  public int diagnosticDataReportCtrl(int key, int ctrlflag)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.diagnosticDataReportCtrl(key, ctrlflag);
  }
  

















  public int navigateRelocationStartByPos(float x, float y, float theta)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.navigateRelocationStartByPos(x, y, theta);
  }
  













  public int humanDetectCtrl(int opt)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.humanDetectCtrl(opt);
  }
  











  public float[] getHumiture()
  {
    float[] ret = null;
    if (mRosControlServiceUtil == null) {
      return ret;
    }
    return mRosControlServiceUtil.getHumiture();
  }
  
  
  
  
  
  
  

  public int runWithEskinEnable(String[] name, int eskinMask)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.runWithEskinEnable(name, eskinMask);
  }
  






  public int registerEskinStatusCallback(RemoteEskinStatusListener listener)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.registerEskinStatusCallback(listener);
  }
  










  public int setNavigateSpeed(float speed)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.setNavigateSpeed(speed);
  }
  








  public float getNavigateSpeed()
  {
    if (mRosControlServiceUtil == null) {
      return 0.0F;
    }
    return mRosControlServiceUtil.getNavigateSpeed();
  }
  












  public int navigateToByPresetedSpeed(float x, float y, float theta)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.navigateToByPresetedSpeed(x, y, theta);
  }
  













  private int setProperty(String key, String value)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.setProperty(key, value);
  }
  










  public String getProperty(String key, String defalutValue)
  {
    if (mRosControlServiceUtil == null) {
      return "";
    }
    return mRosControlServiceUtil.getProperty(key, defalutValue);
  }
  





















  public int setFingerAdjust(String value)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.setFingerAdjust(value);
  }
  











  public int firmwareUpgrade()
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.firmwareUpgrade();
  }
  























  public int navigateTrackTo(float x, float y, float theta, float maxSpeed, int retryCount, int interval)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.navigateTrackTo(x, y, theta, maxSpeed, retryCount, interval);
  }
  













  public int machineReset(String moduleName)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.machineReset(moduleName);
  }
  


  public int getMoveStatus()
  {
    boolean ret = false;
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.getMoveStatus();
  }
  
















  public int powerOn(String currentTime, String scheduleTime, String option)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.powerOn(currentTime, scheduleTime, option);
  }
  
  
  
  
  
  
  
  public int moveTo2(float x, float y, float theta, float maxSpeed)
  {
    if (mRosControlServiceUtil == null) {
      return 0;
    }
    return mRosControlServiceUtil.moveTo2(x, y, theta, maxSpeed);
  }
  












  public Odometer getCurrentOdmInfo()
  {
    if (mRosControlServiceUtil == null) {
      return null;
    }
    return mRosControlServiceUtil.getCurrentOdmInfo();
  }
}