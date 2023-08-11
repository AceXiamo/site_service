package com.axm.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.axm.entity.DuolingoProfile;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * The type Duolingo service.
 *
 * @Author: AceXiamo
 * @ClassName: DuolingoService
 * @Date: 2023 /8/2 14:50
 */
@Service
public class DuolingoService {

    /**
     * The User id.
     */
    @Value("${duolingo.userId}")
    private String userId;

    /**
     * The Cookie.
     */
    @Value("${duolingo.cookie}")
    private String cookie;

    /**
     * The constant URL.
     */
    private static final String URL = "https://www.duolingo.cn/2017-06-30/users/";
    /**
     * The constant SAVE_PATH.
     */
    public static final String SAVE_PATH = System.getProperty("user.dir") + "/json/duolingo.json";
    /**
     * The constant duolingoHtmlPath.
     */
    private static final String duolingoHtmlPath = System.getProperty("user.dir") + "/static/duolingo/template.html";
    /**
     * The constant duolingoScreenshotPath.
     */
    private static final String duolingoScreenshotPath = System.getProperty("user.dir") + "/static/dolingo/image.png";

    /**
     * The Profile.
     */
    private DuolingoProfile profile = null;

    /**
     * The Site preview service.
     */
    @Autowired
    private SitePreviewService sitePreviewService;

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
     * Get image byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getImage() {
        return FileUtil.readBytes(duolingoScreenshotPath);
    }

    /**
     * Sync profile.
     */
    public void syncProfile() {
        String jsonStr = HttpRequest.get(URL + userId + "?fields=trackingProperties")
                .header("cookie", cookie)
                .execute().body();
        System.out.println("eee 😄");
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String str = jsonObject.getJSONObject("trackingProperties").toJSONString();
        profile = JSON.parseObject(str, DuolingoProfile.class);
        FileUtil.writeString(dolingoHtmlHandle(new BigDecimal(profile.getUtc_offset()).setScale(0).toString(), String.valueOf(profile.getStreak())), duolingoHtmlPath, "utf-8");
        FileUtil.writeBytes(sitePreviewService.staticFile(duolingoHtmlPath), duolingoScreenshotPath);
    }

    /**
     * Dolingo html handle string.
     *
     * @param level the level
     * @param day   the day
     * @return the string
     */
    private String dolingoHtmlHandle(String level, String day) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <title>Document</title>\n" +
                "    <style type=\"text/css\">\n" +
                "        .duolingo {\n" +
                "            width: 215px;\n" +
                "            height: 120px;\n" +
                "            display: flex;\n" +
                "            flex-direction: column;\n" +
                "            background-color: white;\n" +
                "            padding: 10px;\n" +
                "            border-radius: 8px;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-logo {\n" +
                "            width: 100px;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec {\n" +
                "            display: flex;\n" +
                "            gap: 10px;\n" +
                "            margin-top: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec > .fire-content {\n" +
                "            width: 70px;\n" +
                "            aspect-ratio: 73/90;\n" +
                "            background-image: url(https://d35aaqx5ub95lt.cloudfront.net/images/achievements/217492e7baf0961abdd2ddfb5881e7f9.svg);\n" +
                "            background-repeat: no-repeat;\n" +
                "            background-size: cover;\n" +
                "            position: relative;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec > .fire-content > span {\n" +
                "            position: absolute;\n" +
                "            bottom: 10px;\n" +
                "            color: white;\n" +
                "            font-size: 14px;\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec > .desc {\n" +
                "            display: flex;\n" +
                "            flex-direction: column;\n" +
                "            gap: 5px;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec > .desc > .desc-item {\n" +
                "            display: flex;\n" +
                "            gap: 10px;\n" +
                "            align-items: center;\n" +
                "            position: relative;\n" +
                "            width: -moz-max-content;\n" +
                "            width: max-content;\n" +
                "            min-width: 30px;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec > .desc > .desc-item > span:nth-child(1) {\n" +
                "            color: #333;\n" +
                "            font-size: 12px;\n" +
                "            width: 24px;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec > .desc > .desc-item > span:nth-child(2) {\n" +
                "            color: white;\n" +
                "            font-size: 12px;\n" +
                "            padding: 2px 6px;\n" +
                "            border-radius: 3px;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec > .desc > .desc-item > img {\n" +
                "            width: 30px;\n" +
                "            height: 30px;\n" +
                "            object-fit: cover;\n" +
                "            position: absolute;\n" +
                "            right: 0;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec > .desc > .desc-user {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: 5px;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec > .desc > .desc-user > img {\n" +
                "            width: 24px;\n" +
                "            height: 24px;\n" +
                "            -o-object-fit: cover;\n" +
                "            object-fit: cover;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);\n" +
                "            border-radius: 100%;\n" +
                "        }\n" +
                "\n" +
                "        .duolingo > .duolingo-sec > .desc > .desc-user > span {\n" +
                "            margin-left: 5px;\n" +
                "            font-size: 12px;\n" +
                "            padding: 2px 6px;\n" +
                "            border-radius: 3px;\n" +
                "            background-color: #7c3aed;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        /*# sourceMappingURL=style.css.map */\n" +
                "\n" +
                "        body {\n" +
                "            background-color: #333;\n" +
                "            margin: unset;\n" +
                "        }\n" +
                "\n" +
                "        @font-face {\n" +
                "            font-family: 'AllFont';\n" +
                "            src: url('https://xiamo.oss-cn-shenzhen.aliyuncs.com/font/%E7%AD%91%E7%B4%ABA%E4%B8%B8.woff2');\n" +
                "            font-weight: 400;\n" +
                "            font-style: normal;\n" +
                "            font-display: swap;\n" +
                "        }\n" +
                "\n" +
                "        *:not([class*='icon']):not(i) {\n" +
                "            font-family: 'AllFont' !important;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"duolingo\">\n" +
                "    <img class=\"duolingo-logo\" src=\"https://d35aaqx5ub95lt.cloudfront.net/vendor/70a4be81077a8037698067f583816ff9.svg\" />\n" +
                "    <div class=\"duolingo-sec\">\n" +
                "        <div class=\"fire-content\">\n" +
                "            <span>第 ");
        builder.append(level);
        builder.append(" 级</span>\n" +
                "        </div>\n" +
                "        <div class=\"desc\">\n" +
                "            <div class=\"desc-user\">\n" +
                "                <img src=\"https://axm.moe/avatar\" />\n" +
                "                <span>AceXiamo</span>\n" +
                "            </div>\n" +
                "            <div class=\"desc-item\">\n" +
                "                <span>连胜</span>\n" +
                "                <span style=\"background-color: #fe9700\">");
        builder.append(day + "d");
        builder.append("</span>\n" +
                "                <img style=\"transform: translateX(100%) translateY(-1px)\" src=\"https://d35aaqx5ub95lt.cloudfront.net/images/1994201dce8a55a0017d59b58a035fc3.svg\" />\n" +
                "            </div>\n" +
                "            <div class=\"desc-item\">\n" +
                "                <img style=\"transform: translateX(calc(0%)) translateY(15px)\" src=\"https://d35aaqx5ub95lt.cloudfront.net/images/9cb1bd734855384c2de08fe80443af9f.svg\" />\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
        return builder.toString();
    }

}
