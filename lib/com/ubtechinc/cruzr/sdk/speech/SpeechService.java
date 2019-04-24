package com.ubtechinc.cruzr.sdk.speech;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SpeechService extends Service
{
  private static final String TAG = SpeechService.class.getSimpleName();
  private SpeechUtil dispatch = SpeechUtil.getInstance(this);
  
  public SpeechService() {}
  
  public IBinder onBind(Intent intent) { SpeechUtil.ISpeechBindListener mListener = dispatch.getSpeechBindListener();
    if (mListener != null) {
      mListener.onBindSpeech();
    }
    return dispatch.getIBinder();
  }
}