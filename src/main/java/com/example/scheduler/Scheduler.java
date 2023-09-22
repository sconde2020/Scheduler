package com.example.scheduler;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

import static com.cronutils.model.CronType.SPRING;

@Component
@ConditionalOnProperty({"cron.task.one", "cron.task.two"})
public class Scheduler {
    @Autowired
    Task1 task1;

    @Autowired
    Task2 task2;

    @Value("${cron.task.one}")
    private String cronTaskOne;

    @Value("${cron.task.two}")
    private String cronTaskTwo;

    @PostConstruct
    public void init() {
        System.out.println("cronTaskOne: <" + cronTaskOne + ">");
        System.out.println("Task1 executes " + parseCron(cronTaskOne));
        System.out.println("Task2 executes " + parseCron(cronTaskTwo));
    }

    @Scheduled(cron="${cron.task.one}")
    public void scheduleTask() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("About taskOne starts");
            task1.taskOne();
            Thread.sleep(1000);
        }
    }

    @Scheduled(cron="${cron.task.two}")
    public void scheduleTask2() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("About taskTwo starts");
            task2.taskTwo();
            Thread.sleep(1000);
        }
    }

    public String parseCron(String cron) {
        try {
            CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(SPRING);
            CronParser parser = new CronParser(cronDefinition);
            CronDescriptor descriptor = CronDescriptor.instance(Locale.US);
            return descriptor.describe(parser.parse(cron));
        } catch (Exception e) {
            System.out.println("ERROR WHILE PARSING CRON EXPRESSION: " + e);
            return null;
        }
    }
}
