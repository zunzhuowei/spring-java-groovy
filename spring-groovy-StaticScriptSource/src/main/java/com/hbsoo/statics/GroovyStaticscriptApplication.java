package com.hbsoo.statics;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zun.wei on 2021/7/22.
 */
public class GroovyStaticscriptApplication {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-groovy.xml");

        HelloService bean = context.getBean(HelloService.class);

        String sayHello = bean.sayHello();

        System.out.println(sayHello);
    }

}
