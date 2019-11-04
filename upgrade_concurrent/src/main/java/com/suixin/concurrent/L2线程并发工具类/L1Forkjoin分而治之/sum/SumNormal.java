package com.suixin.concurrent.L2线程并发工具类.L1Forkjoin分而治之.sum;

/**
 * 模拟单线程来计算
 */

public class SumNormal {
	
	public static void main(String[] args) {
	    Long count = 0L;
	    int[] src = MakeArray.makeArray();
	    //开始计时
	    long start = System.currentTimeMillis();
	    for(int i= 0;i<src.length;i++){
	    	//休眠模拟平时业务消耗时间
	    	//SleepTools.sleepByMS(1);
	    	count = count + src[i];
	    }
	    System.out.println("The count is "+count
	            +" spend time:"+(System.currentTimeMillis()-start)+"ms");		
	}
}
