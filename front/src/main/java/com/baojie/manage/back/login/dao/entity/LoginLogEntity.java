package com.baojie.manage.back.login.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.baojie.manage.base.dao.BaseEntity;


@Entity
@Table(name = "sys_login_log")
public class LoginLogEntity extends BaseEntity{

    private static final long serialVersionUID = -5937479892477896509L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employeeId", length = 20)
    private Long employeeId;  
    
    @Column(name = "loginType", length = 4)
    private Integer loginType;
    
    @Column(name = "ip", length = 20)
    private String ip;
    
    @Column(name = "projectSource", length = 20)
    private String projectSource;
    @Column(name = "created")
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    
    @Column(name = "status")
    private int status;
    
    public LoginLogEntity() {
    }
    public LoginLogEntity(Long employeeId,Integer loginType, String ip,String projectSource) {
        this.employeeId = employeeId;
        this.loginType = loginType == null?0:loginType;
        this.ip = ip == null?"":ip;
        this.projectSource = projectSource ==null?"":projectSource;
        this.created = new Date();
        this.updated = new Date();
        this.status = 1;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
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
    
    
}
