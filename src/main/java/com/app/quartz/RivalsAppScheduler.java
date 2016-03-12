package com.app.quartz;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("rivalsAppScheduler")
public class RivalsAppScheduler implements ApplicationContextAware {

	private static final String TARGET_BEAN_NAME_KEY = "targetBean";
	private static final String METHOD_NAME_KEY = "method";
	private static final String ARGUMENTS_KEY = "arguments";

	@Autowired
	private Scheduler scheduler;

	private ApplicationContext applicationContext;

	private Trigger buildExactTimeTrigger(final String jobName,
			final String group, final Date when) {
		Trigger trigger = (Trigger) TriggerBuilder.newTrigger().startAt(when).endAt(null).forJob(jobName, group).build();												// group, when);
		return trigger;
	}

	public void scheduleInvocation(final String jobName, final String group,
			final Date when, final Job job) {
		schedule(createDynamicJobDetail(job, jobName, group),
				buildExactTimeTrigger(jobName, group, when));
	}

	private JobDetail createDynamicJobDetail(
			Job job, final String jobName,
			final String group) {
		JobDetail detail = JobBuilder.newJob().withIdentity(jobName, group)
				.ofType(RivalScheduledJob.class).build();// new JobDetail(jobName,// group,
		setJobToAutoDelete(detail);
		return detail;
	}

	private void setJobToAutoDelete(final JobDetail detail) {
		// JobDetailImpl jimpl = (JobDetailImpl) detail;
		detail.getJobBuilder().storeDurably(false);
		// jimpl.setDurability(false);
	}

	@Override
	public void setApplicationContext(ApplicationContext ctxt)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void schedule(final JobDetail job, final Trigger trigger) {
		if (isJobExists(job)) {
			rescheduleJob(job, trigger);
		} else {
			doScheduleJob(job, trigger);
		}
	}

	private void rescheduleJob(final JobDetail job, final Trigger trigger) {
		try {
			TriggerKey trigKey = trigger.getKey();
			this.scheduler.rescheduleJob(trigKey, trigger);
		} catch (SchedulerException e) {
			throw new IllegalStateException("Failed to reschedule the Job.", e);
		}
	}

	public boolean isJobExists(final JobDetail job) {
		try {
			JobKey jobKey = job.getKey();
			return this.scheduler.getJobDetail(jobKey) != null;
		} catch (SchedulerException e) {
			throw new IllegalStateException("Failed to find Job.", e);
		}
	}

	private void doScheduleJob(final JobDetail job, final Trigger trigger) {
		try {
			this.scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			throw new IllegalStateException("Failed to schedule the Job.", e);
		}
	}

	/*public static class InvocationDetail {
		private String targetBeanName;
		private String targetMethod;
		private List<?> methodArgs;

		public InvocationDetail(final String newTargetBean,
				final String newTargetMethod, final List<?> newMethodArgs) {
			this.targetBeanName = newTargetBean;
			this.targetMethod = newTargetMethod;
			this.methodArgs = newMethodArgs;
		}

		public String getTargetBeanName() {
			return targetBeanName;
		}

		public String getTargetMethod() {
			return targetMethod;
		}

		public Object[] getMethodArgs() {
			return methodArgs.toArray();
		}
	}*/
}
