package moe.henry_zhr.force_dark;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class MainHook implements IXposedHookZygoteInit {
  @Override
  public void initZygote(StartupParam startupParam) {
    XposedHelpers.findAndHookMethod(
        "android.os.SystemProperties",
        null,
        "native_get_boolean",
        String.class,
        boolean.class,
        new XC_MethodHook() {
          @Override
          protected void beforeHookedMethod(MethodHookParam param) {
            if ("debug.hwui.force_dark".equals(param.args[0])) {
              param.setResult(true);
            }
          }
        }
    );
  }
}