package com.ubtechinc.cruzr.sdk.Object;

import java.io.Serializable;
import java.util.ArrayList;

public class ObjectListJson
  implements Serializable
{
  public ArrayList<ObjectInfo> objectList;
  
  public ObjectListJson() {}
}

// Create additional JSON parser for the callback
// import GSON lib by google