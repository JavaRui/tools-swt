package com.yt.tool.swt.save;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import java.util.HashMap;
import java.util.Map;

public class UiCombo implements UiAndFieldFace<JSONArray> {

    private Combo combo;
    private Map<String,String> nameAndIdMap = new HashMap<>();
    private String nameKey ;
    private String idKey;
    private JSONArray jsonArray;
    public UiCombo(Composite composite , String nameKey , String idKey){
        combo = new Combo(composite, SWT.PUSH);
        this.nameKey = nameKey;
        this.idKey = idKey;

    }

    public void setNameKeyAndIdKey(String nameKey , String idKey){
        this.nameKey = nameKey;
        this.idKey = idKey;
    }

    public void input(JSONArray jsonArray  ){
        combo.removeAll();
        this.jsonArray = jsonArray;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString(nameKey);
            String id = jsonObject.getString(idKey);
            combo.add(name);
            nameAndIdMap.put(name,id);
        }

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
