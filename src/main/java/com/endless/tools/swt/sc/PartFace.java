package com.endless.tools.swt.sc;

import java.util.LinkedList;
import java.util.List;

public interface PartFace<T,K> {

    /**
     * 输入
     * */
    void input(List<T> input);
    /**
     * 输入
     * */
    default void input(T obj){
        List<T> linked = new LinkedList<>();
        linked.add(obj);
        input(linked);
    }

    /**
     * 输出
     * */
    K output();

}
