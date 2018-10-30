package com.baojie.manage.back.common.enums;

import com.baojie.manage.base.exception.IMessageCode;

public enum financeExCode implements IMessageCode{
    FINANCE_PWD_UPDATE_OK("30000","密码修改成功"),
    FINANCE_PWD_NOTEQUAL("30001","密码不一致"),
    FINANCE_PARAM_NULL("30002","缺少参数"),
    FINANCE_PWD_UPDATE_ERROR("30003","修改失败"),
    FINANCE_PAYRETURN_OK("30004","申请成功"),
    FINANCE_PAYRETURN_ERROR("30005","申请失败"),
    FINANCE_PWD_UPDATE_CANNOT("30006","非财务人员,不能修改密码"),
    FINANCE_PAYRETURN_CANNOT("30007","非财务人员,不能申请退款"),
    FINANCE_PWD_NULL("30008","请先设置密码后再申请退款"),
    FINANCE_PWD_ERROR("30009","密码错误"),
    TASKSTATUS_NOT_NOPASS("30010","任务状态不正确"),
    TASK_INFO_NULL("30011","任务不存在");
 
    

    private String code;
    private String message;
    
    private financeExCode(String code, String message){
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
