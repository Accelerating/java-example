package com.xxzou.javaexample.mysql.spring.dynamicdatasource.service;

import com.xxzou.javaexample.mysql.spring.dynamicdatasource.annotation.TargetDataSource;
import com.xxzou.javaexample.mysql.spring.dynamicdatasource.config.DataSourceName;
import com.xxzou.javaexample.mysql.spring.dynamicdatasource.mapper.DataSourceAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSourceService {

    @Autowired
    private DataSourceAMapper dataSourceAMapper;

    @TargetDataSource(DataSourceName.DATASOURCE_B)
    public String getDataSource(){
        return dataSourceAMapper.getDataSourceName();
    }

}
