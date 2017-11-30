package com.job.abdullah.jobs;

import java.util.Date;

public class SomeDailyJob implements Runnable{

	private static int counter = 0;
	public void run() {
		System.out.println("Daily Job Started");
		method1();
		System.out.println("Daily Job Ended");
		
		
	}
	
	public void method1()
	{
		System.out.println(new Date());
		counter++;
		System.out.println(counter);
	}
	

}
