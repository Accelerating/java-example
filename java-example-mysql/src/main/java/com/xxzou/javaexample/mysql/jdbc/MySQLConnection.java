package com.xxzou.javaexample.mysql.jdbc;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * thread unsafe
 */
public class MySQLConnection {
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private final String username;
    private final String password;
    private final String url;

    private Connection connection;
    private boolean autoCommit;

    public MySQLConnection(String url, String username, String password){
        this.username = username;
        this.password = password;
        this.url = url;
        this.autoCommit = true;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(this.url, this.username, this.password);
        connection.setAutoCommit(false);
    }

    public <T> List<T> query(String sql, Class<T> resultType, Object... params){
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
            ResultSet rs = ps.executeQuery();
            List<T> resultList = new ArrayList<>();
            List<Method> setMethods = Stream.of(resultType.getDeclaredMethods()).filter(m -> m.getName().startsWith("set") && m.getParameterCount() == 1).collect(Collectors.toList());
            while(rs.next()){
                T result = resultType.getConstructor().newInstance();
                for (Method setMethod : setMethods) {
                    Parameter[] parameters = setMethod.getParameters();
                    char[] chars = setMethod.getName().substring(3).toCharArray();
                    chars[0] = Character.toLowerCase(chars[0]);
                    //assume table field name is underline type
                    String fieldName = camelToUnderLine(new String(chars));
                    try{
                        setMethod.invoke(result, rs.getObject(fieldName, parameters[0].getType()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
                resultList.add(result);
            }
            return resultList;

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int update(String sql, Object... params){

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
            int effCount = ps.executeUpdate();
            if(autoCommit){
                commit();
            }
            return effCount;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void startTransaction(){
        this.autoCommit = false;
    }

    public void commit(){
        try{
            this.connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.autoCommit = true;
        }

    }

    public void rollback(){
        try{
            this.connection.rollback();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.autoCommit = true;
        }
    }

    public static String camelToUnderLine(String str){
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(c));  //统一都转小写


        }
        return sb.toString();
    }
}
