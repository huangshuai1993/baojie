package com.baojie.manage.back.baojie.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.baojie.manage.base.common.service.BaseDO;

import tk.mybatis.mapper.annotation.NameStyle;

@Table(name = "opt_contract")
@NameStyle
public class ContractEntity extends BaseDO implements Serializable {

	private static final long serialVersionUID = 23765255042547423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// id

	@Column
	private String contractName;

	@Column
	private Integer type;

	@Column
	private Integer detailType;

	@Column
	private Long towerId;// id

	@Column
	private String towerName;

	@Column
	private String company;

	@Column
	private String serviceCompany;

	@Column
	private String commencementDate;

	@Column
	private String terminationDate;

	@Column
	private String totalPrices;

	@Column
	private String monthPrices;

	@Column
	private String paidPrices;

	@Column
	private String balance;

	@Column
	private Integer peopleCount;

	@Column
	private Integer copies;

	@Column
	private String department;

	@Column
	private String transactor;

	@Column
	private String contact;
	@Column
	private Integer status;// 合同履行情况：1:正常履行、2:到期终止、3:变更、4：解除

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
	private Integer dataFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getServiceCompany() {
		return serviceCompany;
	}

	public void setServiceCompany(String serviceCompany) {
		this.serviceCompany = serviceCompany;
	}

	public String getCommencementDate() {
		return commencementDate;
	}

	public void setCommencementDate(String commencementDate) {
		this.commencementDate = commencementDate;
	}

	public String getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getTotalPrices() {
		return totalPrices;
	}

	public void setTotalPrices(String totalPrices) {
		this.totalPrices = totalPrices;
	}

	public String getMonthPrices() {
		return monthPrices;
	}

	public void setMonthPrices(String monthPrices) {
		this.monthPrices = monthPrices;
	}

	public String getPaidPrices() {
		return paidPrices;
	}

	public void setPaidPrices(String paidPrices) {
		this.paidPrices = paidPrices;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public Integer getDataFlag() {
		return dataFlag;
	}

	public void setDataFlag(Integer dataFlag) {
		this.dataFlag = dataFlag;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDetailType() {
		return detailType;
	}

	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}

	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	public Integer getCopies() {
		return copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

}
