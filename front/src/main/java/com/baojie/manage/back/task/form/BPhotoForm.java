
package com.baojie.manage.back.task.form;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.baojie.manage.back.task.dto.BPhotoDto;
import com.baojie.manage.base.common.util.PropertiesHelper;

public class BPhotoForm implements Serializable{

	private static final long serialVersionUID = -4918548871189203986L;
	private static String urlPrefix = PropertiesHelper.getString("urlpath.prefix", "/");
    private Long id ;
    private int type;
    private double lat;
    private double lng;
    private String origUrl;//原始照片
    private String lowUrl;//低质量照片
    private String smallUrl;//缩略小图照片
    private String name;
    public BPhotoForm() {}
    
    public BPhotoForm(String test) {
        this.setId(1L);
        this.setType(1);
        this.setLat(39.9096);
        this.setLng(116.3972);
        this.setOrigUrl(urlPrefix+"/00/00/rBH6-Fn8ICiAcALNAAvWFkcZHjA079.jpg");
        this.setLowUrl(urlPrefix+"/00/00/rBH6-Fn8IC-AdkPuAACfuF9EUmA724.jpg");
        this.setSmallUrl(urlPrefix+"/00/00/rBH6-Fn8IDCAV-atAAAIWxOBrbc646.jpg");
    }
    
    public BPhotoForm(BPhotoDto d){
        this.setId(d.getId());
        this.setType(d.getType());
        this.setLat(d.getLat());
        this.setLng(d.getLng());
        this.setOrigUrl(urlPrefix+d.getOrigUrl());
        this.setLowUrl(urlPrefix+d.getLowUrl());
        this.setSmallUrl(urlPrefix+d.getSmallUrl());
        this.setName(d.getName());
   }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getOrigUrl() {
        return origUrl;
    }

    public void setOrigUrl(String origUrl) {
        this.origUrl = origUrl;
    }

    public String getLowUrl() {
        return lowUrl;
    }

    public void setLowUrl(String lowUrl) {
        this.lowUrl = lowUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    };
    
    @Override
    public String toString() {
        String[] others = new String[] {};
        return ReflectionToStringBuilder.toStringExclude(this, others);
    }
    
}
