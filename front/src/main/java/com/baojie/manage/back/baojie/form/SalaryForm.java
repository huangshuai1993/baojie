package com.baojie.manage.back.baojie.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalaryForm implements Serializable {

	private static final long serialVersionUID = -1929072513865151501L;
	private Long id;
	private Long staffId;// 职员id
	private String staffName;// 职员姓名
	private Long towerId;// 楼盘id
	private String towerName;// 楼盘名称
	private Long positionId;// 职务id
	private String positionName;// 职务名
	private BigDecimal basePay;// 基本工资
	private BigDecimal allowance;// 岗位津贴
	private Integer workDay;// 工作天数(出勤)
	private BigDecimal overtimePay;// 加班费
	private BigDecimal holiday;// 节假日费
	private BigDecimal other;// 其他
	private BigDecimal sendPay;// 应发工资
	private BigDecimal personTax;// 个调税
	private BigDecimal socialSecurity;// 社保
	private BigDecimal askForLeave;// 病事假
	private BigDecimal otherDeductPay;// 其他扣款项
	private BigDecimal deductTotalPay;// 扣款合计
	private BigDecimal realPay;// 实发工资
	private String salaryMonth;//工资时间
	private String memo;// 备注
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;// 创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updated;// 修改时间
	private int dataFlag = 1;// 数据标志，0 无效 1 有效a

	@Override
	public String toString() {
		String[] others = new String[] {};
		return ReflectionToStringBuilder.toStringExclude(this, others);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
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

	public int getDataFlag() {
		return dataFlag;
	}

	public void setDataFlag(int dataFlag) {
		this.dataFlag = dataFlag;
	}

	public String getSalaryMonth() {
		return salaryMonth;
	}

	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}
}
