package com.job.abdullah;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.job.abdullah.jobs.SomeDailyJob;

@WebListener
public class BackgroundJobManager implements ServletContextListener {

	private ScheduledExecutorService scheduler;
	
	
	
	public void contextDestroyed(ServletContextEvent arg0) {
		scheduler.shutdownNow();
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new SomeDailyJob(), 5, 2, TimeUnit.SECONDS);
        /*scheduler.scheduleAtFixedRate(new SomeHourlyJob(), 0, 1, TimeUnit.HOURS);
        scheduler.scheduleAtFixedRate(new SomeQuarterlyJob(), 0, 15, TimeUnit.MINUTES)*/
        
		
	}

	
}
