package com.truck.photo;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathManager {

    //86378740574354687354635435468798
    //32fdfgwe
    private static PathManager sPathManager;

    public List<String> wechat_user_paths = new ArrayList<>();
    public List<String> wechat_image_paths = new ArrayList<>();
    public List<String> wechat_video_paths = new ArrayList<>();
    public List<String> wechat_voice_paths = new ArrayList<>();


    private String basePath;

    public String qqBasePath;
    public String qqBasePathOld;
    public String[] qqBasePaths;

    public List<String> qq_voice_paths = new ArrayList<>();
    public List<String> tim_voice_paths = new ArrayList<>();


    private PathManager() {
    }

    public static PathManager get() {
        if (sPathManager == null) {
            sPathManager = new PathManager();
        }
        if (sPathManager.wechat_user_paths.size() == 0) {
            sPathManager.init();
        }
        return sPathManager;
    }

    public void init() {

        basePath = Environment.getExternalStorageDirectory().getPath();
        initWechat();
        initQQ();
        initTim();
    }

    private void initWechat() {
        String[] wechatBasePaths = new String[]{basePath + "/tencent/MicroMsg", basePath + "/Android/data/com.tencent.mm/MicroMsg"};

        for (String wechatBasePath : wechatBasePaths) {
            File wechatBaseDir = new File(wechatBasePath);
            File[] files = wechatBaseDir.listFiles();
            if (files == null || files.length == 0) return;
            for (File file : files) {
                if (file.getName().length() > 24) {
                    wechat_user_paths.add(file.getPath());
                }   
            }
        }
        for (String s : wechat_user_paths) {
            wechat_image_paths.add(s + "/image2");
            wechat_video_paths.add(s + "/video");
            wechat_video_paths.add(s + "/record");
            wechat_voice_paths.add(s + "/voice2");
        }
        Log.e("tag","");
    }

    private void initQQ() {
        qqBasePath = basePath + "/Android/data/com.tencent.mobileqq/Tencent/MobileQQ";
        qqBasePathOld = basePath + "/tencent/MobileQQ";
        qqBasePaths = new String[]{qqBasePath, qqBasePathOld};

        for (String qqPath : qqBasePaths) {
            File file_qqPath = new File(qqPath);
            File[] files = file_qqPath.listFiles();
            if (files == null || files.length == 0) continue;
            for (File file : files) {
                String name = file.getName();
                Pattern pattern = Pattern.compile("\\d{1,11}");
                Matcher matcher = pattern.matcher(name);
                if (matcher.find()) {
                    String group = matcher.group(0);
                    if (group.length() == name.length()) {
                        qq_voice_paths.add(file.getPath());
                    }
                }
            }
        }

    }

    private void initTim() {
        File file_qqPath = new File(basePath + "/tencent/Tim");
        File[] files = file_qqPath.listFiles();
        if (files == null || files.length == 0) return;
        for (File file : files) {
            String name = file.getName();
            Pattern pattern = Pattern.compile("\\d{1,11}");
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                String group = matcher.group(0);
                if (group.length() == name.length()) {
                    tim_voice_paths.add(file.getPath());
                }
            }
        }

    }


    public static String getBasePath() {
        return PathManager.get().basePath();
    }


    private String basePath() {
        return basePath;
    }

}