package  com.sys.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SysAutoGenerator extends AutoGenerator {
    private static final Logger logger = LoggerFactory.getLogger(AutoGenerator.class);

    @Override
    public void execute() {
        logger.debug("==========================准备生成文件...==========================");
        if (null == this.config) {
            this.config = new ConfigBuilder(this.getPackageInfo(), this.getDataSource(), this.getStrategy(), this.getTemplate(), this.getGlobalConfig());
            if (null != this.injectionConfig) {
                this.injectionConfig.setConfig(this.config);
            }
        }

//        if (null == this.getTemplateEngine()) {
//            this.setTemplateEngine(new VelocityTemplateEngine());
//        }
        this.setTemplateEngine(new SysFreemarkerTemplateEngine());
        final ConfigBuilder configBuilder = this.pretreatmentConfigBuilder(this.config);


        final Map<String, String> pathInfo = configBuilder.getPathInfo();
        final Map<String, String> packageInfo = configBuilder.getPackageInfo();
        packageInfo.put("VO",packageInfo.get("Controller")+".vo");
        pathInfo.put("vo_path",pathInfo.get("controller_path")+"/vo");
        packageInfo.put("VOConvert",packageInfo.get("Controller")+".convert");
        pathInfo.put("vo_convert_path",pathInfo.get("controller_path")+"/convert");



        this.getTemplateEngine().init(configBuilder).mkdirs().batchOutput().open();


        logger.debug("==========================文件生成完成！！！==========================");

    }
}
