package com.baojie.manage.back.baojie.dao.entity;

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
@Table(name = "opt_staff")
@org.hibernate.annotations.Entity(
		dynamicInsert = true,dynamicUpdate = true
)
public class StaffEntity extends BaseEntity {

	private static final long serialVersionUID = -5352108387175885494L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// id

	@Column
	private String name;

	@Column
	private String idCard;
	@Column
	private int age;
	@Column
	private int gender;//0男，1女

	@Column
	private String phone;
	@Column
	private Long positionId;
	@Column
	private String positionName;
	@Column
	private Long towerId;
	@Column
	private String towerName;
	@Column
	private int status;//0在职，1离职
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public void setCreated(Date created) {
		this.created = created;
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

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
