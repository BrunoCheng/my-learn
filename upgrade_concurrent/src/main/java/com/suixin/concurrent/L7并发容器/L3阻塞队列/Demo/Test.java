package com.suixin.concurrent.L7并发容器.L3阻塞队列.Demo;

import java.util.concurrent.DelayQueue;

/**
 * @Description
 * @Created by acheng
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        DelayQueue<ItemVo<Order>> queue = new DelayQueue<>();
        new Thread(new PutOrder(queue)).start();
        new Thread(new FetchOrder(queue)).start();

        //每隔500毫秒，打印个数字
        for(int i=1;i<15;i++){
            Thread.sleep(500);
            System.out.println(i*500);
        }
    }
}
