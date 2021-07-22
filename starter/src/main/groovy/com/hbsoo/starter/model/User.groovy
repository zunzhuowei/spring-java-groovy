package com.hbsoo.starter.model

/**
 * Created by zun.wei on 2021/7/22.
 *
 */
class User {


    long id
    String username
    String password


    @Override
    String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
