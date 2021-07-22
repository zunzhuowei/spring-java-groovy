package com.hbsoo.db;

import com.hbsoo.db.utils.SpringContextUtils;
import groovy.lang.GroovyClassLoader;
import org.apache.tools.ant.util.FileUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;

/**
 * Created by zun.wei on 2021/7/22.
 */
public class DatabaseScriptSourceApp {


    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-groovy.xml");

        // 从数据库中读取脚本信息
        //GroovyScript groovyScript = groovyScriptService.getOne(new QueryWrapper<GroovyScript>()
        //        .eq("script_name", "helloService").eq("status", "ENABLE"));
        //System.out.println(groovyScript.getScriptContent());

        // 从classpath目录中读取脚本信息，模拟db读取
        ClassPathResource resource = new ClassPathResource("in-db-scripti.txt");
        try (Reader r = new FileReader(resource.getFile())) {
            final String readFully = FileUtils.readFully(r);
            Class clazz = new GroovyClassLoader().parseClass(readFully);
            Object o = clazz.newInstance();

            SpringContextUtils.autowireBean(o);

            Method method = clazz.getMethod("sayHello", String.class);

            String s = (String) method.invoke(o, "maple");

            System.out.println("s = " + s);
        }


    }

}
