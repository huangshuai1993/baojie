package com.baojie.manage.base.service;

import org.apache.log4j.Logger;

import com.baojie.manage.base.common.util.MonitorLogger;

public abstract class BaseService {

	protected final Logger logger = Logger.getLogger(getClass());

	protected final MonitorLogger monitorLog = MonitorLogger.getInstance();
}
