package com.axm.web;

import com.axm.common.ResultVo;
import com.axm.entity.DuolingoProfile;
import com.axm.service.DuolingoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: AceXiamo
 * @ClassName: DuolingoController
 * @Date: 2023/8/2 14:32
 */
@RestController
@RequestMapping("/duolingo")
public class DuolingoController {

    @Autowired
    private DuolingoService duolingoService;

    @GetMapping("/profile")
    public ResultVo profile() {
        return ResultVo.success(duolingoService.getProfile());
    }

}
