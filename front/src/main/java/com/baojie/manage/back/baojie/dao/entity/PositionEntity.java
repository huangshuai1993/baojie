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

import com.baojie.manage.base.dao.BaseEntity;

@Entity
@Table(name = "opt_position")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PositionEntity extends BaseEntity {

	private static final long serialVersionUID = -2965189141967715426L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long positionId;// id
	@Column
	private String positionName; // 职位名称,
	@Column
	private Long towerId; // 所属楼盘,
	@Column
	private String towerName; // 楼盘名称,
	@Column
	private BigDecimal basePay; // 基本工资,
	@Column
	private BigDecimal allowance; // 岗位津贴,
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

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public BigDecimal getBasePay() {
		return basePay;
	}

	public void setBasePay(BigDecimal basePay) {
		this.basePay = basePay;
	}

	public BigDecimal getAllowance() {
		return allowance;
	}

	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
