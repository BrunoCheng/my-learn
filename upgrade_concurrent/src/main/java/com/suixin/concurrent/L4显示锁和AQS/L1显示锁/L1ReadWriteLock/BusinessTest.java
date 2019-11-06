package com.suixin.concurrent.L4显示锁和AQS.L1显示锁.L1ReadWriteLock;

import com.suixin.concurrent.L2线程并发工具类.L4Semaphore.SleepTools;

import java.util.Random;

/**
 * @Description 测试类:模拟50个线程，读写比例为10:1
 * @Created by acheng
 */
public class BusinessTest {
    static final int readWriteRatio = 10;//读写线程的比例
    static final int minthreadCount = 3;//最少线程数

    //读操作
    private static class GetThread implements Runnable{

        private GoodsService goodsService;
        public GetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            for(int i=0;i<100;i++){//操作100次
                goodsService.getNum();
            }
            System.out.println(Thread.currentThread().getName()+"读取商品数据耗时："
                    +(System.currentTimeMillis()-start)+"ms");

        }
    }

    //写操做
    private static class SetThread implements Runnable{

        private GoodsService goodsService;
        public SetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Random r = new Random();
            for(int i=0;i<10;i++){//操作10次
                SleepTools.ms(50);
                goodsService.setNum(r.nextInt(10));
            }
            System.out.println(Thread.currentThread().getName()
                    +"写商品数据耗时："+(System.currentTimeMillis()-start)+"ms---------");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GoodsInfo goodsInfo = new GoodsInfo("Cup",100000,10000);
        //GoodsService goodsService = new UseSyn(goodsInfo);
        GoodsService goodsService = new UseRwLock(goodsInfo);
        for(int i = 0;i<minthreadCount;i++){
            Thread setT = new Thread(new SetThread(goodsService));
            for(int j=0;j<readWriteRatio;j++) {
                Thread getT = new Thread(new GetThread(goodsService));
                getT.start();
            }
            SleepTools.ms(100);
            setT.start();
        }
    }
}
