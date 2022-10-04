package com.xxzou.javaexample.net.netty.tcp.rpc.server.ctx;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class ServiceExecutor {

    private static ServiceExecutor INSTANCE = new ServiceExecutor();

    private Map<String, ServiceMethod> serviceMap = new ConcurrentHashMap<>();

    public static ServiceExecutor getInstance(){
        return INSTANCE;
    }

    private ServiceExecutor(){
        init();
    }


    /**
     *
     * @param serviceName  className.methodName[argTypes...]
     * @param <T>
     * @return
     */
    public <T> T executeService(String serviceName, Object... args){
        ServiceMethod serviceMethod = serviceMap.get(serviceName);
        return (T) serviceMethod.invoke(args);
    }

    private void init(){
        try{
            String serviceImplPkg = "com.xxzou.javaexample.net.netty.tcp.rpc.server.impl";
            URL resource = Thread.currentThread().getContextClassLoader().getResource(serviceImplPkg.replace(".", "/"));
            Path path = Paths.get(resource.toURI());
            Files.list(path).forEach(classFile -> {
                try{
                    Class<?> serviceClass = Class.forName(serviceImplPkg + "." + classFile.getFileName().toString().replace(".class", ""));
                    Class<?>[] serviceInterfaces = serviceClass.getInterfaces();
                    Class serviceInterface = serviceInterfaces[0];
                    Method[] methods = serviceInterface.getMethods();
                    Object instance = serviceClass.getDeclaredConstructor().newInstance();
                    for (Method method : methods) {
                        List<String> paramTypes = new ArrayList<>();
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        for (Class<?> parameterType : parameterTypes) {
                            paramTypes.add(parameterType.getSimpleName());
                        }
                        String serviceName = serviceInterface.getSimpleName() + "." + method.getName() + paramTypes;
                        serviceMap.put(serviceName, new ServiceMethod(instance, method));

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static class ServiceMethod{
        static ObjectMapper mapper = new ObjectMapper();
        Object instance;
        Method method;
        ServiceMethod(Object instance, Method method){
            this.instance = instance;
            this.method = method;
        }

        Object invoke(Object... args){
            try {
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class pClass = parameterTypes[i];
                    args[i] = mapper.readValue(mapper.writeValueAsBytes(args[i]), pClass);
                }
                return this.method.invoke(this.instance, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
