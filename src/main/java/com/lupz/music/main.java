package com.lupz.music;

/**
 * @program: music
 * @description:
 * @author: LuPz
 * @create: 2021-08-15 13:48
 **/
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mmail2
 * @description:
 * @author: LuPz
 * @create: 2021-06-24 12:47
 **/
public class main {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456789a0.0");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/music?useUnicode=true");
        autoGenerator.setDataSource(dataSourceConfig);
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOpen(false);
        String projectPath = System.getProperty("user.dir");
        //System.getProperty("user.dir") 获取的是F:\idea workspace\mmail2
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setAuthor("LuPz");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        autoGenerator.setGlobalConfig(globalConfig);
        //包信息
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.lupz.music");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.Impl");
        autoGenerator.setPackageInfo(packageConfig);

        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        //如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        //自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);
        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setTablePrefix("m_");
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }
}
