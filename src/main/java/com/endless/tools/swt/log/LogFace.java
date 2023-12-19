package com.endless.tools.swt.log;

import cn.hutool.core.date.DateUtil;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 应该优先使用logFace，而不是用simpleLogComp，
 * 为了兼容没有ui的情况
 * */
public  class LogFace {

    static Logger logger = LoggerFactory.getLogger(LogFace.class);

    static Logger getLogger(){
        return logger;
    }

    static Text logText;
    static void setLogText(Text logText){
        LogFace.logText = logText;
    }

    static void log( Object log){
        String format = DateUtil.format(new Date(), "hh:mm:ss   ");

        if(LogFace.logText != null){
            logText.setText(format+log+"");
        }
        else if(getLogger() != null){
            getLogger().info(log+"");
        }else{
            System.out.println(log);
        }
    }

    static void log(Class<?> clsName, Object log){
        String format = DateUtil.format(new Date(), "hh:mm:ss   ");

        if(LogFace.logText != null){
            logText.setText(format+log+"");
        }
        else if(getLogger() != null){
            getLogger().info(clsName.toString(),log);
        }else{
            System.out.println(clsName+"  "+log);
        }
    }


    static void err( Object log){
        String format = DateUtil.format(new Date(), "hh:mm:ss   ");

        if(LogFace.logText != null){
            logText.setText(format+log+"");
        }
        else if(getLogger() != null){
            getLogger().error(log+"");
        }else{
            System.err.println(log);
        }
    }

    static void err(Class<?> clsName, Object log){
        String format = DateUtil.format(new Date(), "hh:mm:ss   ");

        if(LogFace.logText != null){
            logText.setText(format+log+"");
        }
        else if(getLogger() != null){
            getLogger().error(clsName.toString(),log);
        }else{
            System.err.println(clsName+"  "+log);
        }
    }

}
