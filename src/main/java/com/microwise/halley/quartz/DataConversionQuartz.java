package com.microwise.halley.quartz;

import com.microwise.halley.common.SysConfig;
import com.microwise.halley.service.*;
import org.quartz.*;

import java.io.IOException;

/**
 * Quartz 定时检查转换 GPS 数据
 *
 * @author li.jianfei
 * @date 2013-11-14
 */
@Deprecated
public class DataConversionQuartz {

    private Scheduler scheduler;
    private ExhibitionService exhibitionService;
    private CarService carService;
 //   private DeviceService deviceService;
    private RouteHistoryService routeHistoryService;
    private LocationService locationService;

    public DataConversionQuartz(Scheduler scheduler, ExhibitionService exhibitionService, CarService carService,
                                LocationService locationService, RouteHistoryService routeHistoryService) {
        this.scheduler = scheduler;
        this.exhibitionService = exhibitionService;
        this.carService = carService;
     //   this.deviceService = deviceService;
        this.routeHistoryService = routeHistoryService;
        this.locationService = locationService;
    }

    public static final String DATA_CONVERSION_QUARTZ_TRIGGER = "dataConversionQuartzTrigger";
    public static final String DATA_CONVERSION_QUARTZ_JOB = "dataConversionQuartzJob";
    public static final String HALLEY_GROUP = "halleyGroup";

    /**
     * 初始化任务计划
     */
    @SuppressWarnings("unused")
    private void initQuartz() throws SchedulerException, IOException {
        JobDetail job = JobBuilder.newJob(DataConversionQuartzJob.class)
                .withIdentity(DATA_CONVERSION_QUARTZ_JOB, HALLEY_GROUP)
                .storeDurably().build();

        scheduler.addJob(job, false);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("exhibitionService", exhibitionService);
        jobDataMap.put("carService", carService);
        //jobDataMap.put("deviceService", deviceService);
        jobDataMap.put("locationService", locationService);
        jobDataMap.put("routeHistoryService", routeHistoryService);

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
                .withIdentity(DATA_CONVERSION_QUARTZ_TRIGGER)
                .withSchedule(
                        SimpleScheduleBuilder.repeatSecondlyForever(SysConfig.dataConversionCycle)
                ).usingJobData(jobDataMap)
                .forJob(DATA_CONVERSION_QUARTZ_JOB, HALLEY_GROUP).build();
        scheduler.scheduleJob(simpleTrigger);
    }

}
