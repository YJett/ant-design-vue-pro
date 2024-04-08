package com.example.llmauthentication.model;

public class EcnuUser {
    private String userId;
    private String name;
    private Integer vpnEnabled;
    // 省略构造函数、getter和setter


    @Override
    public String toString() {
        return "EcnuUser{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", vpnEnabled='" + vpnEnabled + '\'' +
                '}';
    }

    public EcnuUser() {
    }

    public EcnuUser(String userId, String name, Integer vpnEnabled) {
        this.userId = userId;
        this.name = name;
        this.vpnEnabled = vpnEnabled;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVpnEnabled() {
        return vpnEnabled;
    }

    public void setVpnEnabled(Integer vpnEnabled) {
        this.vpnEnabled = vpnEnabled;
    }
}
