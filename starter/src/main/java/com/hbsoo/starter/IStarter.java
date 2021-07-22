package com.hbsoo.starter;

import com.hbsoo.starter.model.User;
import groovy.lang.Closure;

/**
 * Created by zun.wei on 2021/7/21.
 */
public interface IStarter {

    int add(int a, int b);

    String getUserName(Long id);

    long testClosure(Closure closure);

    void execute(Long times);


    User getUserById(Long id);

}
