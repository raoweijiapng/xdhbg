package cn.xdh.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyPhone {
    public static boolean isMobile(final String str) {
      Pattern p = null;
      Matcher m = null;
      boolean b = false;
      p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
      m = p.matcher(str);
      b = m.matches();
      return b;
    }

}
