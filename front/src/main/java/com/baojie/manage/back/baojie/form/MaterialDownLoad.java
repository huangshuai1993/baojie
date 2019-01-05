package com.baojie.manage.back.baojie.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("物料管理downLoad")
public class MaterialDownLoad implements Serializable {

	private static final long serialVersionUID = -8681746224007047330L;
	@ApiModelProperty("物料名称")
	private String materialName;
	@ApiModelProperty("物料类型")
	private String typeName;
	@ApiModelProperty("单价")
	private BigDecimal price;
	@ApiModelProperty("数量")
	private Integer count;
	@ApiModelProperty("总价")
	private BigDecimal totalPrice;
	@ApiModelProperty("进货日期")
	private String purchaseTime;
	@ApiModelProperty("楼盘名称")
	private String towerName;
	@ApiModelProperty("备注")
	private String memo;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created;// 创建时间
	@ApiModelProperty("修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updated;// 修改时间

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
