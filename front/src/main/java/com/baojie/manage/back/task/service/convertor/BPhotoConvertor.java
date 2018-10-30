package com.baojie.manage.back.task.service.convertor;

import org.springframework.stereotype.Repository;

import com.baojie.manage.back.task.dto.BPhotoDto;
import com.baojie.manage.base.dao.entity.PhotoEntity;
import com.baojie.manage.base.service.convertor.AbstractConvertor;
@Repository("bphotoConvertor")
public class BPhotoConvertor extends AbstractConvertor<BPhotoDto, PhotoEntity>{

    @Override
    public BPhotoDto entity2Dto(PhotoEntity e){
        if (e == null){
            return null;
        }
        BPhotoDto dto = new BPhotoDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setLat(e.getLat());
        dto.setLng(e.getLng());
        dto.setLowUrl(e.getLowPath());
        dto.setOrigUrl(e.getOriginalPath());
        dto.setSmallUrl(e.getSmallPath());
        dto.setType(e.getType());
        return dto;
    }
}
