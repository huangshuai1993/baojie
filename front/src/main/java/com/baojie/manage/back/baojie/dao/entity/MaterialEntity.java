package com.baojie.manage.back.baojie.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.baojie.manage.base.dao.BaseEntity;

@Entity
@Table(name = "opt_material")
@DynamicInsert
@DynamicUpdate
public class MaterialEntity extends BaseEntity {
	private static final long serialVersionUID = -2416487141469822776L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long materialId;// id
	@Column
	private String materialName;
	@Column
	private Integer type;
	@Column
	private BigDecimal price;
	@Column
	private Integer count;
	@Column
	private BigDecimal totalPrice;
	@Column
	private String purchaseTime;
	@Column
	private Long towerId;
	@Column
	private String towerName;

	@Column
	private String memo;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;// 创建时间
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;// 修改时间

	/**
	 * 状态 默认1可用
	 */
	@Column
	private int dataFlag = 1;

	
	public Long getMaterialId() {
		return materialId;
	}


	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}


	public String getMaterialName() {
		return materialName;
	}


	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public BigDecimal getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getPurchaseTime() {
		return purchaseTime;
	}


	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}


	

	public Long getTowerId() {
		return towerId;
	}


	public void setTowerId(Long towerId) {
		this.towerId = towerId;
	}


	public String getTowerName() {
		return towerName;
	}


	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
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


	public int getDataFlag() {
		return dataFlag;
	}


	public void setDataFlag(int dataFlag) {
		this.dataFlag = dataFlag;
	}


	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
