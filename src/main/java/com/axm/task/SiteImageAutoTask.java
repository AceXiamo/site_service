package com.axm.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import com.axm.service.SitePreviewService;
import com.axm.utils.PreviewUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The type Site image auto task.
 *
 * @Author: AceXiamo
 * @ClassName: SiteImageAutoTask
 * @Date: 2023 /5/24 22:04
 */
@Slf4j
@Component
public class SiteImageAutoTask {

    @Autowired
    private SitePreviewService service;

    private static final String[] siteArr = new String[]{
            "https://axm.moe",
    };

    /**
     * Auto task.
     */
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void autoTask() {
        log.info("📸 screenshot task begin at {}", DateTime.now());
        for (String site : siteArr) {
            previewImageGenerate(site);
        }
        log.info("📸 screenshot task end at {}", DateTime.now());
    }

    /**
     * Preview image generate.
     *
     * @param url the url
     */
    public void previewImageGenerate(String url) {
        try {
            byte[] bytes = service.takeScreenshot(url);
            FileUtil.writeBytes(bytes, PreviewUtil.SAVE_PATH + PreviewUtil.fileNameHandle(url) + ".png");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
