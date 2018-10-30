package com.baojie.manage.base.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baojie.manage.base.common.util.GetUniqueNoUtil;



public abstract class BaseController {

	private final static String COOKIE_DOMAIN = "51baojie.com";

	protected final Logger logger = Logger.getLogger(getClass());


	protected HttpServletRequest getHttpRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/*protected Long getCurrentUserId() {
		 String _id = PlatformThreadLocal.getInstance().getViewId();
		 Long userId = NumberUtils.toLong(_id);
		 return userId > 0L ? userId : null;
	}*/
	
	protected Long getUserId(){
        HttpServletRequest request = getHttpRequest();
        Long userId = (Long)request.getAttribute("userId");
        return userId;
    }

	protected Long getEmpId(){
	    HttpServletRequest request = getHttpRequest();
	    Long employeeId = (Long)request.getAttribute("employeeId");
        return employeeId;
	}
	protected String getCurrentUserName() {
		return null;
	}

	protected String getCookieVal(String name) {
		String _val = "";
		Cookie[] cookies = getHttpRequest().getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(name)) {
					_val = cookies[i].getValue();
					break;
				}
			}
		}
		return _val;
	}

	protected void addCookie(String name, String value, int maxAge, String path, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		cookie.setDomain(COOKIE_DOMAIN);
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	protected void delCookie(String name, String path, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, "0101");
		cookie.setPath(path);
		cookie.setDomain(COOKIE_DOMAIN);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/*protected void writeMessageUtf8(HttpServletResponse response, Map<String, Object> json) throws IOException {
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(JsonUtil.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.getWriter().close();
		}
	}*/

	protected String getRemoteIpAddress() {
		HttpServletRequest request = getHttpRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		} else {
			if (ip.indexOf(",") != -1) {
				String[] address = ip.split(",");
				ip = address[0];
				for (int i = 0; i < address.length; i++) {
					if (!"unknown".equalsIgnoreCase(address[i].trim())) {
						ip = address[i].trim();
						break;
					}
				}
			}
		}
		return ip;
	}

	protected void responsePdf(byte[] data, HttpServletResponse response) {
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment;filename=audit_"+GetUniqueNoUtil.getRandomCode(null)+".pdf");
		InputStream is = null;
		OutputStream os = null;
		try {
			byte buffer[] = new byte[1024];
			is = new ByteArrayInputStream(data);
			os = response.getOutputStream();
			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				os.write(buffer, 0, len);
			}
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}
}
