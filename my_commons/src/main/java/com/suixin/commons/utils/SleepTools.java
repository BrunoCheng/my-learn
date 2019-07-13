package com.suixin.commons.utils;

import java.util.concurrent.TimeUnit;

/**
 * @Description 线程休眠辅助工具类
 * @Date 2019/7/13
 * @Created by acheng
 */
public class SleepTools {
    /**
     * 按秒休眠
     * @param seconds 秒数
     */
    public static final void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }

    /**
     * 按毫秒数休眠
     * @param seconds 毫秒数
     */
    public static final void sleepByMS(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
