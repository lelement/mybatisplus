package com.niull.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;

/**
 * @Author niull
 * @Date 2020/11/30 14:05
 * @Description 牛立露你就写点注释吧
 */
public class GenerateCode {
    public static void main(String[] args) {
        //构建一个代码生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setAuthor("niull");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://sh-cdb-2h0hokg2.sql.tencentcdb.com:61540/kxg_apollo_config_db_dev?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("kxg_apollo");
        dataSourceConfig.setPassword("QmFzZTY05piv572R57uc5LiK5pyA5bi46KeB55");
        autoGenerator.setDataSource(dataSourceConfig);

        PackageConfig pc = new PackageConfig();
        pc.setModuleName("mybatisplus");
        pc.setParent("com.niull");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        autoGenerator.setPackageInfo(pc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude("t_user");
        strategy.setLogicDeleteFieldName("deleted");
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        strategy.setTableFillList(Arrays.asList(createTime,updateTime));
        strategy.setVersionFieldName("version");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("t_");
        autoGenerator.setStrategy(strategy);
        autoGenerator.execute();
    }
}
