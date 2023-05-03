package com.xxzou.javaexample.mysql.spring.dynamicdatasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {


    @Bean(DataSourceName.DATASOURCE_A)
    @ConfigurationProperties(prefix = "datasource.a")
    public DataSource dataSourceA(){
        return new HikariDataSource();
    }

    @Bean(DataSourceName.DATASOURCE_B)
    @ConfigurationProperties(prefix = "datasource.b")
    public DataSource dataSourceB(){
        return new HikariDataSource();
    }


    @Primary
    @Bean("dataSource")
    public DataSource dynamicDataSource(@Qualifier(DataSourceName.DATASOURCE_A) DataSource dataSourceA,
                                        @Qualifier(DataSourceName.DATASOURCE_B) DataSource dataSourceB) {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceName.DATASOURCE_A, dataSourceA);
        dataSourceMap.put(DataSourceName.DATASOURCE_B, dataSourceB);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }

    @Bean("transactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
