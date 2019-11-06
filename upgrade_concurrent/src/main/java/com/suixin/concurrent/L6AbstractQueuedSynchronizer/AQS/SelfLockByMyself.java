package com.suixin.concurrent.L6AbstractQueuedSynchronizer.AQS;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description 实现一个类似ReentrantLock的锁
 * @Created by acheng
 */
//1.ReentrantLock源码中不是直接的继承AQS而是继承Lock，通过内部类来继承AQS，这里我们也采用这种方式
public class SelfLockByMyself implements Lock, Serializable {

    //2.state 表示获取到锁 state=1 获取到了锁，state=0，表示这个锁当前没有线程拿到
    private static class Sync extends AbstractQueuedSynchronizer{

        //是否占用
        protected boolean isHeldExclusively() {
            return getState()==1;
        }

        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)) {
                //独占式
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int arg) {
            //state为0，表示还没有独占，释放会异常
            if(getState()==0) {
                throw new UnsupportedOperationException();
            }
            //去除当先线程独占
            setExclusiveOwnerThread(null);
            //状态改为0
            setState(0);

            /*
            独占锁释放只有拥有锁的才可以进入，只用setState既可以，而上面tryAcquire()方法是多个线程情况下，需要
            compareAndSetState的CAS保证原子性。
             */


            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sycn = new Sync();

    @Override
    public void lock() {
        sycn.acquire(1);

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sycn.acquireInterruptibly(1);

    }

    @Override
    public boolean tryLock() {
        return sycn.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sycn.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sycn.release(1);

    }

    @Override
    public Condition newCondition() {
        return sycn.newCondition();
    }

}
