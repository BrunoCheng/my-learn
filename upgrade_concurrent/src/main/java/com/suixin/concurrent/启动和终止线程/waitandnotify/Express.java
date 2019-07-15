package com.suixin.concurrent.启动和终止线程.waitandnotify;

/**
 * @Description 快递员实体类
 * @Date 2019/7/15
 * @Created by acheng
 */
public class Express {
    //默认城市为上海
    public final static String CITY = "ShangHai";
    //快递运输里程数
    private int km;
    //快递到达地点
    private String site;

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public synchronized void changeKm(){
        this.km = 101;
        notifyAll();
        //其他的业务代码

    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public synchronized void changeSite(){
        this.site = "BeiJing";
        notifyAll();
    }

    public synchronized void waitKm(){
        while(this.km<=100) {
            try {
                wait();
                System.out.println("check km thread["+Thread.currentThread().getId()
                        +"] is be notifed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the km is"+this.km+",I will change db.");

    }

    public synchronized void waitSite(){
        while(CITY.equals(this.site)) {
            try {
                wait();
                System.out.println("check site thread["+Thread.currentThread().getId()
                        +"] is be notifed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the site is"+this.site+",I will call user.");
    }
}
