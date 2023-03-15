package com.xxzou.javaexample.mysql.spring.dynamicdatasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> curDataSource = new ThreadLocal<>();


    @Override
    protected Object determineCurrentLookupKey() {
        return getCurrentDataSource();
    }


    public static String getCurrentDataSource(){
        String dataSource = curDataSource.get();
        if(dataSource == null){
            return DataSourceName.DATASOURCE_A;
        }
        return dataSource;
    }


    public static void setCurrentDataSource(String dataSource){
        curDataSource.set(dataSource);
    }

    public static void resetCurrentDataSource(){
        curDataSource.remove();
    }


}
