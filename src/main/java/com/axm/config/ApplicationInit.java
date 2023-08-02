package com.axm.config;

import com.axm.service.SitePreviewService;
import com.axm.utils.PreviewUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: AceXiamo
 * @ClassName: ApplicationInit
 * @Date: 2023/5/24 14:44
 */
@Slf4j
@Configuration
public class ApplicationInit implements CommandLineRunner {

    @Autowired
    private SitePreviewService driverConfig;

    @Override
    public void run(String... args) {
        log.info("load chrome driver 😎");
        driverConfig.init();
//        log.info("preview init 😎");
//        PreviewUtil.previewInit();
    }

}
