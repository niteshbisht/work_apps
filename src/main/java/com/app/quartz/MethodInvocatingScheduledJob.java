package com.app.quartz;

import java.lang.reflect.InvocationTargetException;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.beans.support.ArgumentConvertingMethodInvoker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.MethodInvoker;

public class MethodInvocatingScheduledJob implements Job,
		ApplicationContextAware {

	private ApplicationContext applicationContext;
	private static final String TARGET_BEAN_NAME_KEY = "targetBean";
	private static final String METHOD_NAME_KEY = "method";
	private static final String ARGUMENTS_KEY = "arguments";

	@Override
	public void execute(final JobExecutionContext context)
			throws JobExecutionException {
		try {
			JobDataMap data = jobData(context);
			invokeMethod(targetBean(context, data), method(data),
					arguments(data));
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

	private JobDataMap jobData(final JobExecutionContext context) {
		return context.getJobDetail().getJobDataMap();
	}

	private Object targetBean(JobExecutionContext context, JobDataMap data)
			throws Exception {
		return applicationContext.getBean(data.getString(TARGET_BEAN_NAME_KEY));
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext = arg0;
	}

	private String method(final JobDataMap data) {
		return data.getString(METHOD_NAME_KEY);
	}

	private Object[] arguments(final JobDataMap data) {
		return (Object[]) data.get(ARGUMENTS_KEY);
	}

	private void invokeMethod(final Object target, final String method,
			final Object[] args) throws ClassNotFoundException,
			NoSuchMethodException, InvocationTargetException,
			IllegalAccessException {
		MethodInvoker inv = new ArgumentConvertingMethodInvoker();

		inv.setTargetObject(target);
		inv.setTargetMethod(method);
		inv.setArguments(args);
		inv.prepare();
		inv.invoke();
	}
}
