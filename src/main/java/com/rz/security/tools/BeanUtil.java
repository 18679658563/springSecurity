package com.rz.security.tools;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 类转化器
 * User: silence
 * Date: 2019-02-21
 * Time: 上午8:37
 */
public class BeanUtil {

    /**
     * 单个类转化
     * @param obj
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T createBeanByTarget (Object obj, Class cls){
        Object target = null;
        try {
            target = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(obj, target);
        return (T) target;
    }

    /**
     * 集合类型转化
     * @param objects
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> createBeanListByTarget(List objects, Class cls){
        List<T> results = new ArrayList<T>();
        if(CollectionUtils.isEmpty(objects)){
            return results;
        }
        for(Object obj : objects){
            T t = createBeanByTarget(obj, cls);
            results.add(t);
        }
        return results;
    }


}
