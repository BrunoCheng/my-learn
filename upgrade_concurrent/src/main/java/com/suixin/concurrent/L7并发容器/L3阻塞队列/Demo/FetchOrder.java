package com.suixin.concurrent.L7并发容器.L3阻塞队列.Demo;

import java.util.concurrent.DelayQueue;

/**
 * @Description 取出到期订单元素于队列
 * @Created by acheng
 */
public class FetchOrder implements Runnable {

    private DelayQueue<ItemVo<Order>> queue;

    public FetchOrder(DelayQueue<ItemVo<Order>> queue) {
        super();
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                ItemVo<Order> item = queue.take();
                Order order = (Order)item.getDate();
                System.out.println("get from queue:"+order.getOrderNo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
