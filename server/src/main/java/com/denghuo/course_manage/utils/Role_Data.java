package com.denghuo.course_manage.utils;

public enum Role_Data {

    admin(3)
    ,teacher(2)
    ,student(1);


    private Integer roleNum;

    Role_Data(Integer roleNum) {
        this.roleNum = roleNum;
    }

    public Integer getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(Integer roleNum) {
        this.roleNum = roleNum;
    }
}
