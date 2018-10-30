package com.baojie.manage.back.organize.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baojie.manage.back.organize.dto.StoreDto;
import com.baojie.manage.back.organize.dto.WorkerDto;
import com.baojie.manage.back.organize.form.ReginAndStoreForm;
import com.baojie.manage.back.organize.form.StoreForm;
import com.baojie.manage.back.organize.form.WorkerForm;
import com.baojie.manage.back.organize.service.OrganizeManageService;
import com.baojie.manage.back.sys.form.Region;
import com.baojie.manage.base.common.consts.Const;
import com.baojie.manage.base.common.util.DateTimeUtil;
import com.baojie.manage.base.common.util.PageResults;
import com.baojie.manage.base.common.util.PageUtil;
import com.baojie.manage.base.common.util.SysDqHelper;
import com.baojie.manage.base.controller.BaseController;
import com.baojie.manage.base.exception.BizException;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/organize")
public class OrganizeManageController extends BaseController{
	@Autowired
	private OrganizeManageService organizeManageService;
	@RequestMapping("/getWorkerList")
	public String getWorker(HttpServletRequest request,Model model, Integer pageNumber, Integer pageSize,
			String name,String mobile,Integer status) throws BizException{
		logger.info("worker [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize);
		if (pageNumber == null) {
            pageNumber = 1;
        } 
        if (pageSize == null) {
            pageSize = 10;
        }
        if(status==null){//状态:全部
        	status=10;
        }
        PageUtil pageUtil = new PageUtil(pageSize);   
        pageUtil.setPageIndex(pageNumber);
        //获取门店id
        Long storeId = (Long)request.getAttribute("storeId");
        //调用service
        PageResults<WorkerDto> workerPageList=organizeManageService.getWorkerPageList(storeId,name, mobile, status, pageNumber, pageSize);
        List<WorkerDto> list = workerPageList.getList();
        List<WorkerForm> formList=Lists.newArrayList();
        for (WorkerDto workerDto : list) {
        	formList.add(new WorkerForm(workerDto));
		}
        model.addAttribute("workerList", formList);
        pageUtil.setTotalCount((int) workerPageList.getTotalCount());
        model.addAttribute("page", pageUtil);
        //条件查询返回
        model.addAttribute("searchName", name);
        model.addAttribute("searchMobile", mobile);
        model.addAttribute("searchStatus", status);
        //判断登录人员权限   返回地区及门店列表
        Long employeeId = (Long)request.getAttribute("employeeId");
        List<ReginAndStoreForm> regionAndStore = organizeManageService.getRegionAndStore(employeeId);
        model.addAttribute("regionAndStore", regionAndStore);
		return "organize/getWorkerList";
	}
	@RequestMapping("/addWorker")
    @ResponseBody
    public Map<String, Object> addWorker(HttpServletRequest request,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "idcardNo") String idcardNo,
            @RequestParam(value = "birthday") String birthday,
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "empNo") String empNo,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "store_id") Long store_id,
            @RequestParam(value = "status") Integer status,
            @RequestParam(value = "gender") Integer gender) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(idcardNo) || StringUtils.isEmpty(birthday)
        		|| StringUtils.isEmpty(mobile) || StringUtils.isEmpty(empNo) 
        		|| store_id==null || status==null || gender==null) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "参数不能为空");
            return map;
        }
        //转化类型
        Date date = DateTimeUtil.StrToDate(birthday);
        WorkerDto worker = new WorkerDto();
        worker.setName(name);
        worker.setIdcardNo(idcardNo);
        worker.setBirthday(date);
        worker.setMobile(mobile);
        worker.setEmpNo(empNo);
        worker.setEmail(email);
        worker.setStoreId(store_id);
        worker.setStatus(status);
        worker.setGender(gender);
        organizeManageService.addWorker(worker);
        map.put(Const.retCode, Boolean.TRUE);
        map.put(Const.retMsg, "添加成功!");
        return map;
    }
	@RequestMapping("/updateWorker")
    @ResponseBody
    public Map<String, Object> updateWorker(HttpServletRequest request,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "idcardNo") String idcardNo,
            @RequestParam(value = "birthday") String birthday,
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "empNo") String empNo,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "store_id") Long store_id,
            @RequestParam(value = "status") Integer status,
            @RequestParam(value = "gender") Integer gender,Long id) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(idcardNo) || birthday==null
        		|| StringUtils.isEmpty(mobile) || StringUtils.isEmpty(empNo) 
        		|| store_id==null || status==null || gender==null) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "参数不能为空");
            return map;
        }
        if(id == null){
        	map.put(Const.retCode, false);
            map.put(Const.retMsg, "id参数不能为空");
            return map;
        }
        //转化类型
        Date date = DateTimeUtil.StrToDate(birthday);
        WorkerDto worker = new WorkerDto();
        worker.setId(id);
        worker.setName(name);
        worker.setIdcardNo(idcardNo);
        worker.setBirthday(date);
        worker.setMobile(mobile);
        worker.setEmpNo(empNo);
        worker.setEmail(email);
        worker.setStoreId(store_id);
        worker.setStatus(status);
        worker.setGender(gender);
        organizeManageService.updateWorker(worker);
        map.put(Const.retCode, true);
        map.put(Const.retMsg, "更新成功");
        return map;
    }
	/**
	 * 修改员工状态
	 * @param request
	 * @param status
	 * @param id
	 * @return
	 * @throws BizException
	 */
    @RequestMapping("/updateWorkerStatus")
    @ResponseBody
    public Map<String, Object> updateWorkerStatus(HttpServletRequest request,
            @RequestParam(value = "status") Integer status,
            @RequestParam(value = "id") Long id) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        if(id == null || status==null){
        	map.put(Const.retCode, false);
            map.put(Const.retMsg, "参数不能为空");
            return map;
        }
        organizeManageService.updateEnable(status, id);
        map.put(Const.retCode, true);
        map.put(Const.retMsg, "更新成功!");
        return map;
    }
    /**
     * 获取员工详细信息
     * @param id
     * @return
     * @throws BizException
     */
    @RequestMapping("/getWorkerInfo")
    @ResponseBody
    public Map<String, Object> getWorkerInfo(HttpServletRequest request,Long id) throws BizException{
    	Long employeeId = (Long)request.getAttribute("employeeId");
    	return organizeManageService.getWorkerInfo(id,employeeId);
    }
    @RequestMapping("/getStoreList")
    public String getStoreList(Model model, Integer pageNumber, Integer pageSize,
            String provinceCode, String name) throws BizException {
    	logger.info("Store [get]: pageNumber=" + pageNumber + ", pageSize=" + pageSize);
    	if (pageNumber == null) {
            pageNumber = 1;
        } 
        if (pageSize == null) {
            pageSize = 10;
        }
        PageUtil pageUtil = new PageUtil(pageSize);
        pageUtil.setPageIndex(pageNumber);
        PageResults<StoreDto> storePageList = organizeManageService.getStorePageList(provinceCode, name, pageNumber, pageSize);
        List<StoreDto> list = storePageList.getList();
        List<StoreForm> formList=Lists.newArrayList();
        for (StoreDto storeDto : list) {
			formList.add(new StoreForm(storeDto));
		}
        model.addAttribute("storeList", formList);
        pageUtil.setTotalCount((int) storePageList.getTotalCount());
        model.addAttribute("page", pageUtil);
        //获取省份区域信息
        String code=null;
        List<Region> lookDqByParentCode = SysDqHelper.lookDqByParentCode(code);
        model.addAttribute("regionList", lookDqByParentCode);
        //条件查询返回
        model.addAttribute("searchName", name);
        model.addAttribute("searchProvinceCode", provinceCode);
        return "organize/getStoreList";
    }
    
    @RequestMapping("/addStore")
    @ResponseBody
    public Map<String, Object> addStore(HttpServletRequest request,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "provinceCode") String provinceCode) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(provinceCode)) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "门店名称或省份不能为空");
            return map;
        }
        StoreDto store = new StoreDto();
        store.setName(name);
        store.setProvinceCode(provinceCode);
        organizeManageService.addStore(store);
        map.put(Const.retCode, Boolean.TRUE);
        map.put(Const.retMsg, "添加成功!");
        return map;
    }
    
    /**
    * 修改门店信息
    * @param request
    * @param name
    * @param provinceCode
    * @param id
    * @return
    * @throws BizException
    */
    @RequestMapping("/updateStore")
    @ResponseBody
    public Map<String, Object> updateStore(HttpServletRequest request,
    		@RequestParam(value = "name") String name,
            @RequestParam(value = "provinceCode") String provinceCode,
            Long id) throws BizException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(provinceCode)) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "门店名称或省份不能为空");
            return map;
        }
        if(id == null){
        	map.put(Const.retCode, false);
            map.put(Const.retMsg, "id参数不能为空");
            return map;
        }
        StoreDto store = new StoreDto();
        store.setId(id);
        store.setName(name);
        store.setProvinceCode(provinceCode);
        organizeManageService.updateStore(store);
        map.put(Const.retCode, true);
        map.put(Const.retMsg, "更新成功");
        return map;
        
    }
    
    /**
     * 获取门店详细信息
     * @param id
     * @return
     * @throws BizException
     */
    @RequestMapping("/getStoreInfo")
    @ResponseBody
    public Map<String, Object> getStoreInfo(Long id) throws BizException{
    	Map<String, Object> map = new HashMap<String, Object>();
    	StoreDto dto = organizeManageService.getStoreInfo(id);
    	map.put(Const.retCode, true);
        map.put("store",new StoreForm(dto));
        return map;
        
    }
    /**
     * 删除门店
     * @return
     * @throws BizException 
     */
    @RequestMapping("/deleteStore")
    @ResponseBody
    public Map<String, Object> deleteStore(Long id) throws BizException{
    	 Map<String, Object> map = new HashMap<String, Object>();
    	organizeManageService.deleteStore(id);
    	map.put("retCode", true);
		map.put(Const.retMsg, "删除成功");
		return map;
    }
    
    @ResponseBody
	@RequestMapping("/getWorkerByStoreId")
	public Map<String, Object> getWorkerByStoreId(HttpServletRequest request,
			Long storeId) throws BizException {
		logger.info("getWorkerByStoreId [get]: storeId=" + storeId);
		 Map<String, Object> map = new HashMap<String, Object>();
		if(storeId == null){
			map.put(Const.retCode, false);
            map.put(Const.retMsg, "storeId参数不能为空");
		}
		List<WorkerDto> dtoList = organizeManageService.getWorkerListByStoreId(storeId);
		
		List<WorkerForm> formList = Lists.newArrayList();
		for (WorkerDto workerDto : dtoList) {
			formList.add(new WorkerForm(workerDto));
		}
		map.put("workerList", formList);
		return map;
	}
    
}
