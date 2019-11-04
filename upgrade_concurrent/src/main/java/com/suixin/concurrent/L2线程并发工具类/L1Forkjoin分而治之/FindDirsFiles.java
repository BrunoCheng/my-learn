package com.suixin.concurrent.L2线程并发工具类.L1Forkjoin分而治之;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 类说明：遍历指定目录（含子目录）找寻指定类型文件
 * 由于不需要返回 结果继承RecursiveAction
 */
public class FindDirsFiles extends RecursiveAction {
    //当前任务需要搜寻的目录
    private File path;
    //子目录由构造函数传递进来
    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        //invokeAll可以存放集合
        //定义一个list,存放新的子目录
        List<FindDirsFiles> subTasks = new ArrayList<>();

        //当我们从构造函数中拿到目录时,拿到path目录下的文件
        File[] files = path.listFiles();
        //判断当前目录有没有内容
        if (files != null) {
            //有文件
            for (File file : files) {
                //如果是目录
                if (file.isDirectory()) {
                    //添加到list中
                    subTasks.add(new FindDirsFiles(file));
                } else {
                    //不是文件夹,是文件，检查是否以查找后缀相同
                    if (file.getAbsolutePath().endsWith("txt")) {
                        //如果是,打印出来
                        System.out.println("文件：" + file.getAbsolutePath());
                    }
                }
            }
            //如果当前目录list拥有子目录
            if (!subTasks.isEmpty()) {
                //循环遍历list,一一提交到池中
                //invokeAll(subTasks)代表的是提交任务
                for (FindDirsFiles subTask : invokeAll(subTasks)) {
                    subTask.join();//提交任务,等待子任务执行完成
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            // 用一个 ForkJoinPool 实例调度总任务
            ForkJoinPool pool = new ForkJoinPool();
            //声明一个总任务
            FindDirsFiles task = new FindDirsFiles(new File("D:/"));

            //异步调用,传入任务task
            pool.execute(task);

            System.out.println("Task is Running......");
            Thread.sleep(1);
            int otherWork = 0;
            //模拟主业务工作,观察异步工作情况
            for (int i = 0; i < 100; i++) {
                otherWork = otherWork + i;
            }
            System.out.println("Main Thread done sth......,otherWork=" + otherWork);
            task.join();//阻塞的方法,防止主线程过早的结束影响ForkJoinPool的task执行
            System.out.println("Task end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
