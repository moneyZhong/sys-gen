package com.sys.test;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.sun.tools.javah.Gen;
import com.sys.util.SysAutoGenerator;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GenCode {
    public final static String DB_URL = "jdbc:mysql://172.16.1.44:3306/okex?useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull";
    public final static String DB_USERNAME = "gcp_dev";
    public final static String DB_PASSWORD = "abD&k4mhl";

    public final static String TABLE_PREFIX = "sys_";
    public final static String PN_PARENT = "com.sys";
    public final static String MODULE_NAME = "";
    public final static String author = "zhongqian";

    private static String[] tables = {
            "sys_resources",
            "sys_role",
            "sys_role_resources",
            "sys_user",
            "sys_user_role"
    };

    public static void main(String[] args) {
        final URL resource = GenCode.class.getResource("");
       createFile(getOutFolder(),tables);
    }
    public static void createFile(String folderPath, String... tableNames){
        System.out.println("代码输出文件夹："+ folderPath);



        File f = new File(folderPath);
        if(f.exists()){
            final boolean delete = f.delete();
            System.out.println("delete: "+folderPath);
            System.out.println("delete: "+delete);


        }

        // 代码生成器
        AutoGenerator mpg = new SysAutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(folderPath);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setEntityName("%sDO");
        gc.setServiceName("%sService");
        gc.setSwagger2(true);


        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        dsc.setUrl(DB_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(DB_USERNAME);
        dsc.setPassword(DB_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(MODULE_NAME);
        pc.setParent(PN_PARENT)
                .setController("controller")
                .setEntity("entity")
                .setMapper("dao")
                .setXml("dao.xml");

        mpg.setPackageInfo(pc);




        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        /*focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });*/

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        path = path + "templates/controller.java";
        System.out.println(path);
        // 配置自定义输出模板
        // templateConfig.setEntity();
        // templateConfig.setService();
        //templateConfig.setController(path);

        // templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(tableNames);
        //strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(TABLE_PREFIX);
        //生成字段常量
        strategy.setEntityColumnConstant(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
    public static String getOutFolder(){
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        path = path.substring(0,path.substring(0,path.length()-1).lastIndexOf("/"));
        return path + "/gen/" + GenCode.class.getClass().getSimpleName();
    }
}
