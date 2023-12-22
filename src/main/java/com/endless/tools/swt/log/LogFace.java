package com.endless.tools.swt.log;

import cn.hutool.core.date.DateUtil;
import com.endless.tools.swt.base.SwtVoid;
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
    public static void setLogText(Text logText){
        LogFace.logText = logText;
    }

    public static void log( Object log){
        String format = DateUtil.format(new Date(), "hh:mm:ss   ");

        if(LogFace.logText != null){
            SwtVoid.delayAsy(0,()->{
                logText.setText(format+log+"");
            });
        }
        else if(getLogger() != null){
            getLogger().info(log+"");
        }else{
            System.out.println(log);
        }
    }

    public static void log(Class<?> clsName, Object log){
        String format = DateUtil.format(new Date(), "hh:mm:ss   ");

        if(LogFace.logText != null){
            SwtVoid.delayAsy(0,()->{
                logText.setText(format+log+"");
            });
        }
        else if(getLogger() != null){
            getLogger().info(clsName.toString(),log);
        }else{
            System.out.println(clsName+"  "+log);
        }
    }


    public static void err( Object log){
        String format = DateUtil.format(new Date(), "hh:mm:ss   ");

        if(LogFace.logText != null){
            SwtVoid.delayAsy(0,()->{
                logText.setText(format+log+"");
            });
        }
        else if(getLogger() != null){
            getLogger().error(log+"");
        }else{
            System.err.println(log);
        }
    }

    public static void err(Class<?> clsName, Object log){
        String format = DateUtil.format(new Date(), "hh:mm:ss   ");

        if(LogFace.logText != null){
            SwtVoid.delayAsy(0,()->{
                logText.setText(format+log+"");
            });
        }
        else if(getLogger() != null){
            getLogger().error(clsName.toString(),log);
        }else{
            System.err.println(clsName+"  "+log);
        }
    }

}
