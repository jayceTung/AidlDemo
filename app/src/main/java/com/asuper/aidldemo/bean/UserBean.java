package com.asuper.aidldemo.bean;

/**
 * @author super
 * @date 2020/10/9
 */
public class UserBean extends BaseBean {
    public String userName;
    public int age;

    public UserBean(String name ,int age){
        this.userName =name;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
