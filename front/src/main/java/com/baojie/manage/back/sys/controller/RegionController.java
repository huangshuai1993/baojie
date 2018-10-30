package com.baojie.manage.back.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baojie.manage.back.sys.form.Region;
import com.baojie.manage.base.common.Response;
import com.baojie.manage.base.common.enums.EnumGlobalExcInfo;
import com.baojie.manage.base.common.util.SysDqHelper;
import com.baojie.manage.base.controller.BaseController;

@RequestMapping("/region")
@Controller
public class RegionController extends BaseController {
	@Resource(name = "redisTemplate")
	protected ValueOperations<String, List<Region>> valueOperations;

	/**
	 * 根据父类code获取地区
	 * 
	 * @return
	 * 
	 */
	@ResponseBody
	@RequestMapping("/regions")
	public Response<List<Region>> region(HttpServletRequest request, String region_id) {
		logger.info("Region [get]: region_id=" + region_id);
		Response<List<Region>> response = new Response<List<Region>>();
		// List<Region> regionList =
		// this.valueOperations.get(SysCacheKey.SYS_REGION);
		List<Region> regionList = SysDqHelper.lookDqByParentCode(region_id);
		if (regionList.isEmpty())
			response.setMessage(EnumGlobalExcInfo.GLOBAL_OPERATION_ERROR);
		// this.valueOperations.set(SysCacheKey.SYS_REGION, regionList,
		// Utils.DAY, TimeUnit.MILLISECONDS);
		response.setData(regionList);
		return response;
	}

}
