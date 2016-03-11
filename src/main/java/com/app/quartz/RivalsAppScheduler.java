package com.app.quartz;

import java.util.Date;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.core.jmx.JobDetailSupport;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

public class RivalsAppScheduler implements ApplicationContextAware {

	private static final String TARGET_BEAN_NAME_KEY = "targetBean";
	private static final String METHOD_NAME_KEY = "method";
	private static final String ARGUMENTS_KEY = "arguments";

	@Autowired
	private Scheduler scheduler;

	private ApplicationContext applicationContext;

	private SimpleTrigger buildExactTimeTrigger(final String jobName,
			final String group, final Date when) {
		SimpleTriggerImpl trigger = new SimpleTriggerImpl();// new
															// SimpleTrigger(jobName,
															// group, when);
		trigger.setJobName(jobName);
		trigger.setJobGroup(group);
		trigger.setStartTime(when);
		trigger.setEndTime(null);
		trigger.setRepeatCount(0);
		trigger.setRepeatInterval(0);
		return trigger;
	}

	public void scheduleInvocation(final String jobName, final String group,
			final Date when, final InvocationDetail invocationDetail) {
		schedule(createDynamicJobDetail(invocationDetail, jobName, group),
				buildExactTimeTrigger(jobName, group, when));
	}

	private JobDetail createDynamicJobDetail(
			final InvocationDetail invocationDetail, final String jobName,
			final String group) {
		JobDetail detail = JobBuilder.newJob().withIdentity(jobName, group)
				.ofType(MethodInvocatingScheduledJob.class).build();// new
																	// JobDetail(jobName,
																	// group,
																	// MethodInvocatingScheduledJob.class);
		setJobArguments(invocationDetail, detail);
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

	private void setJobArguments(final InvocationDetail invocationDetail,
			final JobDetail detail) {
		detail.getJobDataMap().put(TARGET_BEAN_NAME_KEY,
				invocationDetail.getTargetBeanName());
		detail.getJobDataMap().put(METHOD_NAME_KEY,
				invocationDetail.getTargetMethod());
		detail.getJobDataMap().put(ARGUMENTS_KEY,
				invocationDetail.getMethodArgs());
	}

	public static class InvocationDetail {
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
	}
}
