package com.yt.tool.swt.save;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.yt.tool.swt.base.LogFace;
import com.yt.tool.swt.base.SwtVoid;

import java.lang.reflect.Field;

/**
 * ui和类属性的转换，保存到本地json
 * */
public class UiAndFieldUtils {


    /**
     * 从uiObj中获取有uiAndField的field，拼装成json
     * */
    public static JSONObject getJsonByUi(Object uiObj){
        JSONObject jsonObject = new JSONObject();
        Field[] fields = ReflectUtil.getFields(uiObj.getClass());
        for (Field field : fields) {
            Object fieldValue = ReflectUtil.getFieldValue(uiObj, field);
            if(fieldValue == null){
                continue;
            }
            String name = field.getName();
            //如果只是属性
            String outputMethodName = getOutputMethodName(field);
            if(StrUtil.isNotBlank(outputMethodName)) {
                Object invoke = ReflectUtil.invoke(fieldValue, outputMethodName);
                jsonObject.put(name, invoke);
            }

            //如果是uiClass，会变成递归
            UiAndClass andClass = field.getAnnotation(UiAndClass.class);
            if(andClass !=null){
                JSONObject jsonByUi = getJsonByUi(fieldValue);
                jsonObject.put(name,jsonByUi);
            }
        }
        return jsonObject;
    }

    /**
     * 保存对象为json
     * */
    public static void save(Object uiObj){
        JSONObject jsonByUi = getJsonByUi(uiObj);
        String s = JSONObject.toJSONString(jsonByUi, true);
        LogFace.log(uiObj.getClass().getSimpleName()+" save-->  "+jsonByUi);
        FileUtil.writeString(s, SwtVoid.getUserDir()+uiObj.getClass().getSimpleName()+".json","utf-8");
    }

    /**
     * 将jsonObject的值存入到uiObj中
     * */
    public static void setJson2Ui(Object uiObj , JSONObject jsonObject){
        Field[] fields = ReflectUtil.getFields(uiObj.getClass());
        for (Field field : fields) {
            String name = field.getName();
            Object o = jsonObject.get(name);
            Object fieldValue = ReflectUtil.getFieldValue(uiObj, field);
            if(fieldValue == null){
//                OpLogComp.log(field.getName()+"    初始化未完成");
                continue;
            }

            String inputMethodName = getInputMethodName(field, fieldValue);
            if(StrUtil.isNotBlank(inputMethodName)){
                ReflectUtil.invoke(fieldValue, inputMethodName,o);
            }

            UiAndClass uiAndClass = field.getAnnotation(UiAndClass.class);
            if(uiAndClass != null){
                setJson2Ui(fieldValue, (JSONObject) o);
            }


        }

    }
    /**
     * 读取json转换为ui
     * */
    public static void load(Object uiObj ){
        try{
            String str = FileUtil.readString(SwtVoid.getUserDir()+uiObj.getClass().getSimpleName()+".json","utf-8");
            LogFace.log(uiObj.getClass().getSimpleName()+" load-->  "+str);
            JSONObject jsonObject = JSONObject.parseObject(str);
            setJson2Ui(uiObj,jsonObject);

        }catch (Exception e){
            LogFace.err(e);
        }

    }
    /**
     * 获取input的名称，可以根据不同的obj类型，获取不同的input，如text，btn等
     * */
    public static String getInputMethodName(Field field , Object o){

        UiAndField annotation = field.getAnnotation(UiAndField.class);
        if(annotation != null && o != null){

            String methodName = annotation.setMethodName();
            if(ClassUtil.isAssignable(UiAndFieldFace.class,field.getType()) && methodName.trim().length() == 0){
                methodName = "input";
            }
//            ReflectUtil.invoke(fieldValue, methodName,o);
            return methodName;
        }

        return null;
    }

    /**
     * 获取output的名称，可以根据不同的obj类型，获取不同的output，如text，btn等
     * */
    public static String getOutputMethodName(Field field){
        UiAndField annotation = field.getAnnotation(UiAndField.class);
        if(annotation != null){
            String methodName = annotation.getMethodName();
            if(ClassUtil.isAssignable(UiAndFieldFace.class,field.getType())  && methodName.trim().length() == 0){
                methodName = "output";
            }
            return methodName;
        }


        return null;
    }
}
