package com.baojie.manage.back.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import com.baojie.manage.back.sys.dto.PowerDto;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.CookiesUtil;
import com.baojie.manage.base.common.util.PropertiesLoader;
import com.google.common.collect.Maps;

@Service("powerFilter")
public class PowerFilter implements Filter {

	@Resource(name = "redisTemplate")
	protected ValueOperations<String, Object> redis;
	@Resource(name = "redisTemplate")
	protected ValueOperations<String, List<Index_MenuForm>> Index_MenuCache;

	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	private static PropertiesLoader loader = new PropertiesLoader("powerUrl.properties");
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();

	public void init(FilterConfig filterConfig) throws ServletException {
		Properties pro = loader.getProperties();
		Enumeration en = pro.propertyNames(); // 得到配置文件的名字
		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = pro.getProperty(strKey);
			map.put(strKey, strValue);
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String LOGOUT_URL = httpRequest.getContextPath() + "/service/login/logout";

		String url = urlPathHelper.getLookupPathForRequest(httpRequest);
		String token = CookiesUtil.getCookies(httpRequest, Const.TOKEN);
		String mainPowerCookie = CookiesUtil.getCookies(httpRequest, Const.mainPowerCookie);
		Long mainPowerCookieId = null;
		try {
			mainPowerCookieId = Long.valueOf(mainPowerCookie);
		} catch (Exception e) {
			mainPowerCookieId = 1L;
		}
		// Index_MenuCache.getOperations().delete(Const.MENU+token);
		List<Index_MenuForm> result = Index_MenuCache.get(Const.MENU + token);
		List<PowerDto> resultTwo = new ArrayList<PowerDto>();
		if (result != null && result.size() > 0) {
			for (Index_MenuForm form : result) {
				if (form.getPowerId().equals(mainPowerCookieId)) {
					for (PowerDto dtoTwo : form.getSubMenuName()) {
						resultTwo.add(dtoTwo);
					}
					break;
				}
			}
		}
		String powerName = map.get(url);// 得到权限名称
		if (StringUtils.isNotBlank(powerName)) {
			boolean flag = getPower(powerName, result);
			if (!flag) {
				httpResponse.sendRedirect(LOGOUT_URL);
				return;
			}
		}
		request.setAttribute("resultMenu", result);
		request.setAttribute("resultMenuTwo", resultTwo);
		chain.doFilter(request, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	private boolean getPower(String powerName, List<Index_MenuForm> result) {
		boolean flag = false;
		if (result != null && result.size() > 0) {
			for (Index_MenuForm index : result) {
				List<PowerDto> d = index.getSubMenuName();
				for (PowerDto dto : d) {
					if (dto.getPowerName().equals(powerName)) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

}
