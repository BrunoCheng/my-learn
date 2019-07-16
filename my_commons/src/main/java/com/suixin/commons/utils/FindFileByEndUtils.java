package com.suixin.commons.utils;

import com.suixin.commons.usedClass.FindDirsFilesByEnd;
import com.suixin.commons.usedClass.FindDirsFilesByName;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

/**
 * @Description 通过文件后缀查找目录下所有的文件
 * @Date 2019/7/16
 * @Created by acheng
 */
public class FindFileByEndUtils {
    /**
     * 根据查找目录下的所有文件
     * @param filePath 查找路径
     * @param endWith   查找后缀
     */
    public static void findDirsFilesByEnd(String filePath,String endWith){
            // 用一个 ForkJoinPool 实例调度总任务
            ForkJoinPool pool = new ForkJoinPool();
            //声明一个总任务
            FindDirsFilesByEnd task = new FindDirsFilesByEnd(new File(filePath),endWith);

            //异步调用,传入任务task
            pool.execute(task);
            System.out.println("Task is Running......");
            task.join();//阻塞的方法
            System.out.println("Task is end......");
    }

    /**
     * 根据名字查找目录下所有文件
     * @param filePath 查找路径
     * @param name 查找文件名
     */
    public static void findDirsFilesByName(String filePath,String name){
        // 用一个 ForkJoinPool 实例调度总任务
        ForkJoinPool pool = new ForkJoinPool();
        //声明一个总任务
        FindDirsFilesByName task = new FindDirsFilesByName(new File(filePath),name);

        //异步调用,传入任务task
        pool.execute(task);
        System.out.println("Task is Running......");
        task.join();//阻塞的方法
        System.out.println("Task is end......");
    }
}
