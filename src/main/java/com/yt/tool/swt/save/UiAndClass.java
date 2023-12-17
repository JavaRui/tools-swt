package com.yt.tool.swt.save;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD,METHOD,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 作用和UiAndField一样，但是，是放在一个对象上面，然后递归下去，解析uiAndClass里面的uiAndField
 * */
public @interface UiAndClass {

}
