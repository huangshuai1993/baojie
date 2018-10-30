package com.baojie.manage.base.dao.entity;

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
@Table(name = "OPT_TASK")
public class TaskEntity extends BaseEntity {

	private static final long serialVersionUID = -8220092225912193913L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// id

	@Column
	private String name = "";
	@Column
	private int gender = 0;
	@Column
	private String idcardNo = "";
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;// 出生日期
	@Column
	private String mobile = "";
	@Column
	private int marriage = 0;
	@Column
	private int income = 0;
	@Column
	private int hasCautioner = 0;// 是否有担保人(0=无,1=有)
	@Column
	private String cautionerName = "";
	@Column
	private String cautionerIdcardNo = "";
	@Column
	private String cautionerRelation = "";
	@Column
	private String homeProvinceCode = "";
	@Column
	private String homeCityCode = "";
	@Column
	private String homeRegionCode = "";
	@Column
	private String homeAddress = "";
	@Column
	private String workProvinceCode = "";
	@Column
	private String workCityCode = "";
	@Column
	private String workRegionCode = "";
	@Column
	private String workAddress = "";
	@Column
	private int product = 0;
	@Column
	private int amount = 0;
	@Column
	private int source = 0;
	@Column
	private Long empId = 0L;
	@Column
	private Long storeId = 0L;
	@Column
	private String reportText = "";
	@Column
	private int taskStatus = 0;
	@Column
	private String memo = "";
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date submitDated;
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
	private int status = 1;

	public Date getSubmitDated() {
		return submitDated;
	}

	public void setSubmitDated(Date submitDated) {
		this.submitDated = submitDated;
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getMarriage() {
		return marriage;
	}

	public void setMarriage(int marriage) {
		this.marriage = marriage;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getHasCautioner() {
		return hasCautioner;
	}

	public void setHasCautioner(int hasCautioner) {
		this.hasCautioner = hasCautioner;
	}

	public String getCautionerName() {
		return cautionerName;
	}

	public void setCautionerName(String cautionerName) {
		this.cautionerName = cautionerName;
	}

	public String getCautionerIdcardNo() {
		return cautionerIdcardNo;
	}

	public void setCautionerIdcardNo(String cautionerIdcardNo) {
		this.cautionerIdcardNo = cautionerIdcardNo;
	}

	public String getCautionerRelation() {
		return cautionerRelation;
	}

	public void setCautionerRelation(String cautionerRelation) {
		this.cautionerRelation = cautionerRelation;
	}

	public String getHomeProvinceCode() {
		return homeProvinceCode;
	}

	public void setHomeProvinceCode(String homeProvinceCode) {
		this.homeProvinceCode = homeProvinceCode;
	}

	public String getHomeCityCode() {
		return homeCityCode;
	}

	public void setHomeCityCode(String homeCityCode) {
		this.homeCityCode = homeCityCode;
	}

	public String getHomeRegionCode() {
		return homeRegionCode;
	}

	public void setHomeRegionCode(String homeRegionCode) {
		this.homeRegionCode = homeRegionCode;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getWorkProvinceCode() {
		return workProvinceCode;
	}

	public void setWorkProvinceCode(String workProvinceCode) {
		this.workProvinceCode = workProvinceCode;
	}

	public String getWorkCityCode() {
		return workCityCode;
	}

	public void setWorkCityCode(String workCityCode) {
		this.workCityCode = workCityCode;
	}

	public String getWorkRegionCode() {
		return workRegionCode;
	}

	public void setWorkRegionCode(String workRegionCode) {
		this.workRegionCode = workRegionCode;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public int getProduct() {
		return product;
	}

	public void setProduct(int product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getReportText() {
		return reportText;
	}

	public void setReportText(String reportText) {
		this.reportText = reportText;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
