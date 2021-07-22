package com.hbsoo.db.processor;

import org.apache.tools.ant.util.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scripting.ScriptSource;
import org.springframework.util.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by zun.wei on 2021/7/22.
 */
public class DatabaseScriptSource implements ScriptSource{

    /**
     * 脚本名称
     */
    private String scriptName;

    /**
     * 构造函数
     *
     * @param scriptName
     */
    public DatabaseScriptSource(String scriptName) {
        this.scriptName = scriptName;
    }

    @Override
    public String getScriptAsString() throws IOException {
        // 读取db获取脚本缓存起来
        //return GroovyCache.getByName(scriptName).getGroovyContent();
        // 模拟读取db
        //ClassPathResource resource = new ClassPathResource("in-db-scripti.txt");
        ClassPathResource resource = new ClassPathResource(this.scriptName + ".txt");
        try (Reader r = new FileReader(resource.getFile())) {
            return FileUtils.readFully(r);
        }

    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public String suggestedClassName() {
        return StringUtils.stripFilenameExtension(this.scriptName);
    }

}
