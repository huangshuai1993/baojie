package com.baojie.manage.back.task.service.convertor;

import com.baojie.manage.back.task.dto.BTaskDto;
import com.baojie.manage.base.dao.entity.TaskEntity;
import com.baojie.manage.base.service.convertor.AbstractConvertor;

public class BTaskConvertor extends AbstractConvertor<BTaskDto, TaskEntity>{

	@Override
	public TaskEntity dto2Entity(BTaskDto dto){
		if (dto == null) {
            return null;
        }
		TaskEntity e = new TaskEntity();
        e.setId(dto.getId());
        e.setName(dto.getName());
        e.setGender(dto.getGender());
        e.setIdcardNo(dto.getIdcardNo());
        e.setBirthday(dto.getBirthday());
        e.setMobile(dto.getMobile());
        e.setMarriage(dto.getMarriage());
        e.setIncome(dto.getIncome());
        e.setHasCautioner(dto.getHasCautioner());
        e.setCautionerName(dto.getCautionerName());
        e.setCautionerIdcardNo(dto.getCautionerIdcardNo());
        e.setCautionerRelation(dto.getCautionerRelation());
        e.setHomeProvinceCode(dto.getHomeProvinceCode());
        e.setHomeCityCode(dto.getHomeCityCode());
        e.setHomeRegionCode(dto.getHomeRegionCode());
        e.setHomeAddress(dto.getHomeAddress());
        e.setWorkProvinceCode(dto.getWorkProvinceCode());
        e.setWorkCityCode(dto.getWorkCityCode());
        e.setWorkRegionCode(dto.getWorkRegionCode());
        e.setWorkAddress(dto.getWorkAddress());
        e.setProduct(dto.getProduct());
        e.setAmount(dto.getAmount());
        e.setSource(dto.getSource());
        e.setStoreId(dto.getStoreId());
        e.setEmpId(dto.getEmpId());
        e.setMemo(dto.getMemo());
        e.setTaskStatus(dto.getTaskStatus());
        e.setCreated(dto.getCreated());
        e.setUpdated(dto.getUpdated());
        e.setStatus(dto.getStatus());
        e.setReportText(dto.getReportText());
        return e;
	}
    @Override
    public BTaskDto entity2Dto(TaskEntity e){
        if (e == null) {
            return null;
        }
        BTaskDto dto = new BTaskDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setGender(e.getGender());
        dto.setIdcardNo(e.getIdcardNo());
        dto.setBirthday(e.getBirthday());
        dto.setMobile(e.getMobile());
        dto.setMarriage(e.getMarriage());
        dto.setIncome(e.getIncome());
        dto.setHasCautioner(e.getHasCautioner());
        dto.setCautionerName(e.getCautionerName());
        dto.setCautionerIdcardNo(e.getCautionerIdcardNo());
        dto.setCautionerRelation(e.getCautionerRelation());
        dto.setHomeProvinceCode(e.getHomeProvinceCode());
        dto.setHomeCityCode(e.getHomeCityCode());
        dto.setHomeRegionCode(e.getHomeRegionCode());
        dto.setHomeAddress(e.getHomeAddress());
        dto.setWorkProvinceCode(e.getWorkProvinceCode());
        dto.setWorkCityCode(e.getWorkCityCode());
        dto.setWorkRegionCode(e.getWorkRegionCode());
        dto.setWorkAddress(e.getWorkAddress());
        dto.setProduct(e.getProduct());
        dto.setAmount(e.getAmount());
        dto.setSource(e.getSource());
        dto.setEmpId(e.getEmpId());
        dto.setStoreId(e.getStoreId());
        dto.setMemo(e.getMemo());
        dto.setTaskStatus(e.getTaskStatus());
        dto.setCreated(e.getCreated());
        dto.setUpdated(e.getUpdated());
        dto.setStatus(e.getStatus());
        dto.setReportText(e.getReportText());
        return dto;
    }
}
