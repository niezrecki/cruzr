

package com.ubtechinc.cruzr.sdk.object;








public class StringUtils
{
  public StringUtils() {}
  






  public static boolean isEmpty(String str)
  {
    if ((str == null) || (str.equals("")) || (str.length() == 0)) {
      return true;
    }
    return false;
  }
}