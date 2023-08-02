package com.axm.task;

import com.axm.service.DuolingoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The type Duolingo task.
 *
 * @Author: AceXiamo
 * @ClassName: DuolingoTask
 * @Date: 2023 /8/2 15:07
 */
@Slf4j
@Component
public class DuolingoTask {

    @Autowired
    private DuolingoService duolingoService;

    /**
     * Task.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void task() {
        log.info("📚 duolingo task begin");
        duolingoService.syncProfile();
    }

}
