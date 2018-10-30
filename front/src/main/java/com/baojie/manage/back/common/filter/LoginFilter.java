package com.baojie.manage.back.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UrlPathHelper;

import com.baojie.manage.back.login.form.Index_MenuForm;
import com.baojie.manage.back.sys.dto.EmployeeDto;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.CookiesUtil;

@Service("loginFilter")
public class LoginFilter implements Filter {

	@Resource(name = "redisTemplate")
	protected ValueOperations<String, Object> redis;
	@Resource(name = "redisTemplate")
	protected ValueOperations<String, List<Index_MenuForm>> Index_MenuCache;

	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	private static List<String> excludeUri = new ArrayList<String>();

	static {
		excludeUri.add("/login/toLogin");
		excludeUri.add("/login/logins");
		excludeUri.add("/login/logout");
		excludeUri.add("/example/get");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String LOGIN_URL = httpRequest.getContextPath() + "/service/login/toLogin";
		String url = urlPathHelper.getLookupPathForRequest(httpRequest);

		if (!excludeUri.contains(url)) {
			String token = CookiesUtil.getCookies(httpRequest, Const.TOKEN);
			if (StringUtils.isBlank(token)) {
				httpResponse.sendRedirect(LOGIN_URL);
				return;
			}
			EmployeeDto dto = (EmployeeDto) redis.get(token);
			if (dto == null) {
				httpResponse.sendRedirect(LOGIN_URL);
				return;
			}
			request.setAttribute("userName", dto.getUsername());
			request.setAttribute("employeeId", dto.getEmployeeId());
			request.setAttribute("storeId", dto.getStoreId());
			// PlatformThreadLocal.getInstance().setViewId(dto.getEmployeeId().toString());
		}
		// String ip = SystemUtil.getRemoteIp(httpRequest);
		// PlatformThreadLocal.getInstance().setVisitIp(ip);
		chain.doFilter(request, response);
		// PlatformThreadLocal.getInstance().remove();
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
