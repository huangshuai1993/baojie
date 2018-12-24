package com.baojie.manage.back.baojie.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("工资管理downLoad")
public class SalaryDownLoad implements Serializable {
	private static final long serialVersionUID = 3819204424651039898L;
	@ApiModelProperty("职员姓名")
	private String staffName;// 职员姓名
	@ApiModelProperty(" 楼盘名称")
	private String towerName;// 楼盘名称
	@ApiModelProperty("职务名")
	private String positionName;// 职务名
	@ApiModelProperty("基本工资")
	private BigDecimal basePay;// 基本工资
	@ApiModelProperty("岗位津贴")
	private BigDecimal allowance;// 岗位津贴
	@ApiModelProperty("工作天数(出勤)")
	private Integer workDay;// 工作天数(出勤)
	@ApiModelProperty("加班费")
	private BigDecimal overtimePay;// 加班费
	@ApiModelProperty("节假日费")
	private BigDecimal holiday;// 节假日费
	@ApiModelProperty("其他")
	private BigDecimal other;// 其他
	@ApiModelProperty("应发工资")
	private BigDecimal sendPay;// 应发工资
	@ApiModelProperty("个调税")
	private BigDecimal personTax;// 个调税
	@ApiModelProperty("社保")
	private BigDecimal socialSecurity;// 社保
	@ApiModelProperty("病事假")
	private BigDecimal askForLeave;// 病事假
	@ApiModelProperty("其他扣款项")
	private BigDecimal otherDeductPay;// 其他扣款项
	@ApiModelProperty("扣款合计")
	private BigDecimal deductTotalPay;// 扣款合计
	@ApiModelProperty("实发工资")
	private BigDecimal realPay;// 实发工资
	@ApiModelProperty("工资时间")
	private String salaryMonth;//工资时间
	@ApiModelProperty("备注")
	private String memo;// 备注
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;// 创建时间
	@ApiModelProperty("修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updated;// 修改时间

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	public String getTowerName() {
		return towerName;
	}

	public void setTowerName(String towerName) {
		this.towerName = towerName;
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

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public BigDecimal getOvertimePay() {
		return overtimePay;
	}

	public void setOvertimePay(BigDecimal overtimePay) {
		this.overtimePay = overtimePay;
	}

	public BigDecimal getHoliday() {
		return holiday;
	}

	public void setHoliday(BigDecimal holiday) {
		this.holiday = holiday;
	}

	public BigDecimal getOther() {
		return other;
	}

	public void setOther(BigDecimal other) {
		this.other = other;
	}

	public BigDecimal getSendPay() {
		return sendPay;
	}

	public void setSendPay(BigDecimal sendPay) {
		this.sendPay = sendPay;
	}

	public BigDecimal getPersonTax() {
		return personTax;
	}

	public void setPersonTax(BigDecimal personTax) {
		this.personTax = personTax;
	}

	public BigDecimal getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(BigDecimal socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public BigDecimal getAskForLeave() {
		return askForLeave;
	}

	public void setAskForLeave(BigDecimal askForLeave) {
		this.askForLeave = askForLeave;
	}

	public BigDecimal getOtherDeductPay() {
		return otherDeductPay;
	}

	public void setOtherDeductPay(BigDecimal otherDeductPay) {
		this.otherDeductPay = otherDeductPay;
	}

	public BigDecimal getDeductTotalPay() {
		return deductTotalPay;
	}

	public void setDeductTotalPay(BigDecimal deductTotalPay) {
		this.deductTotalPay = deductTotalPay;
	}

	public BigDecimal getRealPay() {
		return realPay;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
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

	public String getSalaryMonth() {
		return salaryMonth;
	}

	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}
}
