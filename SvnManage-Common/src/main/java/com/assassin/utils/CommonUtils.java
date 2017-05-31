package com.assassin.utils;

import com.assassin.exception.RealEstateException;
import oracle.sql.CLOB;
import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by assassin on 2015/9/14.
 */
public class CommonUtils {

    /**
     * 获取参数
     * @param allQueryParam
     * @param keyName
     * @param IsThrowException
     * @return
     */
    public static String GetMapValue(Map<String, String> allQueryParam, String keyName,
                                     Boolean IsThrowException) {
        if (!allQueryParam.containsKey(keyName)) {
            if (IsThrowException) {
                throw new RealEstateException("请传入" + keyName + "参数");
            } else {
                return "";
            }
        } else {
            String str="";
            Object objValue=allQueryParam.get(keyName);
            try {
                //处理数据库中的Clob字段信息
                if (objValue instanceof com.alibaba.druid.proxy.jdbc.ClobProxyImpl) {
                    com.alibaba.druid.proxy.jdbc.ClobProxyImpl impl = (com.alibaba.druid.proxy.jdbc.ClobProxyImpl) objValue;
                    CLOB clob = (CLOB) impl.getRawClob(); // 获取原生的这个 Clob
                    str= (clob == null || clob.length() == 0) ? null : clob.getSubString((long) 1, (int) clob.length());
                } else {
                    str=allQueryParam.get(keyName);
                }
            }
            catch (Exception er){
                return "";
            }

            if(str.equals("null") || str.equals("undefined")){
                str="";
            }
            return str;
        }
    }

    /**
     * 获取Map对象忽略大小写
     * @param map
     * @param key
     * @return
     */
    public static<T> T getMapValueIgnoreCase(Map<String, T> map,String key){
        for(String k:map.keySet()){
            if(k.equalsIgnoreCase(key)){
                return map.get(k);
            }
        }
        return null;
    }

    /**
     * 判断是都存在Key，忽略大小写
     * @param map
     * @param key
     * @param <T>
     * @return
     */
    public static<T> Boolean mapContainsKey(Map<String, T> map,String key){
        for(String k:map.keySet()){
            if(k.equalsIgnoreCase(key)){
               return true;
            }
        }
        return false;
    }



    /**
     * List<map>对象键值转换成小写的
     * @param lst
     * @param <T>
     * @return
     */
    public static<T> List<Map<String, T>> listMapKeyToLowerCaseComm(List<Map<String,T>> lst,Boolean isUpperCase) {
        List<Map<String, T>> lstMapLower = new ArrayList<>();
        if (lst != null && lst.size() > 0) {
            for (Map<String,T> m : lst) {
                Map<String, T> mapLower = mapKeyToLowerCase(m,isUpperCase);
                lstMapLower.add(mapLower);
            }
        }
        return lstMapLower;
    }

    /**
     * map对象键值转换成小写的
     * @param map
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> mapKeyToLowerCase(Map<String, T> map, Boolean isUpperCase) {
        Map<String, T> mapResult = new HashedMap();
        for (String k : map.keySet()) {
            if(isUpperCase)
                k=k.toUpperCase();
            else
                k=k.toLowerCase();
            mapResult.put(k, map.get(k));
        }
        return mapResult;
    }

}
