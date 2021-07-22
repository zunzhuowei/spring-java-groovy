//package com.hbsoo.db.processor;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.maple.database.entity.GroovyScript;
//import com.maple.database.groovy.cache.GroovyCache;
//import com.maple.database.groovy.cache.GroovyInfo;
//import com.maple.database.service.GroovyScriptService;
//import groovy.lang.GroovyClassLoader;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.xml.ResourceEntityResolver;
//import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.Assert;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Created by zun.wei on 2021/7/22.
// */
//public class GroovyDynamicLoader implements ApplicationContextAware, InitializingBean {
//
//    private ConfigurableApplicationContext applicationContext;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
//    }
//
//    @Resource
//    private GroovyScriptService groovyScriptService;
//
//    private static final GroovyClassLoader groovyClassLoader = new GroovyClassLoader(GroovyDynamicLoader.class.getClassLoader());
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        init();
//    }
//
//    private void init() {
//
//        List<GroovyScript> groovyScripts = groovyScriptService.list(new QueryWrapper<GroovyScript>().eq("status", "ENABLE"));
//
//        List<GroovyInfo> groovyInfos = groovyScripts.stream().map(groovyScript -> {
//            GroovyInfo groovyInfo = new GroovyInfo();
//            groovyInfo.setClassName(groovyScript.getScriptName());
//            groovyInfo.setGroovyContent(groovyScript.getScriptContent());
//            return groovyInfo;
//        }).collect(Collectors.toList());
//
//        if (CollectionUtils.isEmpty(groovyInfos)) {
//            return;
//        }
//
//        ConfigurationXMLWriter config = new ConfigurationXMLWriter();
//
//        addConfiguration(config, groovyInfos);
//
//        GroovyCache.put2map(groovyInfos);
//
//        loadBeanDefinitions(config);
//    }
//
//    private void addConfiguration(ConfigurationXMLWriter config, List<GroovyInfo> groovyInfos) {
//        for (GroovyInfo groovyInfo : groovyInfos) {
//            writeBean(config, groovyInfo);
//        }
//    }
//
//    private void writeBean(ConfigurationXMLWriter config, GroovyInfo groovyInfo) {
//
//        if (checkSyntax(groovyInfo)) {
//
//            DynamicBean bean = composeDynamicBean(groovyInfo);
//
//            config.write(GroovyConstant.SPRING_TAG, bean);
//        }
//    }
//
//    private boolean checkSyntax(GroovyInfo groovyInfo) {
//
//        try {
//            groovyClassLoader.parseClass(groovyInfo.getGroovyContent());
//        } catch (Exception e) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private DynamicBean composeDynamicBean(GroovyInfo groovyInfo) {
//
//        DynamicBean bean = new DynamicBean();
//
//        String scriptName = groovyInfo.getClassName();
//
//        Assert.notNull(scriptName, "parser className cannot be empty!");
//
//        //设置bean的属性，这里只有id和script-source。
//        bean.put("id", scriptName);
//        bean.put("script-source", GroovyConstant.SCRIPT_SOURCE_PREFIX + scriptName);
//
//        return bean;
//    }
//
//    private void loadBeanDefinitions(ConfigurationXMLWriter config) {
//
//        String contextString = config.getContent();
//
//        if (StringUtils.isBlank(contextString)) {
//            return;
//        }
//
//        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) this.applicationContext.getBeanFactory());
//        beanDefinitionReader.setResourceLoader(this.applicationContext);
//        beanDefinitionReader.setBeanClassLoader(applicationContext.getClassLoader());
//        beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this.applicationContext));
//        beanDefinitionReader.loadBeanDefinitions(new InMemoryResource(contextString));
//
//        String[] postProcessorNames = applicationContext.getBeanFactory().getBeanNamesForType(CustomerScriptFactoryPostProcessor.class, true, false);
//
//        for (String postProcessorName : postProcessorNames) {
//            applicationContext.getBeanFactory().addBeanPostProcessor((BeanPostProcessor) applicationContext.getBean(postProcessorName));
//        }
//    }
//
//}
