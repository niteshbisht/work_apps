package com.app.quartz;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.app.rival.challengebean.RivalBean;
public class RivalScheduledJob extends QuartzJobBean{

	private RivalBean rivalBean;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		this.rivalBean.execute();
	}

	public void setRivalBean(RivalBean rivalBean) {
		this.rivalBean = rivalBean;
	}

}
