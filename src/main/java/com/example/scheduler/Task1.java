package com.example.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Task1 {

    Logger logger = LoggerFactory.getLogger("taskOne");

    public void taskOne() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss.SSS");

        String strDate = dateFormat.format(new Date());
        System.out.println(
                "Cron job 1 Scheduler: Job running at - "
                        + strDate);
    }

}
