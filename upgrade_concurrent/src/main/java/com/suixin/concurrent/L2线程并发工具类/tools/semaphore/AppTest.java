package com.suixin.concurrent.L2线程并发工具类.tools.semaphore;

import java.sql.Connection;
import java.util.Random;
import com.suixin.commons.utils.SleepTools;

/**
 *类说明：测试数据库连接池，这里主要是测试DBPoolBySemaphore类的效果
 */
public class AppTest {

	//3，实例化DBPoolBySemaphore
	private static DBPoolBySemaphore dbPool = new DBPoolBySemaphore();
	
	//2.模拟业务线程
	private static class BusiThread extends Thread{
		@Override
		public void run() {
			//随机一个数，模拟每个连接耗时
			Random r = new Random();
			//记录当前系统时间
			long start = System.currentTimeMillis();
			try {
				//4.实例化之后先获取连接
				Connection connect = dbPool.takeConnect();
				//5.打印当前线程的获取连接时间，即主要4的耗时
				System.out.println("Thread_"+Thread.currentThread().getId()
						+"_获取数据库连接共耗时【"+(System.currentTimeMillis()-start)+"】ms.");
				//6.模拟业务耗时，线程持有连接查询数据
				SleepTools.sleepByMS(100 + r.nextInt(100));
				System.out.println("查询数据完成，归还连接！");
				//7.归还连接
				dbPool.returnConnect(connect);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public static void main(String[] args) {

        for (int i = 0; i < 50; i++) {
			//1.开启50个线程，测试DBPoolBySemaphore的连接情况
            Thread thread = new BusiThread();
            thread.start();
	}
	}
	
}
