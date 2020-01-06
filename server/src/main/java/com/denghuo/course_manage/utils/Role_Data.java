package com.denghuo.course_manage.utils;

public enum Role_Data {

    admin(3,"admin")
    ,teacher(2,"teacher")
    ,student(1,"student");


    private Integer roleNum;
    private String roleName;

    Role_Data(Integer roleNum, String roleName) {
        this.roleNum = roleNum;
        this.roleName = roleName;
    }

    public Integer getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(Integer roleNum) {
        this.roleNum = roleNum;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
