package com.app.quartz;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;
public class RivalScheduledJob extends QuartzJobBean{
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		JobDetail jobDetail = arg0.getJobDetail();
		JobKey key = jobDetail.getKey();
		String challengeId = key.getName();
		RivalAppSchedulerUtil.computeStatsForChallenge(Long.parseLong(challengeId));
	}
}
