package com.endless.tools.swt.save;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import java.util.HashMap;
import java.util.Map;

/**
 * 专门处理combo的工具类
 * */
public class UiCombo implements UiAndFieldFace<JSONArray> {

    private Combo combo;
    private Map<String,String> nameAndIdMap = new HashMap<>();
    /**
     * 在jsonObject中的展示的那个文本
     * */
    private String frontKey;
    /**
     * 在jsonObject中的获取当前的front对应的值。
     * */
    private String backKey;
    private JSONArray jsonArray;
    public UiCombo(Composite composite , String frontKey, String backKey){
        combo = new Combo(composite, SWT.PUSH);
        this.frontKey = frontKey;
        this.backKey = backKey;

    }

    public void setFrontAndBack(String frontKey , String backKey){
        this.frontKey = frontKey;
        this.backKey = backKey;
    }
    /**
     * 输入jsonArray
     * */
    public void input(JSONArray jsonArray  ){
        combo.removeAll();
        this.jsonArray = jsonArray;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            addItem(jsonObject);
        }

    }
    /**
     * 新增一条item
     * */
    public void addItem(JSONObject jsonObject){
        String name = jsonObject.getString(frontKey);
        String id = jsonObject.getString(backKey);
        combo.add(name);
        nameAndIdMap.put(name,id);
    }


    public String getId(){
        String text = combo.getText();
        return nameAndIdMap.get(text);
    }

    public JSONArray output(){
        return jsonArray;
    }

    public Combo getCombo(){
        return combo;
    }



}
