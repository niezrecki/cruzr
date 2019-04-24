package com.ubtechinc.cruzr.sdk.speech;

public abstract interface ISpeechContext
{
  public abstract void onStart();
  
  public abstract void onStop();
  
  public abstract void onPause();
  
  public abstract void onResume();
  
  public abstract void onResult(String paramString);
}