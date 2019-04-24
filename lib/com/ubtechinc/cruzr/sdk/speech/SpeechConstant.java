package com.ubtechinc.cruzr.sdk.speech;

public class SpeechConstant
{
  public static final int WAKE_UP_TYPE_NONE = -1;
  public static final int WAKE_UP_TYPE_SPEECH = 0;
  public static final int WAKE_UP_TYPE_KEY = 1;
  public static final int WAKE_UP_TYPE_FACE_IN = 2;
  public static final int WAKE_UP_TYPE_FACE_OUT = 3;
  public static final int WAKE_UP_TYPE_LOOPER = 4;
  public static final int WAKE_UP_TYPE_STOP = 5;
  public static final int WAKE_UP_TYPE_RESUME = 6;
  public static final int SPEECH_DISPATCH_PERMISSION_NONE = -1;
  public static final int SPEECH_DISPATCH_PERMISSION_NLP = 0;
  public static final int SPEECH_DISPATCH_PERMISSION_ALL = 1;
  public static final int PROHIBIT_WAKE_UP_TYPE_SPEECH = 0;
  public static final int PROHIBIT_WAKE_UP_TYPE_KEY = 1;
  public static final int PROHIBIT_WAKE_UP_TYPE_FACE = 2;
  public static final int PROHIBIT_WAKE_UP_TYPE_ALL = 3;
  public static final String SPEECH_BEGIN = "com.ubtechinc.cruzr.coreservices.ACTION.SPEECH_BEGIN";
  public static final String SPEECH_END = "com.ubtechinc.cruzr.coreservices.ACTION.SPEECH_END";
  public static final String SPEECH_ERROR = "com.ubtechinc.cruzr.coreservices.ACTION.SPEECH_ERROR";
  public static final int COMMAND_OFFLINE = 0;
  public static final int COMMAND_ONLINE = 1;
  public static final int SPEECH_PRIORITY_HIGH = 2;
  public static final int SPEECH_PRIORITY_MIDDLE = 0;
  public static final int SPEECH_PRIORITY_LOW = 1;
  public static final String APP = "app";
  public static final String GLOBAL_CONTEXT = "globalContext";
  public static final String APP_CONTEXT = "appContext";
  
  public SpeechConstant() {}
}