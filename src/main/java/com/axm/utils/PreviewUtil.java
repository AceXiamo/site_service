package com.axm.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.axm.service.SitePreviewService;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Preview util.
 *
 * @Author: AceXiamo
 * @ClassName: PreviewUtil
 * @Date: 2023 /5/24 22:24
 */
@Slf4j
public class PreviewUtil {

    /**
     * The constant SAVE_PATH.
     */
    public static final String SAVE_PATH = System.getProperty("user.dir") + "/preview/";

    private static final String[] siteArr = new String[]{
            "https://axm.moe",
            "https://xiamoqwq.com",
            "https://qwq.link",
            "https://image.qwq.link",
            "https://github.com/openjdk",
            "https://spring.io",
            "https://go.dev",
            "https://github.com/go-gorm",
            "https://www.iris-go.com",
            "https://zh.wikipedia.org/wiki/JavaScript",
            "https://zh.wikipedia.org/wiki/CSS",
            "https://zh.wikipedia.org/wiki/HTML",
            "https://www.typescriptlang.org",
            "https://sass-lang.com",
            "https://www.electronjs.org",
            "https://cn.vuejs.org",
            "https://nodejs.org",
            "https://flutter.dev",
            "https://ubuntu.com",
            "https://www.sqlite.org",
            "https://redis.io",
            "https://rabbitmq.com",
            "https://www.baomidou.com",
            "https://centos.org",
            "https://docker.com",
            "https://nginx.com",
            "https://mysql.com",
            "https://maven.apache.org",
            "https://www.selenium.dev",
            "https://pinia.vuejs.org",
            "https://fontawesome.com",
            "https://tailwindcss.cn",
            "https://unocss.dev",
            "https://vitejs.dev",
            "https://eslint.org",
            "https://prettier.io",
            "https://git-scm.com",
            "https://arc.net",
            "https://github.com",
            "https://www.jetbrains.com/idea",
            "https://www.jetbrains.com/webstorm",
            "https://www.jetbrains.com/go",
            "https://www.jetbrains.com/datagrip",
            "https://termius.com",
            "https://postman.com",
            "https://warp.dev",
            "https://raycast.com",
            "https://code.visualstudio.com",
            "https://openai.com",
            "https://github.com/features/copilot"
    };

    /**
     * Preview init.
     */
    public static void previewInit() {
        log.info("📸 screenshot init begin at {}", DateTime.now());
        SitePreviewService service = SpringUtil.getBean(SitePreviewService.class);
        try {
            for (String url : siteArr) {
                byte[] bytes = service.takeScreenshot(url);
                FileUtil.writeBytes(bytes, PreviewUtil.SAVE_PATH + PreviewUtil.fileNameHandle(url) + ".png");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        log.info("📸 screenshot init end at {}", DateTime.now());
    }

    /**
     * File name handle string.
     *
     * @param url the url
     * @return the string
     */
    public static String fileNameHandle(String url) {
        url = url.replace("https://", "");
        url = url.replace(".", "_");
        url = url.replace("/", "_");
        return url;
    }

    /**
     * Bytes by url byte [ ].
     *
     * @param url the url
     * @return the byte [ ]
     */
    public static byte[] bytesByUrl(String url) {
        return FileUtil.readBytes(SAVE_PATH + fileNameHandle(url) + ".png");
    }

}
