package com.hbsoo.db;

import com.hbsoo.db.service.HelloService;
import com.hbsoo.db.utils.SpringContextUtils;
import groovy.lang.GroovyClassLoader;
import org.apache.tools.ant.util.FileUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Method;

/**
 * Created by zun.wei on 2021/7/22.
 */
public class DatabaseScriptSourceApp2 {


    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-groovy.xml");

        //HelloService bean = SpringContextUtils.getBean("helloService");
        HelloService bean = context.getBean(HelloService.class);
        String sayHello = bean.sayHello("张三");
        System.out.println(sayHello);


    }

}
