package com.app.quartz;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.app.challenge.fbutil.FacebookClientHandler;
@Component("rivalScheduledJob")
public class RivalScheduledJob extends QuartzJobBean{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private FacebookClientHandler fbClientHandler;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		JobDetail jobDetail = arg0.getJobDetail();
		JobKey key = jobDetail.getKey();
		String challengeId = key.getName();
		RivalAppSchedulerUtil.computeStatsForChallenge(Long.parseLong(challengeId),namedParameterJdbcTemplate,fbClientHandler);
	}

}
