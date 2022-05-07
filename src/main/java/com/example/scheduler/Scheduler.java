package com.example.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    Task1 task1;

    @Autowired
    Task2 task2;

    // Method
    // To trigger the scheduler every one minute
    // between 19:00 PM to 19:59 PM
    @Scheduled(cron = "0 * 19 * * MON-SAT")
    public void scheduleTask()
    {
        task1.taskOne();
    }

    @Scheduled(fixedRate = 2000)
    public void scheduleTask2()
    {
     task2.taskTwo();
    }
}
