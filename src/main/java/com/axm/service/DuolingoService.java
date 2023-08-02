package com.axm.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.axm.entity.DuolingoProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The type Duolingo service.
 *
 * @Author: AceXiamo
 * @ClassName: DuolingoService
 * @Date: 2023 /8/2 14:50
 */
@Service
public class DuolingoService {

    @Value("${duolingo.userId}")
    private String userId;

    @Value("${duolingo.cookie}")
    private String cookie;

    private static final String URL = "https://www.duolingo.cn/2017-06-30/users/";
    public static final String SAVE_PATH = System.getProperty("user.dir") + "/json/duolingo.json";

    private DuolingoProfile profile = null;

    /**
     * Gets profile.
     *
     * @return the profile
     */
    public DuolingoProfile getProfile() {
        if (profile == null) {
            if (!FileUtil.exist(SAVE_PATH)) return null;
            String jsonStr = FileUtil.readString(SAVE_PATH, "utf-8");
            profile = JSON.parseObject(jsonStr, DuolingoProfile.class);
        }
        return profile;
    }

    /**
     * Sync profile.
     */
    public void syncProfile() {
        String jsonStr = HttpRequest.get(URL + userId + "?fields=trackingProperties")
                .header("cookie", cookie)
                .execute().body();

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String str = jsonObject.getJSONObject("trackingProperties").toJSONString();
        profile = JSON.parseObject(str, DuolingoProfile.class);
        FileUtil.writeString(str, SAVE_PATH, "utf-8");
    }

}
