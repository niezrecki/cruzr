package com.ubtechinc.cruzr.sdk.speech;

import android.content.Context;
import com.ubtechinc.cruzr.serverlibutil.aidl.SpeechVoice;
import com.ubtechinc.cruzr.serverlibutil.interfaces.InitListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.SpeechASRListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.SpeechTtsListener;
import com.ubtechinc.cruzr.serverlibutil.interfaces.SpeechVolumeListener;
import com.ubtechinc.cruzr.serverlibutil.service.SpeechMainServiceUtil;
import java.util.List;
















public class SpeechRobotApi
{
  private SpeechMainServiceUtil mSpeechServiceUtil;
  private static volatile SpeechRobotApi mSpeechRobotApi;
  private Context mContext;
  
  public SpeechRobotApi() {}
  
  public static SpeechRobotApi get()
  {
    if (mSpeechRobotApi == null) {
      synchronized (SpeechRobotApi.class) {
        if (mSpeechRobotApi == null) {
          mSpeechRobotApi = new SpeechRobotApi();
        }
      }
    }
    return mSpeechRobotApi;
  }
  
  private SpeechRobotApi initializ(Context context) {
    if (mSpeechServiceUtil == null) {
      mSpeechServiceUtil = new SpeechMainServiceUtil(context);
    }
    return get();
  }
  
  private SpeechRobotApi initializ(Context context, InitListener listener) {
    if (mSpeechServiceUtil == null) {
      mSpeechServiceUtil = new SpeechMainServiceUtil(context, listener);
    }
    return get();
  }
  
  private SpeechRobotApi initializ(Context context, int appId) {
    initializ(context);
    SpeechUtil.getInstance(context).initialize(appId, new SpeechRobotApi.1(this));
    



    return get();
  }
  
























  public SpeechRobotApi initializ(Context context, int appId, InitListener listener)
  {
    initializ(context, listener);
    SpeechUtil.getInstance(context).initialize(appId, new SpeechRobotApi.2(this));
    



    return get();
  }
  






  public void registerSpeech(ISpeechContext isc)
  {
    SpeechUtil.getInstance(mContext).registerSpeech(isc);
  }
  







  public int registerSpeechVolume(SpeechVolumeListener listener)
  {
    if (mSpeechServiceUtil == null) {
      return 0;
    }
    return mSpeechServiceUtil.registerSpeechVolume(listener);
  }
  



  public void destory()
  {
    if (mSpeechServiceUtil == null) {
      return;
    }
    mSpeechServiceUtil.destory();
  }
  










  private int speechSetVoiceName(String strVoiceName)
  {
    int nState = 1;
    if (mSpeechServiceUtil == null) {
      nState = 0;
      return nState;
    }
    
    mSpeechServiceUtil.setVoiceName(strVoiceName);
    return nState;
  }
  








  private int speechSetTtsSpeed(int speed)
  {
    int nState = 1;
    if (mSpeechServiceUtil == null) {
      nState = 0;
      return nState;
    }
    mSpeechServiceUtil.setTtsSpeed(speed);
    return nState;
  }
  







  public int speechSetTtsVolume(int volume)
  {
    int nState = 1;
    if (mSpeechServiceUtil == null) {
      nState = 0;
      return nState;
    }
    mSpeechServiceUtil.setTtsVolume(volume);
    return nState;
  }
  









  public int speechStartTTS(String text, SpeechTtsListener listener)
  {
    int nState = 1;
    if (mSpeechServiceUtil == null) {
      nState = 0;
      return nState;
    }
    mSpeechServiceUtil.onPlay(text, listener);
    return nState;
  }
  









  private int speechStartTTS(String text, String strVoicName, SpeechTtsListener listener)
  {
    int nState = 1;
    if (mSpeechServiceUtil == null) {
      nState = 0;
      return nState;
    }
    speechSetVoiceName(strVoicName);
    mSpeechServiceUtil.onPlay(text, listener);
    return nState;
  }
  





  public int speechStopTTS()
  {
    int nState = 1;
    if (mSpeechServiceUtil == null) {
      nState = 0;
      return nState;
    }
    mSpeechServiceUtil.onStopPlay();
    return nState;
  }
  








  public int startSpeechASR(SpeechASRListener listener)
  {
    if (mSpeechServiceUtil == null) {
      return 0;
    }
    
    mSpeechServiceUtil.startSpeechAsr(listener);
    return 1;
  }
  









  public int startSpeechASR(int time, int eostime, SpeechASRListener listener)
  {
    if (mSpeechServiceUtil == null) {
      return 0;
    }
    mSpeechServiceUtil.startSpeechAsr(time, eostime, listener);
    return 1;
  }
  







  public List<SpeechVoice> getSpeechVoices()
  {
    if (mSpeechServiceUtil == null) {
      return null;
    }
    return mSpeechServiceUtil.getSpeechVoices();
  }
  








  public SpeechVoice getCurSpeechVoices()
  {
    if (mSpeechServiceUtil == null) {
      return null;
    }
    return mSpeechServiceUtil.getCurSpeechVoice();
  }
  








  public boolean isTtsSpeaking()
  {
    int ret = 0;
    if (mSpeechServiceUtil == null) {
      return false;
    }
    ret = mSpeechServiceUtil.isTtsSpeaking();
    return ret == 1;
  }
  






  public int stopSpeechASR()
  {
    if (mSpeechServiceUtil == null) {
      return 0;
    }
    mSpeechServiceUtil.stopAsr();
    return 1;
  }
  



  public int speechGetTtsSpeed()
  {
    if (mSpeechServiceUtil == null)
    {
      return -1;
    }
    return mSpeechServiceUtil.speechGetTtsSpeed();
  }
  




  public String speechGetVoiceName()
  {
    String ret = null;
    if (mSpeechServiceUtil == null) {
      return ret;
    }
    return mSpeechServiceUtil.speechGetVoiceName();
  }
  





  private int speechSetDispatch(int appId, String content)
  {
    if (mSpeechServiceUtil == null) {
      return 0;
    }
    return mSpeechServiceUtil.speechSetDispatch(appId, content);
  }
  





  public int speechPermissionDispatch(int appId, int permission)
  {
    if (mSpeechServiceUtil == null) {
      return 0;
    }
    return mSpeechServiceUtil.speechPermissionDispatch(appId, permission);
  }
  






  private int speechLocalBuild(int appId, String content)
  {
    if (mSpeechServiceUtil == null) {
      return 0;
    }
    return mSpeechServiceUtil.speechLocalBuild(appId, content);
  }
  






  private List<String> getSpeechLocalBuild()
  {
    if (mSpeechServiceUtil == null) {
      return null;
    }
    return mSpeechServiceUtil.getSpeechLocalBuild();
  }
  








  public int enableWakeup(int wakeType, boolean enable)
  {
    if (mSpeechServiceUtil == null) {
      return 0;
    }
    return mSpeechServiceUtil.enableWakeup(wakeType, enable);
  }
  




  public int speechSetFrontBeam()
  {
    if (mSpeechServiceUtil == null) {
      return 0;
    }
    return mSpeechServiceUtil.setSpeechBeam(0);
  }
}