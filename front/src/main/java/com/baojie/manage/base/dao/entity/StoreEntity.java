package com.baojie.manage.base.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.baojie.manage.base.dao.BaseEntity;
/**
 * 门店实体
 * @author hs
 *
 * @date 2017年10月24日
 */
@Entity
@Table(name = "OPT_STORE")
public class StoreEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// id
    
    private String name;//门店名称
    
    private String provinceCode;// 省份编码
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date created  ;// 创建时间
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated  ;// 修改时间
    
    /**
     * 状态 默认1可用
     */
    private int status = 1;

	public StoreEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
