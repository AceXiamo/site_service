package com.axm.web;

import com.axm.service.SitePreviewService;
import com.axm.utils.PreviewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * The type Image controller.
 *
 * @Author: AceXiamo
 * @ClassName: ImageController
 * @Date: 2023 /5/24 14:01
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private SitePreviewService sitePreviewService;

    /**
     * Get.
     *
     * @param url      the url
     * @param response the response
     */
    @GetMapping("/get")
    public void get(@RequestParam(name = "url") String url, HttpServletResponse response) {
        try {
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            byte[] imageBytes = PreviewUtil.bytesByUrl(url);
            os.write(imageBytes);
            os.flush();
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Create.
     *
     * @param url      the url
     * @param response the response
     */
    @GetMapping("/create")
    public void create(@RequestParam(name = "url") String url, HttpServletResponse response) {
        try {
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            byte[] imageBytes = sitePreviewService.takeScreenshot(url);
            os.write(imageBytes);
            os.flush();
        }catch (Exception e) {
            System.out.println(e);
        }
    }

}
