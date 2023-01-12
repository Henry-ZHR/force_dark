package moe.henry_zhr.force_dark;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MainHook implements IXposedHookLoadPackage {
  @Override
  public void handleLoadPackage(LoadPackageParam lppram) {
    XposedHelpers.findAndHookMethod(
        "android.os.SystemProperties",
        lppram.classLoader,
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