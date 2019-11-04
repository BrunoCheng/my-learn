package com.suixin.concurrent.L1Java里面的线程;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Description:验证main方法是否是单线程
 * @Auther:acheng
 */
public class OnlyMain {
    public static void main(String[] args) {
        //ThreadMXBean虚拟机提供的线程管理接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + " "
                    + threadInfo.getThreadName());
        }
        /**
         * [6] Monitor Ctrl-Break
         * [5] Attach Listener 获取当前程序运行的相关信息的线程,如内存栈,系统属性等
         * [4] Signal Dispatcher 信号分发器线程,分发处理发送给虚拟机信号的线程
         * [3] Finalizer 调用对象Object的finalizer方法的线程
         * [2] Reference Handler 负责清除引用的线程
         * [1] main  main方法的线程
         *
         * 为啥没有GC线程?
         * 因为当前程序非常的短,jvm觉得没有必要用gc
         */
    }
}
