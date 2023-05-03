package com.xxzou.javaexample.mysql.spring.dynamicdatasource.mapper;

import com.xxzou.javaexample.mysql.spring.dynamicdatasource.annotation.TargetDataSource;
import com.xxzou.javaexample.mysql.spring.dynamicdatasource.config.DataSourceName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
@TargetDataSource(DataSourceName.DATASOURCE_A)
public interface DataSourceAMapper {

    @Select("select name from data_source")
    String getDataSourceName();

}
