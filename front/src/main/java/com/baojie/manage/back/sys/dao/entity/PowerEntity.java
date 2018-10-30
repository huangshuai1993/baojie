package com.baojie.manage.back.sys.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baojie.manage.base.dao.BaseEntity;

@Entity
@Table(name = "sys_power")
public class PowerEntity extends BaseEntity {

	private static final long serialVersionUID = 366572916709354601L;

	@Id
	@Column(name = "powerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long powerId;

	@Column(name = "powerUrl", length = 60)
	private String powerUrl;// 权限路径

	@Column(name = "menuName", length = 50)
	private String menuName;// 菜单名称

	@Column(name = "parentId", length = 11)
	private Long parentId;// 父类菜单id

	@Column(name = "powerName", length = 30)
	private String powerName;// 权限名称

	public Long getPowerId() {
		return powerId;
	}

	public void setPowerId(Long powerId) {
		this.powerId = powerId;
	}

	public String getPowerUrl() {
		return powerUrl;
	}

	public void setPowerUrl(String powerUrl) {
		this.powerUrl = powerUrl;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

}
