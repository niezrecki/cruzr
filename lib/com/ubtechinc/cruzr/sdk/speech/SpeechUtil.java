package com.ubtechinc.cruzr.sdk.speech;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;


public class SpeechUtil
{
  public static final String TAG = "sdkThird_one";
  public static final String SPEECH_RST = "speech_rst";
  public static final int MSG_SEND_TO_SERVER = 3;
  public static final int SERVICE_STATE_START = 1;
  public static final int SERVICE_STATE_STOP = 2;
  public static final int SERVICE_STATE_ONPAUSE = 3;
  public static final int SERVICE_STATE_ONRESUME = 4;
  private int appId;
  private ISpeechContext mSpeechContext;
  
  public static SpeechUtil getInstance(Context context)
  {
    return SpeechUtil.SpeechUtilInstance.access$100();
  }
  



  private Handler mInComingHandler = new Handler(new SpeechUtil.1(this));
  


  private SpeechUtil() {}
  

  private String handleReceiveSpeechRst(Message msg)
  {
    String rst = null;
    Bundle bundle = msg.getData();
    if ((bundle != null) && (bundle.containsKey("speech_rst"))) {
      rst = bundle.getString("speech_rst");
    }
    return rst;
  }
  



  private Messenger mMessenger = new Messenger(mInComingHandler);
  

  private SpeechUtil.ISpeechBindListener mSpeechBindListener;
  

  public IBinder getIBinder()
  {
    return mMessenger.getBinder();
  }
  
  public void registerSpeech(ISpeechContext cb)
  {
    mSpeechContext = cb;
  }
  




  public void initialize(int appId, SpeechUtil.ISpeechBindListener listener)
  {
    this.appId = appId;
    mSpeechBindListener = listener;
  }
  
  public SpeechUtil.ISpeechBindListener getSpeechBindListener() {
    return mSpeechBindListener;
  }
}