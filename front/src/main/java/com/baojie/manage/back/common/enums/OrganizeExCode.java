package com.baojie.manage.back.common.enums;

import com.baojie.manage.base.exception.IMessageCode;

public enum OrganizeExCode implements IMessageCode{
    MESSAGE_NOT_FOUND("10000", "信息未找到"),
    MESSAGE_UPDATE_FAIL("10104", "更新失败"),
    WORKER_NULL("10102", "员工不存在"),
    WORKER_IDCARD_REPEAT("10103", "身份证重复"),
    WORKER_ADD_REPEAT("10101", "员工已存在"),
    WORKER_ADD_FAIL("10001", "添加员工失败"),
    STORE_ADD_REPEAT("10201", "门店已存在"),
    STORE_NULL("10202", "门店不存在"),
    STORE_ADD_FAIL("10003","添加门店失败"),
    STORE_DELETE_INFO("10004","该门店存在员工，不能删除");

    private String code;
    private String message;
    
    private OrganizeExCode(String code, String message){
        this.code = code;
        this.message = message;
    }
    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

}
