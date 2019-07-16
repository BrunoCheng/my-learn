package com.suixin.commons.usedClass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @Description TODO
 * @Date 2019/7/16
 * @Created by acheng
 */

public class FindDirsFilesByName extends RecursiveAction {

    //当前任务需要搜寻的目录
    private File filePath;

    private String fileName;

    //子目录由构造函数传递进来
    public FindDirsFilesByName(File filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    @Override
    protected void compute() {
        //invokeAll可以存放集合
        //定义一个list,存放新的子目录
        List<FindDirsFilesByName> subTasks = new ArrayList<>();

        //当我们从构造函数中拿到目录时,拿到path目录下的文件
        File[] files = filePath.listFiles();
        //判断当前目录有没有内容
        if (files != null) {
            //有文件
            for (File file : files) {
                //如果是目录
                if (file.isDirectory()) {
                    //添加到list中
                    subTasks.add(new FindDirsFilesByName(file, fileName));
                } else {
                    //不是文件夹,是文件，检查是否以查找后缀相同
                    if (file.getAbsolutePath().indexOf(fileName) != -1) {
                        //如果是,打印出来
                        System.out.println("文件：" + file.getAbsolutePath());
                    }
                }
            }
            //如果当前目录list拥有子目录
            if (!subTasks.isEmpty()) {
                //循环遍历list,一一提交到池中
                //invokeAll(subTasks)代表的是提交任务
                for (FindDirsFilesByName subTask : invokeAll(subTasks)) {
                    subTask.join();//提交任务,等待子任务执行完成
                }
            }
        }
    }
}

