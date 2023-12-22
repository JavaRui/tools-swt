package com.endless.tools.swt.save;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD,METHOD,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 如果实现了UiAndFieldFace的接口的，可以不需要写getMethodName和setMethodName
 * 但如果有写的话，会以手写的为主，不然默认为input和output
 * */
public @interface UiAndField {
    String getMethodName() default "";
    String setMethodName() default "";
    /**
     * 默认是以属性名为json的key，如果有特殊的key可以设置这个key();
     * */
    String key() default "";
}
