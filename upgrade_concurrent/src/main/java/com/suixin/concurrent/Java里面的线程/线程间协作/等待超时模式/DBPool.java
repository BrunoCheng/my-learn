package com.suixin.concurrent.Java里面的线程.线程间协作.等待超时模式;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Description 实现一个数据库的连接池
 * @Date 2019/7/15
 * @Created by acheng
 */
public class DBPool {

    //数据库池的容器,使用LinkedList存放连接
    private static LinkedList<Connection> pool = new LinkedList<>();

    //定义一个构造器,初始化一定数量的连接
    public DBPool(int initalSize) {
        if (initalSize > 0) {
            for (int i = 0; i < initalSize; i++) {
                //根据linked结构,从后面添加连接
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }
    //数据库连接池用完了,新线程需要连接时不会一直等待着,应该设置一个等待时间
    //如果在mills时间内还拿不到数据库连接，返回一个null.即超时没有取到
    public Connection fetchConn(long mills) throws InterruptedException {
        synchronized (pool) {
            //如果等待时间小于0,代表数据库连接池永远不超时
            if (mills < 0) {
                //无限期的等待,知道wait()被通知pool有了
                while (pool.isEmpty()) {
                    pool.wait();
                }
                //取出此时pool有了的连接
                return pool.removeFirst();
            } else {
                //设置超时时间点
                long overtime = System.currentTimeMillis() + mills;
                //设置超时时间
                long remain = mills;
                //如果线程池为空并且还有等待的时间
                while (pool.isEmpty() && remain > 0) {
                    //设置超时等待时间
                    pool.wait();
                    remain = overtime - System.currentTimeMillis();
                }
                //假定连接为null
                Connection result = null;
                //如果连接池有了连接则返回连接,此时超时时间还是没有连接就返回null
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

    //放回数据库连接 并唤醒等待的线程
    public void releaseConn(Connection conn) {
        if (conn != null) {
            synchronized (pool) {
                pool.addLast(conn);
                pool.notifyAll();
            }
        }
    }
}

