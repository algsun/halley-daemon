package com.microwise.halley.common;

import com.google.common.base.Strings;
import com.google.common.io.Closeables;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * 系统配置
 *
 * @author li.jianfei
 * @date 2013-11-15
 */
public class SysConfig {

    private SysConfig() {
    }

    public static final Logger log = LoggerFactory.getLogger(SysConfig.class);

    private static SysConfig instance;

    // ==============================config.properties=========================
    /**
     * 短信信息状态
     * 0-关  1-开
     */
    public static Map<String, Integer> swhMsgState = new HashMap<String, Integer>();

    public static Map<String, Date> swhLastDate = new HashMap<String, Date>();

    /**
     * 行驶状态
     * 0-超范围  1-正常
     */
    public static Map<String, Integer> pathMsgState = new HashMap<String, Integer>();

    public static Map<String, Date> pathLastDate = new HashMap<String, Date>();

    public static Map<String, Integer> routeHistoryLastId = new HashMap<String, Integer>();

    /**
     * 数据转换周期
     */
    public static int dataConversionCycle;

    static {
        instance = getInstance();
        initConfig();
    }

    public static SysConfig getInstance() {
        if (instance == null) {
            instance = new SysConfig();
        }
        return instance;
    }

    /**
     * 加载系统配置信息
     */
    private static void initConfig() {
        log.info("加载配置信息");

        InputStream in = null;
        try {
            in = Resources.asByteSource(Resources.getResource("config.properties")).openStream();
            Properties prop = new Properties();
            prop.load(in);

            // 获取数据转换工作周期
            SysConfig.dataConversionCycle = Integer.parseInt(prop.getProperty("app.dataConversionCycle").trim());
        } catch (Exception e) {
            log.error("Load config.properties Error... ", e);
        } finally {
            try {
                Closeables.close(in, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 将数字转换为 boolean
     *
     * @param number 数字字符串（0/1）
     * @return boolean
     */
    private static boolean parseNumericBoolean(String number) {
        if (Strings.isNullOrEmpty(number)) {
            return false;
        }
        return number.trim().equals("1");
    }

}
