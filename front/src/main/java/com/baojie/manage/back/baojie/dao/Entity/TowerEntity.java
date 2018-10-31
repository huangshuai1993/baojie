package com.baojie.manage.back.baojie.dao.Entity;

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
@Table(name = "opt_tower")
public class TowerEntity extends BaseEntity {

	private static final long serialVersionUID = -3747404580948928227L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long towerId;// id

	@Column
	private String towerName = "";
	@Column
	private String memo = "";
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


	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
