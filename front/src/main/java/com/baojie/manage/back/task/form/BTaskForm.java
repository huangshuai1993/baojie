package com.baojie.manage.back.task.form;

import java.io.Serializable;
import java.util.Date;

import com.baojie.manage.back.task.dto.BTaskDto;
import com.baojie.manage.base.common.util.SysDqHelper;

public class BTaskForm implements Serializable{
	private static final long serialVersionUID = 3283809361120389967L;

	 private Long id;// id
	    
	    
     private String name="";
    
     private int gender=0;
    
     private String idcardNo="";
    
     private Date birthday;//出生日期
    
     private String mobile="";
    
     private int marriage=0;
    
     private int income=0;
    
     private int hasCautioner=0;//是否有担保人(0=无,1=有)
    
     private String cautionerName="";
    
     private String cautionerIdcardNo="";
    
     private String cautionerRelation="";
    
     private String homeProvinceCode="";
    
     private String homeCityCode="";
    
     private String homeRegionCode="";
    
     private String homeAddress="";
    
     private String workProvinceCode="";
    
     private String workCityCode="";
    
     private String workRegionCode="";
    
     private String workAddress="";
    
     private int product=0;
     private String productName;
    
     private int amount=0;
     
     private int source=0;
    
     private long empId = 0;
     
     private String empName="--";
    
     private long storeId=0;
    
     private String reportText="";
    
     private int taskStatus=0;
     
     private String taskStatusName;
    
     private String memo="";
    
     private Date submitDated;
    
     private Date created  ;// 创建时间
    
     private Date updated  ;// 修改时间
     
     private Date payDated;//支付时间
     
     private Integer payAmount;//支付金额
     
     private String channel;//支付方式
    
     private Integer photoCount=0;
    /**
     * 状态 默认1可用
     */
     
    private int status;
    
    //
    private String storeProvince;
    private String storeName;
    private String storeProvinceCode;
    
	public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public Integer getPayAmount() {
        return payAmount;
    }
    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }
    public Date getPayDated() {
        return payDated;
    }
    public void setPayDated(Date payDated) {
        this.payDated = payDated;
    }
    public String getStoreProvinceCode() {
		return storeProvinceCode;
	}
	public void setStoreProvinceCode(String storeProvinceCode) {
		this.storeProvinceCode = storeProvinceCode;
	}
	public String getStoreProvince() {
		return storeProvince;
	}
	public void setStoreProvince(String storeProvince) {
		this.storeProvince = storeProvince;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public BTaskForm() {
	}
	public BTaskForm(BTaskDto dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.gender = dto.getGender();
		this.idcardNo = dto.getIdcardNo();
		this.birthday = dto.getBirthday();
		this.mobile = dto.getMobile();
		this.marriage = dto.getMarriage();
		this.income = dto.getIncome();
		this.hasCautioner = dto.getHasCautioner();
		this.cautionerName = dto.getCautionerName();
		this.cautionerIdcardNo = dto.getCautionerIdcardNo();
		this.cautionerRelation = dto.getCautionerRelation();
		this.homeProvinceCode = dto.getHomeProvinceCode();
		this.homeCityCode = dto.getHomeCityCode();
		this.homeRegionCode = dto.getHomeRegionCode();
		this.homeAddress = SysDqHelper.lookupDQName(dto.getHomeProvinceCode())+
				SysDqHelper.lookupDQName(dto.getHomeCityCode())+
				SysDqHelper.lookupDQName(dto.getHomeRegionCode())+
				dto.getHomeAddress();
		this.workProvinceCode = dto.getWorkProvinceCode();
		this.workCityCode = dto.getWorkCityCode();
		this.workRegionCode = dto.getWorkRegionCode();
		this.workAddress = SysDqHelper.lookupDQName(dto.getWorkProvinceCode())+
				SysDqHelper.lookupDQName(dto.getWorkCityCode())+
				SysDqHelper.lookupDQName(dto.getWorkRegionCode())+
				dto.getWorkAddress();
		this.product = dto.getProduct();
		//this.productName=ProductEnum.getEnum(dto.getProduct()).getDesc();
		this.amount = dto.getAmount();
		this.source = dto.getSource();
		this.empId = dto.getEmpId();
		this.empName= dto.getEmpName();
		this.storeId = dto.getStoreId();
		this.reportText = dto.getReportText();
		this.taskStatus = dto.getTaskStatus();
		//this.taskStatusName=TaskStatusEnum.getEnum(dto.getTaskStatus()).getDesc();
		this.memo = dto.getMemo();
		this.submitDated = dto.getSubmitDated();
		this.created = dto.getCreated();
		this.updated = dto.getUpdated();
		this.photoCount = dto.getPhotoCount();
		this.status = dto.getPhotoCount();
		this.payDated = dto.getPayDated();
		this.payAmount = dto.getPayAmount();
		this.storeName=dto.getStoreName();
		this.storeProvince=dto.getStoreProvince();
		this.storeProvinceCode=dto.getStoreProvinceCode();
		//this.channel=PayChannelEnum.getEnum(dto.getChannel()).getDesc();
	}
	
	public String getTaskStatusName() {
		return taskStatusName;
	}
	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
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
	public Date getSubmitDated() {
		return submitDated;
	}
	public void setSubmitDated(Date submitDated) {
		this.submitDated = submitDated;
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
	public Integer getPhotoCount() {
		return photoCount;
	}
	public void setPhotoCount(Integer photoCount) {
		this.photoCount = photoCount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
   
}
    
