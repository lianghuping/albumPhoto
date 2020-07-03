package com.truck.photo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClearCacheUtil {
    private ClearCacheUtil() {
    }

    public static ClearCacheUtil getInstance() {
        return ClearCacheUtilHolder.sInstance;
    }

    private static class ClearCacheUtilHolder {
        private static final ClearCacheUtil sInstance = new ClearCacheUtil();
    }

    private int systemsize = 0;

    public void getCleatData(final Context context, final CallBackData callBackData) {
        new Thread(new Runnable() {

            private Drawable drawable;

            @Override
            public void run() {
//                获取应用程序列表
                List<ClearCacheBean> list = new ArrayList<>();
                PackageManager pm = context.getPackageManager();
                List<PackageInfo> packages = pm.getInstalledPackages(0);
                for (int i = 0; i < packages.size(); i++) {
                    // 判断系统/非系统应用
                    if ((packages.get(i).applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) // 非系统应用
                    {
                        Drawable drawable = packages.get(i).applicationInfo.loadIcon(pm);
                        File file = new File(PathManager.getBasePath() + "/Android/data/" + packages.get(i).packageName + "/cache");
                        long size = FileUtil.getFolderSize(file);
                        list.add(new ClearCacheBean(packages.get(i).applicationInfo.loadLabel(pm).toString(), packages.get(i).packageName, drawable, size));
                        String packageName = packages.get(i).packageName;
                        if (packageName.equals("com.tencent.mobileqq") && list.size() >= 2) {
                            Collections.swap(list, 1, list.size() - 1);
                        }
                        if (packageName.equals("com.tencent.mm") && list.size() >= 3) {
                            Collections.swap(list, 2, list.size() - 1);
                        }
                        if (packageName.equals("com.eg.android.AlipayGphone") && list.size() >= 4) {
                            Collections.swap(list, 3, list.size() - 1);
                        }
                        if (packageName.equals("com.taobao.taobao") && list.size() >= 5) {
                            Collections.swap(list, 4, list.size() - 1);
                        }
                        if (packageName.equals("com.ss.android.ugc.aweme") && list.size() >= 6) {
                            Collections.swap(list, 5, list.size() - 1);
                        }
                    } else {
                        // 系统应用
                        File file = new File(PathManager.getBasePath() + "/Android/data/" + packages.get(i).packageName + "/cache");
                        long size = FileUtil.getFolderSize(file);
                        systemsize += size;
                        String packageName = packages.get(i).packageName;
                        if (packageName.equals("com.android.settings")) {
                            drawable = packages.get(i).applicationInfo.loadIcon(pm);
                        }
                    }
                    if (i == packages.size() - 1) {
                        list.add(new ClearCacheBean("系统缓存", "com.android.settings", drawable, systemsize));
                        Collections.swap(list, 0, list.size() - 1);
                        systemsize = 0;
                    }
                }
                callBackData.getdata(list);
            }
        }).start();
    }

    public interface CallBackData {
        void getdata(List<ClearCacheBean> list);
    }

}
