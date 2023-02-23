package net.fadi.jpa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;


// @Configuration: to create a bean from this class in container
//@Configuration

// @EnableScheduling: to tell springboot this class contain a scheduled methods
@EnableScheduling

@EnableAsync
public class Scheduled {
    Logger logger = LoggerFactory.getLogger(Scheduled.class);

    /*
        *create a simple schedule method (implemented each 2 seconds)
     */
    @org.springframework.scheduling.annotation.Scheduled(fixedRate = 2)
    @Async
    public void testSchedule() throws InterruptedException{
      // using sleep to make this method need 4 seconds to end
        Thread.sleep(4000);
        logger.info("This Letter will be appeared every 2 seconds!");
    }
}
