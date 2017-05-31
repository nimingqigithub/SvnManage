package com.assassin.utils;

import com.assassin.exception.RealEstateException;
import freemarker.ext.beans.HashAdapter;
import oracle.sql.CLOB;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by Administrator on 2016/1/27.
 */
public class MapUtils {
    public static String FilterMapValue(Map map,String key){
        if(map==null)
            return "";
        else{
            if(map.containsKey(key)){
                if(map.get(key)==null){
                    return "";
                }
                else{
                    return String.valueOf(map.get(key));
                }
            }
            return "";
        }
    }

    /**
     * 获取新map并赋值
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Map<String,T> getNewMap(String key,T t){
        Map<String,T>map=new HashedMap();
        map.put(key,t);
        return map;
    }
}
