package com.baojie.manage.back.common.enums;

import com.baojie.manage.base.exception.IMessageCode;

public enum TaskExCode implements IMessageCode{
	TASK_NULL("10301","任务不存在"),
    TASK_ADD_FAIL("10302","添加任务失败"),
    TASK_ID_NULL("10303","任务id为空"),
    TASK_STATUS_FAIL("10304","任务状态错误");

    private String code;
    private String message;
    
    private TaskExCode(String code, String message){
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
