package com.microwise.halley.quartz;


import com.microwise.halley.common.SysConfig;
import com.microwise.halley.service.*;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.io.IOException;


/**
 * Quartz 定时检查数据报警
 *
 * @author li.jianfei
 * @date 2013-11-12
 */
public class AlarmQuartz {
    /**
     * Quartz 调度对象
     */
    private Scheduler scheduler;

    private ExhibitionService exhibitionService;

    private ExhibitionStateService exhibitionStateService;

    private CarService carService;

    private LocationService locationService;

    private UserService userService;

    private PathService pathService;

    private AlarmService alarmService;

    private RouteHistoryService routeHistoryService;


    public AlarmQuartz(Scheduler scheduler, ExhibitionService exhibitionService,
                       ExhibitionStateService exhibitionStateService, CarService carService,
                       LocationService locationService, UserService userService, PathService pathService,
                       AlarmService alarmService,RouteHistoryService routeHistoryService) {
        this.scheduler = scheduler;
        this.exhibitionService = exhibitionService;
        this.exhibitionStateService = exhibitionStateService;
        this.carService = carService;
        this.locationService = locationService;
        this.userService = userService;
        this.pathService = pathService;
        this.alarmService = alarmService;
        this.routeHistoryService = routeHistoryService;
    }

    public static final String ALARM_QUARTZ_TRIGGER = "alarmQuartzTrigger";
    public static final String ALARM_QUARTZ_JOB = "alarmQuartzJob";
    public static final String HALLEY_GROUP = "halleyGroup";

    /**
     * 初始化任务计划
     */
    @SuppressWarnings("unused")
    private void initQuartz() throws SchedulerException, IOException {
        JobDetail job = JobBuilder.newJob(AlarmQuartzJob.class)
                .withIdentity(ALARM_QUARTZ_JOB, HALLEY_GROUP)
                .storeDurably().build();

        scheduler.addJob(job, false);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("exhibitionService", exhibitionService);
        jobDataMap.put("exhibitionStateService", exhibitionStateService);
        jobDataMap.put("carService", carService);
        jobDataMap.put("locationService", locationService);
        jobDataMap.put("userService", userService);
        jobDataMap.put("pathService", pathService);
        jobDataMap.put("alarmService", alarmService);
        jobDataMap.put("routeHistoryService",routeHistoryService);

        // 设置执行时间
        setTrigger(jobDataMap);
    }

    /**
     * 设置 计划任务
     *
     * @param jobDataMap 任务参数
     */
    private void setTrigger(JobDataMap jobDataMap) throws SchedulerException {
        Trigger simpleTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity(ALARM_QUARTZ_TRIGGER)
                .withSchedule(
                        SimpleScheduleBuilder.repeatSecondlyForever(SysConfig.dataConversionCycle)
                ).usingJobData(jobDataMap)
                .forJob(ALARM_QUARTZ_JOB, HALLEY_GROUP).build();
        scheduler.scheduleJob(simpleTrigger);
    }

}
