package com.hbsoo.db.processor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.scripting.support.ScriptFactoryPostProcessor;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Component;
/**
 * Created by zun.wei on 2021/7/22.
 */
@Component
public class CustomerScriptFactoryPostProcessor extends ScriptFactoryPostProcessor {

    @Override
    protected ScriptSource convertToScriptSource(String beanName, String scriptSourceLocator, ResourceLoader resourceLoader) {

        if (scriptSourceLocator.startsWith(INLINE_SCRIPT_PREFIX)) {
            return new StaticScriptSource(scriptSourceLocator.substring(INLINE_SCRIPT_PREFIX.length()), beanName);

        }

//        if (scriptSourceLocator.startsWith(GroovyConstant.SCRIPT_SOURCE_PREFIX)) {
//            return new DatabaseScriptSource(StringUtils.substringAfter(scriptSourceLocator, GroovyConstant.SCRIPT_SOURCE_PREFIX));
//        }

        if (scriptSourceLocator.startsWith("database:")) {
            return new DatabaseScriptSource(StringUtils.substringAfter(scriptSourceLocator, "database:"));
        }

        return new ResourceScriptSource(resourceLoader.getResource(scriptSourceLocator));
    }

}
