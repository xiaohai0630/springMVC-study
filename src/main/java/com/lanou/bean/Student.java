package com.lanou.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dllo on 17/9/29.
 */
// 想要返回xml时添加的注解
//@XmlRootElement
public class Student {

    private String username;
    private String password;
    private String hobby;

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

}
