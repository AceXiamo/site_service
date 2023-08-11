package com.axm.web;

import com.axm.common.ResultVo;
import com.axm.entity.DuolingoProfile;
import com.axm.service.DuolingoService;
import com.axm.service.SitePreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * The type Duolingo controller.
 *
 * @Author: AceXiamo
 * @ClassName: DuolingoController
 * @Date: 2023 /8/2 14:32
 */
@RestController
@RequestMapping("/duolingo")
public class DuolingoController {

    /**
     * The Duolingo service.
     */
    @Autowired
    private DuolingoService duolingoService;

    /**
     * Profile result vo.
     *
     * @return the result vo
     */
    @GetMapping("/profile")
    public ResultVo profile() {
        return ResultVo.success(duolingoService.getProfile());
    }

    /**
     * Image.
     *
     * @param response the response
     */
    @GetMapping("/image")
    public void image(HttpServletResponse response) {
        try {
            response.getOutputStream().write(duolingoService.getImage());
            response.getOutputStream().flush();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
