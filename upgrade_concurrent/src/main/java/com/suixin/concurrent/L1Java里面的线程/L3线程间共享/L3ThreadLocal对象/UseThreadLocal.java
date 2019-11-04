package com.suixin.concurrent.L1Java里面的线程.L3线程间共享.L3ThreadLocal对象;

/**
 * @Description 演示ThreadLocal的使用
 * @Auther:acheng
 */
public class UseThreadLocal {
    //static对于一个类不管new多少都是一份拷贝
    //可以理解为 一个map，类型 Map<Thread,Integer>
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            //每个线程初始值为1
            return 1;
        }
    };

    /**
     * 运行3个线程
     */
    public void StartThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            //每个线程初始名不同
            runs[i] = new Thread(new TestThread(i));
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();
        }
    }

    /**
     * 类说明：测试线程，线程的工作是将ThreadLocal变量的值变化，并写回，看看线程之间是否会互相影响
     */
    public static class TestThread implements Runnable {
        int id;

        public TestThread(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            Integer s = threadLocal.get();//获得变量的值
            s = s + id;//进行一个简单的计算
            threadLocal.set(s);//设置回去变量的值
            System.out.println(Thread.currentThread().getName() + ":"
                    + threadLocal.get());


            //threadLaocl.remove();
        }
    }

    public static void main(String[] args) {
        UseThreadLocal test = new UseThreadLocal();
        test.StartThreadArray();
        /**
         * Thread-1:start
         * Thread-0:start
         * Thread-2:start
         * Thread-1:2 S=1
         * Thread-0:1 S=1
         * Thread-2:3 S=1
         * 测试代码中分别取出来S并设置回去,但是三个线程运行都是使用的S=1时状态,也就说三个线程变量不共享
         */
    }
}
