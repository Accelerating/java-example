package com.xxzou.javaexample.mysql.spring.dynamicdatasource.config;

import com.xxzou.javaexample.mysql.spring.dynamicdatasource.annotation.TargetDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;


@Aspect
@Component
public class DynamicDataSourceAspect {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("@annotation(com.xxzou.javaexample.mysql.spring.dynamicdatasource.annotation.TargetDataSource)")
    public void datasourceAnnotationPointCut(){}

    @Pointcut("execution(* com.xxzou.javaexample.mysql.spring.dynamicdatasource.mapper..*(..))")
    public void executionPointCut(){}

    @Around("executionPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> clazz = signature.getDeclaringType();

        TargetDataSource classAnnotation = clazz.getAnnotation(TargetDataSource.class);
        TargetDataSource methodAnnotation = method.getAnnotation(TargetDataSource.class);

        TargetDataSource targetDataSource = Optional.ofNullable(methodAnnotation).orElse(classAnnotation);



        if (targetDataSource == null) {
            DynamicDataSource.setCurrentDataSource(DataSourceName.DATASOURCE_A);
        } else {
            DynamicDataSource.setCurrentDataSource(targetDataSource.value());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.resetCurrentDataSource();
            log.debug("clean datasource");
        }


    }

}
