package com.baojie.manage.base.resolver;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.baojie.manage.base.common.Form;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.enums.EnumGlobalExcInfo;
import com.baojie.manage.base.common.util.JsonHelper;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Maps;

public class LtcExceptionResolver implements HandlerExceptionResolver {

	private final Logger logger = Logger.getLogger(getClass());
	private final String APP = "/app/";
	private final String BACK = "/service/";

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		String url = request.getRequestURI();
		logger.error("resolveException:", e);
		if (url.contains(APP)) {
			Form form = new Form();
			if (e instanceof BizException) {
				BizException bizEx = (BizException) e;
				form.setMessage(bizEx.code(), bizEx.message());
			} else {
				form.setMessage(EnumGlobalExcInfo.GLOBAL_OPERATION_ERROR);
			}
			this.returnJsonException(response, form);
		}
		if (url.contains(BACK)) {
			Map<String, Object> map = Maps.newHashMap();
			map.put(Const.retCode, false);
			// 判断是否 Ajax 请求
			if ((request.getHeader("accept").indexOf("application/json") > -1
					|| (request.getHeader("X-Requested-With") != null
							&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				if (e instanceof BizException) {
					BizException bizEx = (BizException) e;
					map.put(Const.retMsg, bizEx.message());
				} else {
					map.put(Const.retMsg, EnumGlobalExcInfo.GLOBAL_OPERATION_ERROR.message());
					e.printStackTrace();
				}
				this.returnJsonException(response, map);
			} else {
				map.put("errmsg", e.getMessage());
				return new ModelAndView("/common/error", "error", map);
			}
		}
		return new ModelAndView();
	}

	private void returnJsonException(HttpServletResponse response, Object obj) {
		response.setStatus(HttpStatus.OK.value()); // 设置状态码
		response.setContentType(MediaType.APPLICATION_JSON.toString()); // 设置ContentType
																		// application/json
		response.setCharacterEncoding("UTF-8"); // 避免乱码
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		try {
			response.getWriter().write(JsonHelper.formatJson(obj));
		} catch (IOException e1) {
			logger.error("与客户端通讯异常:" + e1.getMessage(), e1);
			e1.printStackTrace();
		}
	}
}
