 package com.seeenergy.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.PropertyNameProcessor;

import java.lang.reflect.Field;

/**
     * 利用反射获取属性中list类的属性名和泛型类型
     * @param clazz
     * @return
     * @throws ClassNotFoundException
     */
 public class JsonToBean {
	 
    public  static  Map<String, Class<?>> getListType(Class<?> clazz) throws ClassNotFoundException{
        Map<String, Class<?>> classmap = new HashMap<>();

        //通过反射获得list类的属性名和泛型类型
        Class<?> target = Class.forName(clazz.getName());
        Field[] fields = target.getDeclaredFields();
        for(Field f : fields){
            //判断是否为list类
            if(f.getType().isAssignableFrom(List.class)){
                //属性名
                String fieldName = f.getName();
                Type fc = f.getGenericType();
                if(fc instanceof ParameterizedType){
                    ParameterizedType pt = (ParameterizedType) fc;
                    //泛型类型
                    Class genericClazz = (Class)pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。

                    classmap.put(fieldName, genericClazz);
                    //递归调用此方法，获取属性类中的list
                    classmap.putAll(getListType(genericClazz));

                }

            }
        }
        return classmap;
    }


    /**
     * 将json转换为特定的类
     * @param clazz 目标类
     * @param jsonString json字符串
     * @return  
     * @throws ClassNotFoundException
     */
    public static Object getJson(Class<?> clazz,String jsonString) throws ClassNotFoundException{
        JsonConfig config=new JsonConfig();
        config.setRootClass(clazz);
        Map<String,Class<?>> classMap = getListType(clazz);

        PropertyNameProcessor lowerCasePropertyNameProcessor = new PropertyNameProcessor() {
            @Override
            public String processPropertyName(Class aClass, String s) {
                return s.substring(0,1).toLowerCase() + s.substring(1);
            }
        };

        config.setClassMap(classMap);

        config.registerPropertyNameProcessor(clazz, lowerCasePropertyNameProcessor);
        for(Entry<String, Class<?>> entry : classMap.entrySet()){
            config.registerPropertyNameProcessor(entry.getValue(), lowerCasePropertyNameProcessor);
        }

        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Object bean = JSONObject.toBean(jsonObject, config);
        return bean;
    }
    
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
          if (map == null)  
               return null;  
     
          Object obj = beanClass.newInstance();  
    
          org.apache.commons.beanutils.BeanUtils.populate(obj, map);  
    
          return obj;  
     }    
    	        
	  public static Map<?, ?> objectToMap(Object obj) {  
	      if(obj == null)  
	          return null;   
	
	      return new org.apache.commons.beanutils.BeanMap(obj);  
	  } 
}