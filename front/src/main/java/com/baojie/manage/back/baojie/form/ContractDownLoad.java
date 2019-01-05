package com.baojie.manage.back.baojie.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("合同管理downLoad")
public class ContractDownLoad implements Serializable {

	private static final long serialVersionUID = 6909679646789746100L;

	@ApiModelProperty("合同名称")
	private String contractName;
	@ApiModelProperty("合同类型")
	private String typeName;
	@ApiModelProperty("服务类型")
	private String detailTypeName;
	@ApiModelProperty("楼盘名称")
	private String towerName;
	@ApiModelProperty("我方单位")
	private String company;
	@ApiModelProperty("对方单位")
	private String serviceCompany;
	@ApiModelProperty("合同生效时间")
	private String commencementDate;
	@ApiModelProperty("合同终止时间")
	private String terminationDate;
	@ApiModelProperty("合同总价")
	private String totalPrices;
	@ApiModelProperty("合同月金额")
	private String monthPrices;
	@ApiModelProperty("合同已付金额")
	private String paidPrices;
	@ApiModelProperty("合同余额")
	private String balance;
	@ApiModelProperty("合同人数")
	private int peopleCount;
	@ApiModelProperty("合同份数")
	private int copies;
	@ApiModelProperty("签约部门")
	private String department;
	@ApiModelProperty("经办人")
	private String transactor;
	@ApiModelProperty("联系方式")
	private String contact;
	@ApiModelProperty("合同履行情况")
	private String statusName; // 合同履行情况：1:正常履行、2:到期终止、3:变更、4：解除
	@ApiModelProperty("备注")
	private String memo;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created;// 创建时间
	@ApiModelProperty("修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updated;// 修改时间


	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getDetailTypeName() {
		return detailTypeName;
	}

	public void setDetailTypeName(String detailTypeName) {
		this.detailTypeName = detailTypeName;
	}
}
