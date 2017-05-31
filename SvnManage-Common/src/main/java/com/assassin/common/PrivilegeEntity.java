package com.assassin.common;

/**
 * Created by Administrator on 2017/4/29.
 */
public class PrivilegeEntity {

    private String privilegeCode; //权限代码
    private String privilegeId; //权限内码
    private String name; //权限名称

    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
