package com.axm.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The type Site preview service.
 *
 * @Author: AceXiamo
 * @ClassName: SitePreviewService
 * @Date: 2023 /5/24 14:47
 */
@Service
public class SitePreviewService {

    /**
     * The Web driver.
     */
    private WebDriver webDriver;

    /**
     * The Font.
     */
    @Value("${selenium.font}")
    private String font;

    /**
     * Init.
     */
    public void init() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.getInstance().browserInDocker();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments(font);
        webDriver = new ChromeDriver(options);
    }

    /**
     * Take screenshot byte [ ].
     *
     * @param url the url
     * @return the byte [ ]
     */
    public byte[] takeScreenshot(String url) {
        webDriver.get(url);
        webDriver.manage().window().setSize(new Dimension(1920, 1080));
        ThreadUtil.sleep(1000);
        TakesScreenshot screenshot = ((TakesScreenshot) webDriver);
        return screenshot.getScreenshotAs(OutputType.BYTES);
    }


    /**
     * Dolingo byte [ ].
     *
     * @param path the path
     * @return the byte [ ]
     */
    public byte[] staticFile(String path) {
        webDriver.get("file://" + path);
        webDriver.manage().window().setSize(new Dimension(235, 140));
        ThreadUtil.sleep(1000);
        TakesScreenshot screenshot = ((TakesScreenshot) webDriver);
        return screenshot.getScreenshotAs(OutputType.BYTES);
    }
}
