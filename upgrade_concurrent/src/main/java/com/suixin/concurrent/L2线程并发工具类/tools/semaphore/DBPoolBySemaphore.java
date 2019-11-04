package com.suixin.concurrent.L2线程并发工具类.tools.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 *类说明：通过Semaphore实现一个数据库的连接。测试类见AppTest
 */
public class DBPoolBySemaphore {

	//1.定义了一个最大连接数量
	private final static int POOL_SIZE = 10;

	//2.定义Semaphore的参数，useful表示可用的数据库连接（即还有需要的连接的数量），useless表示已用的数据库连接，两者的和为定义的最大的连接数
	private final Semaphore useful,useless;

	//3.定义Semaphore的构造器
	public DBPoolBySemaphore() {
		//初始时最大连接数为设置值
		this.useful = new Semaphore(POOL_SIZE);
		//初始时已用连接数为0
		this.useless = new Semaphore(0);
	}
	
	//4.定义一个存放数据库连接的容器
	private static LinkedList<Connection> pool = new LinkedList<Connection>();



	//初始化池
	static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
	}

	//6.归还连接
	public void returnConnect(Connection connection) throws InterruptedException {
		if(connection!=null) {
			System.out.println("当前有"+useful.getQueueLength()+"个线程等待数据库连接！！"
					+"可用连接数:"+useful.availablePermits());
			//已用的获取许可
			useless.acquire();
			synchronized (pool) {
				//集合后加一
				pool.addLast(connection);
			}
			//可用释放，表示可用加1
			useful.release();
		}
	}
	

	//5.从池子获取连接
	public Connection takeConnect() throws InterruptedException {
		//useful拿到许可证
		useful.acquire();
		//先定义一个空的connection，用来接收获取到的连接
		Connection conn;
		//获
		synchronized (pool) {
			//获取集合第一个
			conn = pool.removeFirst();
		}
		//useless释放，表示已用一个
		useless.release();
		return conn;
	}
	
}
