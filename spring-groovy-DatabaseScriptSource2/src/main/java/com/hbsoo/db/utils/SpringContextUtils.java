package com.hbsoo.db.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by zun.wei on 2021/7/22.
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.context = applicationContext;
    }

    public static void autowireBean(Object bean) {
        context.getAutowireCapableBeanFactory().autowireBean(bean);
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);

    }

    public static <T> T getBean(String name) {
        return (T) context.getBean(name);

    }

}
