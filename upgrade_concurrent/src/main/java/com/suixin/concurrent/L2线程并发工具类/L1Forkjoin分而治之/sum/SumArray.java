package com.suixin.concurrent.L2线程并发工具类.L1Forkjoin分而治之.sum;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumArray {
    private static class SumTask extends RecursiveTask<Integer>{
        //Recursive英文意思为递归
        //设置切分大小，将MakeArray数据处理的长度分为10段。
        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH/10;
        //表示我们要实际统计的数组
        private int[] src;
        //开始统计的下标
        private int fromIndex;
        //统计到哪里结束的下标
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

		@Override
		protected Integer compute() {
            //当切分大小小于设置阀值时，表示分片完成
			if(toIndex-fromIndex < THRESHOLD) {
				int count = 0;
				for(int i=fromIndex;i<=toIndex;i++) {
			    	//SleepTools.ms(1);
			    	count = count + src[i];
				}
				return count;
			}else {
				//fromIndex....mid....toIndex
				//1...................70....100
				int mid = (fromIndex+toIndex)/2;
				SumTask left = new SumTask(src,fromIndex,mid);
				SumTask right = new SumTask(src,mid+1,toIndex);
				invokeAll(left,right);
				return left.join()+right.join();
			}
		}
    }


    public static void main(String[] args) {
        //1.创建ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.makeArray();

        //使用采用ForkJoin方法同步计算
        SumTask innerFind = new SumTask(src,0,src.length-1);

        long start = System.currentTimeMillis();

        //同步调用，主线必须等到invoke执行完成之后才可以
        pool.invoke(innerFind);
        System.out.println("Task is Running.....");

        System.out.println("The count is "+innerFind.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");

    }
}
