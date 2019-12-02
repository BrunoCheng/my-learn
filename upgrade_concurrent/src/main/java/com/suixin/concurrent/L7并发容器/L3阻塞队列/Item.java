package com.suixin.concurrent.L7并发容器.L3阻塞队列;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Description 存放队列的元素
 * @Created by acheng
 */
//使用泛型的好处是为了后面的扩展
public class Item<T> implements Delayed {

    //到期时间长度,单位ms
    private long activeTime;
    //实际数据
    private T date;

    // 构造器
    //activeTime是个过期时间
    public Item(long activeTime, T date) {
        super();
        //当前时间加设计设计时长，单位纳秒
        this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime, TimeUnit.MILLISECONDS) + System.nanoTime();
        this.date = date;
    }

    //get方法
    public long getActiveTime() {
        return activeTime;
    }

    public T getDate() {
        return date;
    }

    //返回元素剩余时间
    @Override
    public long getDelay(TimeUnit unit) {
        //元素剩余时间
        Long d = unit.convert(this.activeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        return d;
    }


    //设置排序
    //按照剩余时间排序
    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.NANOSECONDS)-o.getDelay(TimeUnit.NANOSECONDS);
        return 0;
    }
}
