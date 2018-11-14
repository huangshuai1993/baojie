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
@Table(name = "opt_contract")
public class ContractEntity extends BaseEntity {

	private static final long serialVersionUID = 23765255042547423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// id

	@Column
	private String contractName;

	@Column
	private int type;

	@Column
	private int detailType;

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
	private int peopleCount;

	@Column
	private int copies;

	@Column
	private String department;

	@Column
	private String transactor;

	@Column
	private String contact;
	@Column
	private int status;// 合同履行情况：1:正常履行、2:到期终止、3:变更、4：解除

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
	private int dataFlag;

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



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public int getDetailType() {
		return detailType;
	}



	public void setDetailType(int detailType) {
		this.detailType = detailType;
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



	public int getPeopleCount() {
		return peopleCount;
	}



	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}



	public int getCopies() {
		return copies;
	}



	public void setCopies(int copies) {
		this.copies = copies;
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



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
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
